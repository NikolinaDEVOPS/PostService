package com.devops.post.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.post.service.model.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer>{

}