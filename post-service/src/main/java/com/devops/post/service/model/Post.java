package com.devops.post.service.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	private String description;
	private String username;
	private String picture;
	
	@Column(name="likedBy")
	@ElementCollection(targetClass=String.class)
	private List<String> likedBy;
	

	@Column(name="dislikedBy")
	@ElementCollection(targetClass=String.class)
	private List<String> dislikedBy;
	
	@OneToMany()
	private List<Comment> postComments;
}
