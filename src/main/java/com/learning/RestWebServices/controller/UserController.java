package com.learning.RestWebServices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.RestWebServices.bean.User;
import com.learning.RestWebServices.dao.UserDaoService;
import com.learning.RestWebServices.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Integer id) {
		User result = userDaoService.findUserById(id);
		if (result == null) {
			throw new UserNotFoundException("Id: " + id);
		}
		return result;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		User result = userDaoService.deleteUserById(id);
		if (result == null) {
			throw new UserNotFoundException("Id: " + id);
		}

	}
}
