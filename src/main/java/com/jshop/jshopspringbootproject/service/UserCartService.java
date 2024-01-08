package com.jshop.jshopspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringbootproject.dao.UserCartDao;
import com.jshop.jshopspringbootproject.dto.UserCart;
import com.jshop.jshopspringbootproject.response.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class UserCartService {

	@Autowired
	private UserCartDao userCartDao;

	@Autowired
	private ResponseStructure<UserCart> responseStructure;

	@Autowired
	private HttpSession httpSession;

	/*
	 * add product in userCart
	 */
	public ResponseStructure<UserCart> addProductInUserCartService(int productQauntity, int productId) {

		if (httpSession.getAttribute("email") != null) {

			UserCart userCart = userCartDao.addProductInUserCart(productQauntity, productId);

			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMsg("login success fully");
			responseStructure.setDescription("you can perform operations");
			responseStructure.setData(userCart);

		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Your session is logout");
			responseStructure.setDescription("Please login again");
			responseStructure.setData(null);
		}

		return responseStructure;
	}

}
