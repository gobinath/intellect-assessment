package com.user.crud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;

import com.user.crud.model.User;

public class UserFieldsChecker {

	public static String check(User user) throws JSONException {

		if (user.getId() == null || user.getId().trim().equals("")) {

			return ResponseUtil.jsonFormatter("User's Id requird", "null", "XX(OPTIONAL)", "id", "Id field can't be empty");
		} else if (user.getfName() == null || user.getfName().equals("")) {
			return ResponseUtil.jsonFormatter("User's Firstname requird", user.getId(), "XX(OPTIONAL)", "fName",
					"Firstname field can't be empty");
		} else if (user.getlName() == null || user.getlName().equals("")) {
			return ResponseUtil.jsonFormatter("User's Lastname requird", user.getId(), "XX(OPTIONAL)", "lName",
					"Lastname field can't be empty");
		} else if (user.getEmail() == null || user.getEmail().equals("")) {
			return ResponseUtil.jsonFormatter("User's emailId requird", user.getId(), "XX(OPTIONAL)", "email",
					"Email field can't be empty");
		} else if (user.getPinCode() == 0) {
			return ResponseUtil.jsonFormatter("User's PinCode requird", user.getId(), "XX(OPTIONAL)", "pinCode",
					"PinCode field can't be empty");
		} else if (user.getBirthDate() == null || user.getBirthDate().equals("")) {
			return ResponseUtil.jsonFormatter("User's BirthDate requird", user.getId(), "XX(OPTIONAL)", "birthDate",
					"BirthDate field can't be empty");
		} else if (user.getBirthDate() != null) {

			String dateStr = user.getBirthDate().trim();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			Date date;
			Date now = new Date();
			try {
				date = df.parse(dateStr);

			} catch (ParseException e) {
				return ResponseUtil.jsonFormatter("Date pattern mismatch", user.getId(), "XX(OPTIONAL)", "birthDate",
						"BirthDate should be in dd-MMM-yyyy pattern e.g: 07-JAN-1992");
			}

			if (date.before(now) == false) {
				return ResponseUtil.jsonFormatter("Enter correct birth date", user.getId(), "XX(OPTIONAL)", "birthDate",
						"User date must be less than current date");
			}

		}
		return "OK";

	}
}
