package com.arshi.blog_api.service;

import com.arshi.blog_api.exception.AlreadyLikedException;
import com.arshi.blog_api.exception.IdNotFoundException;
import com.arshi.blog_api.exception.LikeNotFoundException;
import com.arshi.blog_api.model.Like;
import com.arshi.blog_api.model.Post;
import com.arshi.blog_api.repository.LikeRepository;
import com.arshi.blog_api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;

    public Like addLike(Long postId) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IdNotFoundException(postId + " post not found"));

        likeRepository.findByUserNameAndPostId(loggedInUser, postId).ifPresent(l-> {throw new AlreadyLikedException("You have already like this post");});
        Like like = new Like();
        like.setUserName(loggedInUser);
        like.setPostId(postId);
        Like savedLike = likeRepository.save(like);

        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);

        return savedLike;
    }

    public void deleteLike(Long postId) {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IdNotFoundException(postId + " post not found"));

        Like existingLike = likeRepository.findByUserNameAndPostId(loggedInUser, postId).orElseThrow(()->new LikeNotFoundException("Like is not found on this post"));

        likeRepository.delete(existingLike);

        post.setLikeCount(post.getLikeCount() - 1);
        postRepository.save(post);

    }

}
