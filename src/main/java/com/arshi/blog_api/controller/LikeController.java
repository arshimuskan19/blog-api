package com.arshi.blog_api.controller;

import com.arshi.blog_api.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> addLike(@PathVariable Long postId){
        likeService.addLike(postId);
        return new ResponseEntity<>("Post Liked Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}/like")
    public ResponseEntity<Void> deleteLike(@PathVariable Long postId){
        likeService.deleteLike(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
