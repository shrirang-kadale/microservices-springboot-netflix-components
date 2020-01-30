package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserService {

	public Iterable<User> listAllProducts() {

		User user1 = new User(1, "Shrirang", 24);
		User user2 = new User(2, "Prakash", 25);
		List<User> userList = new ArrayList<>();
		
		userList.add(user1);
		userList.add(user2);
		
		return userList;
	}
	
	

}
