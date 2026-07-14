package com.arshi.blog_api.controller;

import com.arshi.blog_api.model.Comment;
import com.arshi.blog_api.repository.CommentRepository;
import com.arshi.blog_api.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @Valid @RequestBody Comment comment) {
        Comment commentAdded = commentService.addComment(postId, comment);
        return new ResponseEntity<>(commentAdded, HttpStatus.CREATED);
    }

    @GetMapping("/api/posts/{postId}/comments")
    public List<Comment> getAllCommentsByPostId(@PathVariable Long postId){
        return commentService.findAllCommentsByPostId(postId);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
