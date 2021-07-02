package com.devops.post.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer userId;
	public String username;

	public String name;
	public String surname;
	private List<Integer> favorites;
}