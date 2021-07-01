package com.devops.post.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devops.post.service.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUsername(String username);
	Post findByPostId(Integer id);
}
