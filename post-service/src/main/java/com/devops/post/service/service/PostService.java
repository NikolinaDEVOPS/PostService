package com.devops.post.service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.devops.post.service.model.Comment;
import com.devops.post.service.model.Post;
import com.devops.post.service.model.User;
import com.devops.post.service.repository.CommentRepository;
import com.devops.post.service.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	public Post findById(Integer id) {
		return postRepository.findById(id).get();
	}

	public List<Post> findByUsername(String username) {
		return postRepository.findByUsername(username);
	}
	
	public Post like(String username, Integer postId) {
		Post post = postRepository.findByPostId(postId);
		if (post.getDislikedBy().contains(username)) {
			post.getDislikedBy().remove(username);
		}
		
		if (post.getLikedBy().contains(username)) {
			return post;
		}
		
		post.getLikedBy().add(username);
		postRepository.save(post);
		return post;
	}

	public Post dislike(String username, Integer postId) {
		Post post = postRepository.findByPostId(postId);
		if (post.getLikedBy().contains(username)) {
			post.getLikedBy().remove(username);
		}
		
		if (post.getDislikedBy().contains(username)) {
			return post;
		}
		post.getDislikedBy().add(username);
		postRepository.save(post);
		return post;
	}
	
	public Comment comment(String username, Integer id, String content) {
		Post post = postRepository.findByPostId(id);
		Comment comment = new Comment(null, username, content);
		comment = commentRepository.save(comment);
		post.getPostComments().add(comment);
		postRepository.save(post);
		return comment;
	}

	public List<Post> findAllPublic() {
		User[] publicUsers =
				restTemplate.getForObject(
				"http://ACCOUNT-SERVICE/user/public"
				, User[].class);
		
		List<Post> posts = new ArrayList<Post>();
		List<Post> post;
		for (int i = 0; i < publicUsers.length; i++) {
		
			post = postRepository.findByUsername(publicUsers[i].getUsername());
			posts.addAll(post);
		}
		return posts;
	}

	public List<Post> searchByTag(String tag) {
		List<Post> posts = findAllPublic();
		List<Post> searchResult = new ArrayList<Post>();
		for (int i = 0; i < posts.size(); i++) {
			if (posts.get(i).getTags().contains(tag)) {
				searchResult.add(posts.get(i));
			}
		}
		return searchResult;
	}
}
