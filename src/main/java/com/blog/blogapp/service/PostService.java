package com.blog.blogapp.service;

import com.blog.blogapp.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto>findAllPost();
    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto post);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
