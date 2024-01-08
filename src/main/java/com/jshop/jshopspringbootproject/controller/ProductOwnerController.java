package com.jshop.jshopspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.response.ResponseStructure;
import com.jshop.jshopspringbootproject.service.ProductOwnerService;

@RestController
@RequestMapping("/productOwner")
public class ProductOwnerController {

	@Autowired
	private ProductOwnerService ownerService;

	/*
	 * productOwner Register
	 */
	@PostMapping("/saveProductOwner")
	public ResponseStructure<ProductOwner> saveProductOwnerController(@RequestBody ProductOwner productOwner) {

		return ownerService.saveProductOwnerService(productOwner);
	}

	/*
	 * loginWithProductOwner
	 */
	@GetMapping("/loginWithOwner/{email}/{password}")
	public ResponseStructure<ProductOwner> loginWithProductOwner(@PathVariable String email,
			@PathVariable String password) {

		return ownerService.loginWithProductOwner(email, password);
	}

	
}
