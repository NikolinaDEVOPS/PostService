package com.devops.post.service.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.post.service.model.Post;
import com.devops.post.service.service.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {
	@Autowired
	private PostService postService;

	@PostMapping("/")
	public Post savePost(@RequestBody Post post) {
		log.info("savePost method of PostController");
		return postService.savePost(post);
	}
	
	@GetMapping("/{username}")
	public List<Post> findByUsername(@PathVariable String username) {
	    return postService.findByUsername(username);
	}
}
