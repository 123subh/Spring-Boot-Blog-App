package com.blog.blogapp.controller;

import com.blog.blogapp.dto.CommentDto;
import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogController {

    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewBlogPost(Model model){
        List<PostDto> postDtos = postService.findAllPost();
        model.addAttribute("posts",postDtos);
        return "blog/view_posts";
    }
    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable("postUrl") String postUrl,
                            Model model){
        PostDto post = postService.findPostByUrl(postUrl);
        CommentDto commentDto  = new CommentDto();
        model.addAttribute("post", post);
        model.addAttribute("comment", commentDto);
        return "blog/blog_post";
    }



}
