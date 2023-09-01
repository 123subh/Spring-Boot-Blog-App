package com.blog.blogapp.service.impl;

import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.mapper.PostMapper;
import com.blog.blogapp.repo.PostRepo;
import com.blog.blogapp.service.PostService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<PostDto> findAllPost() {
        List<Post> allPost= postRepo.findAll();
        return allPost.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepo.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepo.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postdto) {
        Post post = PostMapper.mapToPost(postdto);
        postRepo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepo.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepo.searchPost(query);

        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

}
