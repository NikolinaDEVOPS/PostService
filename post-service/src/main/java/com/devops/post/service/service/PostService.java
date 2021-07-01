package com.devops.post.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.post.service.model.Post;
import com.devops.post.service.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	public Post findById(Integer id) {
		return postRepository.findById(id).get();
	}
}
