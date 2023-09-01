package com.blog.blogapp.repo;

import com.blog.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {

    Optional<Post> findByUrl(String url);

    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')"
    )
    List<Post> searchPost(@Param("query") String query);
}
