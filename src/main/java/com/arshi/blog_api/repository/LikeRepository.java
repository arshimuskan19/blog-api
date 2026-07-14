package com.arshi.blog_api.repository;

import com.arshi.blog_api.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserNameAndPostId(String userName, Long postId);
}
