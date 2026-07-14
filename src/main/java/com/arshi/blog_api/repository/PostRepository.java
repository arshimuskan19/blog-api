package com.arshi.blog_api.repository;

import com.arshi.blog_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostByCategory(String category);
    List<Post> findAllByOrderByLikeCountDesc();
}
