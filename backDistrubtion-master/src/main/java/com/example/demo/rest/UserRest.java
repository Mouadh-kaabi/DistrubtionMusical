package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsersDao;
import com.example.demo.entities.UserAuth;
import com.example.demo.entities.Users;
import com.example.demo.repository.UserRepo;

@RestController
@RequestMapping("/users")

@CrossOrigin(origins = "http://192.168.1.111:4200")
public class UserRest {

	
	@Autowired
	UserRepo userRepository;
	
	@Autowired
	ArtisteRest artisteRest;
	
	@Autowired
	UsersDao usersDao ;
	
	
	/*@GetMapping("/{username}")
	public List<UserAuth> getUsers(@PathVariable String email) {
		List<UserAuth> lUsers = new ArrayList<UserAuth>();
		List<Users> user = userRepository.findB(email);
		if(!user.isEmpty() && user != null) 
			for (Users users : user) {
				UserAuth u = new UserAuth();
				u.setEmail(users.getEmail());
				u.setPassword(users.getPassword());
				u.setRole(users.getRole());
				lUsers.add(u);
			}
			return lUsers;
		
		
		
		
	}*/
	
	
	
	@GetMapping("/forgotPassword/{email}")
	public String forgotPassword(@PathVariable String email) throws MessagingException { 
		return artisteRest.forgotPassword(email);
	}
	
	@GetMapping("/resetPassword/{token}/{password}")
	public String resetPassword(@PathVariable String token, 
			@PathVariable String password) { 
		return artisteRest.restPassword(token, password)	;
	}
	
	/*@GetMapping("/id/{userName}")
	public Users getUserIdByUserName(@PathVariable String email) {
		Users user = usersDao.getUserId(email, "email");
		if(user == null) {
			user = usersDao.getUserId(email, "email");
		}
		return user;
	}*/
}
