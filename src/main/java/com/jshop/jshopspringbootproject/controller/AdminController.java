package com.jshop.jshopspringbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringbootproject.dto.Admin;
import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.response.ResponseStructure;
import com.jshop.jshopspringbootproject.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	/*
	 * sign-up code for admin saveMethod
	 */
	@PostMapping("/saveAdmin")
	public ResponseStructure<Admin> saveAdminService(@RequestBody Admin admin) {

		return adminService.saveAdminService(admin);
	}

	/*
	 * login with admin
	 */
	@GetMapping("/loginAdmin/{email}/{password}")
	public ResponseStructure<Admin> loginWithAdminService(@PathVariable String email, @PathVariable String password) {
		return adminService.loginWithAdminService(email, password);
	}

	/*
	 * getAllProductOwnerAdminDao
	 */
	@GetMapping("/getAllProductOwner")
	public ResponseStructure<List<ProductOwner>> getAllProductOwnerAdminService() {

		return adminService.getAllProductOwnerAdminService();
	}

	/*
	 * getProductOwnerById
	 */
	@GetMapping("/getProductOwnerById/{productOwnerId}")
	public ResponseStructure<ProductOwner> getProductOwnerById(@PathVariable int productOwnerId) {

		return adminService.getProductOwnerById(productOwnerId);
	}

	/*
	 * verify product owner from no to yes and unverified from yes to no
	 */
	@PutMapping("/verifyProductOwner/{productOwnerId}")
	public ResponseStructure<ProductOwner> verifyProductOwnerController(@PathVariable int productOwnerId) {

		return adminService.verifyProductOwnerService(productOwnerId);

	}

	/*
	 * verify Product by admin
	 */
	@PutMapping("/verifyProductByAdmin/{productId}")
	public ResponseStructure<Product> verifyProductDetailsAdminController(@PathVariable int productId) {

		return adminService.verifyProductDetailsAdminService(productId);

	}

}
