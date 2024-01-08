package com.jshop.jshopspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringbootproject.dao.UserDao;
import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.dto.User;
import com.jshop.jshopspringbootproject.response.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ResponseStructure<User> responseStructure;

	@Autowired
	private AdminService adminService;

	@Autowired
	private HttpSession httpSession;

	/*
	 * userSaveService
	 */
	public ResponseStructure<User> saveUserService(User user) {

		String email = adminService.emailValidation(user.getUserEmail());
		String password = adminService.validationPassword(user.getUserPassword());

		if (email != null) {
			if (password != null) {
				User user1 = userDao.saveUserDao(user);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMsg("User-Registered-Successfully");
				responseStructure.setDescription("congratulation--please-Login");
				responseStructure.setData(user1);
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("check--your--Password");
				responseStructure
						.setDescription("password should be one char capital one special and one number is required");
				responseStructure.setData(null);
			}
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("check-your-email");
			responseStructure.setDescription("your email should contain atleast alphabetnumber@hgja.com");
			responseStructure.setData(null);
		}
		return responseStructure;
	}

	/*
	 * loginUser
	 */
	public ResponseStructure<User> loginUserService(String email, String password) {
		User user = userDao.loginUserDao(email);

		if (user != null) {
			System.out.println(user.getUserPassword());
			if (password.equals(user.getUserPassword())) {

				httpSession.setAttribute("password", user.getUserPassword());
				httpSession.setMaxInactiveInterval(200);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMsg("login-success");
				responseStructure.setDescription("you have logged in....please perform your operation");
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("login credentials failed");
				responseStructure.setDescription("please checck your password...and type correctly");
			}
		} else {
			responseStructure.setStatusMsg("login credentials failed");
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setDescription("please checck your email...and type correctly");
		}
		return responseStructure;
	}
}
