package com.jshop.jshopspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringbootproject.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	/*
	 * get Admin Details By Passing adminEmail
	 */
	public Admin findByAdminEmail(String adminEmail);

}
