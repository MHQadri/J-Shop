package com.jshop.jshopspringbootproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringbootproject.dto.User;
import com.jshop.jshopspringbootproject.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Autowired
	private UserRepository userRepository;

	/*
	 * saveUserDao
	 */
	public User saveUserDao(User user) {

		return userRepository.save(user);
	}

	/*
	 * logingUser
	 */
	public User loginUserDao(String email) {

		User user = userRepository.findByUserEmail(email);
		if (user != null) {
			setUser(user);
			return user;
		} else {
			return null;
		}
	}

}
