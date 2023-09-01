package com.blog.blogapp.controller;

import com.blog.blogapp.dto.CommentDto;
import com.blog.blogapp.dto.PostDto;
import com.blog.blogapp.service.PostService;
import com.blog.blogapp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService,CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    //http://localhost:8080/admin/posts
    @GetMapping (path="/admin/posts")
    public String findAllPosts(Model model){
        List<PostDto> posts = postService.findAllPost();
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }


    //http://localhost:8080/admin/posts/newpost
    @GetMapping (path="admin/posts/newpost")
    public String newPostForm(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post",postDto);
        return "admin/createPost";
    }
    // handler method to handle form submit request
    @PostMapping ("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/createPost";
        }

        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle){
        // OOPS Concepts Explained in Java
        // oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }

//    http://localhost:8080/admin/posts/1/edit
    @GetMapping(path="/admin/posts/{postId}/edit")
    public String getPostById(@PathVariable("postId") Long postId,Model model){
        PostDto postById = postService.findPostById(postId);
        model.addAttribute("post",postById);
        return "admin/edit_post";
    }

    // handler method to handle edit post form submit request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto post,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", post);
            return "admin/edit_post";
        }

        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }
   @GetMapping(path="/admin/posts/{postId}/delete")
    public String updatePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return"redirect:/admin/posts";
   }

   @GetMapping(path="/admin/posts/{postUrl}/view")
   public String viewPost(@PathVariable("postUrl") String postUrl,Model model){
       PostDto postByUrl = postService.findPostByUrl(postUrl);
       model.addAttribute("post",postByUrl);
       return "admin/view_post";
   }

    // handler method to handle search blog posts request
    // localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/comments")
    public String getComments(Model model){
        List<CommentDto> comments = commentService.getComments();
        model.addAttribute("comments",comments);
        return "admin/comments";
    }

    @GetMapping("/admin/comments/{commentId}/delete")
    public String deletePost(@PathVariable("commentId") long commentId,Model model){
        commentService.deleteComment(commentId);
        List<CommentDto> comments = commentService.getComments();
        model.addAttribute("comments",comments);
        return "admin/comments";
    }

}
