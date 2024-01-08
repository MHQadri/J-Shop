package com.jshop.jshopspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringbootproject.dto.UserCart;
import com.jshop.jshopspringbootproject.response.ResponseStructure;
import com.jshop.jshopspringbootproject.service.UserCartService;

@RestController
public class UserCartController {

	@Autowired
	private UserCartService userCartService;

	/*
	 * add product in userCart
	 */
	public ResponseStructure<UserCart> addProductInUserCartController(int productQauntity, int productId) {

		return userCartService.addProductInUserCartService(productQauntity, productId);
	}

}
