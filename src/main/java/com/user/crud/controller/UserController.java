package com.user.crud.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.user.crud.model.User;
import com.user.crud.model.UserUpdateModel;
import com.user.crud.service.UserService;
import com.user.crud.util.ResponseUtil;
import com.user.crud.util.UserFieldsChecker;

@RestController()
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//get all users
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userService.getUsers();
		return users;

	}

	
	// add user to users list
	@PostMapping("/users")
	public String createUser(@RequestBody User user) throws JSONException {

		String response = UserFieldsChecker.check(user);

		if (response.equals("OK") != true) {
			return response;
		}
		List<User> users = userService.getUsers();

		for (User u : users) {

			if (u.getId().trim().equals(user.getId().trim()) && u.isActive() == true) {
				return ResponseUtil.jsonFormatter("Duplicate UserId issue!", user.getId(), "XX(OPTIONAL)", "id",
						"User Id " + user.getId() + " Already exists. Please Enter Unique Id");
			} else if (u.getEmail().trim().equals(user.getEmail().trim()) && u.isActive() == true) {
				return ResponseUtil.jsonFormatter("Duplicate EmailId issue!", user.getId(), "XX(OPTIONAL)", "id",
						" Email Id " + user.getEmail() + " Already exists. Please Enter Unique Id");
			}

		}

		users.add(user);

		return ResponseUtil.jsonFormatter("User successfully added!", user.getId(), null, null, null);
	}

	
	//update existing user
	@PutMapping("/users/user-id/{id}")
	public String updateUser(@PathVariable String id, @RequestBody UserUpdateModel userUpdateModel) {

		String response = null;
		if (userUpdateModel.getBirthDate() != null) {
			response = checkBirthDate(id, userUpdateModel.getBirthDate());
			if (response.equals("OK") != true) {
				return response;
			}
		}

		List<User> users = userService.getUsers();
		User find = null;
		for (User u : users) {

			if (u.getId().equals(id.trim()) && u.isActive() == true) {
				find = u;
			} else if (userUpdateModel.getEmail() != null
					&& u.getEmail().trim().equals(userUpdateModel.getEmail().trim()) && u.isActive() == true) {
				return ResponseUtil.jsonFormatter(" User " + u.getId() + " already has this mailId.", u.getId(),
						"XX(OPTIONAL)", "email",
						" Email Id " + u.getEmail() + " Already exists. Please Enter Unique Id");
			}
		}

		if (find == null) {

			return ResponseUtil.jsonFormatter(" User " + id + " does't exists.", id, "XX(OPTIONAL)", "id",
					" Please enter correct user Id for updation");
		} else {
			User temp = find;
			users.remove(temp);
			temp.setEmail(userUpdateModel.getEmail() != null ? userUpdateModel.getEmail().trim() : temp.getEmail());
			temp.setBirthDate(
					userUpdateModel.getBirthDate() != null ? userUpdateModel.getBirthDate() : temp.getBirthDate());
			users.add(temp);
			return ResponseUtil.jsonFormatter(" User " + id + " Updated Successfully.", id, null, null, null);
		}
	}
	
	// deactivate user 
	@DeleteMapping("/users/user-id/{id}")
	public String deleteUser(@PathVariable String id) {

		List<User> users = userService.getUsers();

		for (User u : users) {

			if (u.getId().trim().equals(id.trim()) && u.isActive() == true) {

				u.setActive(false);
				return ResponseUtil.jsonFormatter(" User " + id + " deleted Successfully.", id, null, null, null);
			}
		}

		return ResponseUtil.jsonFormatter(" User " + id + " Not found! ", id, "XX(OPTIONAL)", "id",
				"user id not present in users list");

	}

	public String checkBirthDate(String id, String dateString) {

		String dateStr = dateString;
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date date;
		Date now = new Date();
		try {
			date = df.parse(dateStr);

		} catch (ParseException e) {
			return ResponseUtil.jsonFormatter("Date pattern mismatch", id, "XX(OPTIONAL)", "birthDate",
					"BirthDate should be in dd-MMM-yyyy pattern e.g: 07-JAN-1992");
		}

		if (date.before(now) == false) {
			return ResponseUtil.jsonFormatter("Enter correct birth date", id, "XX(OPTIONAL)", "birthDate",
					"User date must be less than current date");
		}
		return "OK";
	}
}
