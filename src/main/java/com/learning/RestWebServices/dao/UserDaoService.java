package com.learning.RestWebServices.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.learning.RestWebServices.bean.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	private static int count = 3;

	static {
		users.add(new User(1, "Raghav", new Date(System.currentTimeMillis())));
		users.add(new User(2, "Vishkha", new Date(System.currentTimeMillis())));
		users.add(new User(3, "Sekar", new Date(System.currentTimeMillis())));
	}

	public List<User> findAll() {
		return users;
	}

	public User saveUser(User user) {
		if (user.getId() == 0) {
			user.setId(++count);
		}
		users.add(user);
		return user;
	}

	public User findUserById(int id) {
		Optional<User> result = users.stream().filter((user -> user.getId() == id)).findFirst();
		if (result.isPresent()) {
			return result.get();
		} else
			return null;
	}

	public User deleteUserById(int id) {
		Optional<User> result = users.stream().filter((user -> user.getId() == id)).findFirst();
		if (result.isPresent()) {
			users.remove(result.get());
			return result.get();
		} else
			return null;
	}
}