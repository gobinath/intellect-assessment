package com.user.crud.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.crud.model.User;

@Service
public class UserService {

	private static List<User> users = new ArrayList<>(Arrays.asList(
			new User("100", "Gobinath", "Velmurugesan", "gobinath.gct@gmail.com", 123456, "17-JUL-1992", true),
			new User("101", "Prabhu", "Rama", "prabhu@mail.com", 12345, "17-JUL-1992", true),
			new User("102", "Ragu", "Satheesh", "raghu@mail.com", 12345, "17-JUL-1992", true)

	));

	public List<User> getUsers() {

		return users;
	}

}
