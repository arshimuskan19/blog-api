package com.arshi.blog_api.service;

import com.arshi.blog_api.exception.IdNotFoundException;
import com.arshi.blog_api.exception.UnauthorizedAccessException;
import com.arshi.blog_api.model.Post;
import com.arshi.blog_api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post){
        post.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()-> new IdNotFoundException("Post Id "+postId+" doesnt exist"));
    }

    public Post updatePost(Long postId, Post updatedPost){
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Post existingPost =  getPostById(postId);
        if(!existingPost.getUserName().equals(loggedInUser)){
            throw new UnauthorizedAccessException("You are not allowed to update this post");
        }
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setCategory(updatedPost.getCategory());
        return postRepository.save(existingPost);
    }

    public void deletePost(Long postId){
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Post existingPost =  getPostById(postId);
        if(!existingPost.getUserName().equals(loggedInUser)){
            throw new UnauthorizedAccessException("You are not allowed to delete this post");
        }
        postRepository.delete(existingPost);
    }

    public List<Post> getPostByCategory(String category){
        return postRepository.findPostByCategory(category);
    }

    public List<Post> getPopularPosts(){
        return postRepository.findAllByOrderByLikeCountDesc();
    }

}
