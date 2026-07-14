package com.arshi.blog_api.controller;

import com.arshi.blog_api.model.Post;
import com.arshi.blog_api.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController{

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        Post postCreated = postService.createPost(post);
        return new ResponseEntity<>(postCreated,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @Valid @RequestBody Post post){
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{category}")
    public List<Post> getPostsByCategory(@PathVariable String category){
        return postService.getPostByCategory(category);
    }

    @GetMapping("/popular")
    public List<Post> getPopularPosts(){
        return postService.getPopularPosts();
    }
}