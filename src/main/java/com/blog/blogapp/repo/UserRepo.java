package com.blog.blogapp.repo;

import com.blog.blogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
