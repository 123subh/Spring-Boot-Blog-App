package com.blog.blogapp.service.impl;

import com.blog.blogapp.dto.CommentDto;
import com.blog.blogapp.entity.Comment;
import com.blog.blogapp.entity.Post;
import com.blog.blogapp.mapper.CommentMapper;
import com.blog.blogapp.repo.CommentRepo;
import com.blog.blogapp.repo.PostRepo;
import com.blog.blogapp.service.CommentService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private PostRepo postRepo;
    private CommentRepo commentRepo;



    public CommentServiceImpl(PostRepo postRepo, CommentRepo commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;

    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepo.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepo.save(comment);
    }

    @Override
    public List<CommentDto> getComments() {
        List<Comment> comments = commentRepo.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepo.deleteById(commentId);
    }
}
