package com.arshi.blog_api.service;

import com.arshi.blog_api.exception.IdNotFoundException;
import com.arshi.blog_api.exception.UnauthorizedAccessException;
import com.arshi.blog_api.model.Comment;
import com.arshi.blog_api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Long postID, Comment comment) {
        comment.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPostId(postID);
        return commentRepository.save(comment);
    }

    public List<Comment> findAllCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    public void deleteComment(Long id){
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment userId = commentRepository.findById(id).orElseThrow(()-> new IdNotFoundException(id+" Id doesnt exist"));
        if(!loggedInUser.equals(userId.getUserName())){
            throw new UnauthorizedAccessException("You are not allowed to delete this comment");
        }
        commentRepository.deleteById(userId.getId());
    }
}
