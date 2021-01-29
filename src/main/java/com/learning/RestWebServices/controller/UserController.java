package com.learning.RestWebServices.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.RestWebServices.bean.User;
import com.learning.RestWebServices.dao.UserDaoService;
import com.learning.RestWebServices.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable Integer id) {
		User result = userDaoService.findUserById(id);
		if (result == null) {
			throw new UserNotFoundException("Id: " + id);
		}
		EntityModel<User> resource = EntityModel.of(result);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
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

	@GetMapping("/helloworldintmsg")
	public String helloWorld(@RequestHeader(required = false, name="Accept-Language") Locale locale) {
		return messageSource.getMessage("good.morning.message",null, locale); 
	}
	
	//alt way for helloworldintmsg
	@GetMapping("/helloworldintmsgdef")
	public String helloWorld() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale()); 
	}
	
}
