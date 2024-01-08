package com.jshop.jshopspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringbootproject.dao.ProductOwnerDao;
import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.response.ResponseStructure;

import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author Mohd Hashim
 *
 */

@Service
public class ProductOwnerService {

	/*
	 * to call save method from productowner dao class
	 */
	@Autowired
	private ProductOwnerDao ownerDao;
	/*
	 * to call validatePassword() and validateEmail() method from AdminService class
	 */
	@Autowired
	private AdminService adminService;

	/*
	 * to display some message on postman api
	 */
	@Autowired
	private ResponseStructure<ProductOwner> responseStructure;
	
	@Autowired
	private HttpSession httpSession;

	/*
	 * productOwner Register
	 */
	public ResponseStructure<ProductOwner> saveProductOwnerService(ProductOwner productOwner) {

		String email = adminService.emailValidation(productOwner.getProductOwnerEmail());
		String password = adminService.validationPassword(productOwner.getProductOwnerPassword());

		if (email != null) {
			if (password != null) {
				ProductOwner owner = ownerDao.saveProductOwnerDao(productOwner);

				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMsg("product-owner--Registered");
				responseStructure.setDescription("congratulation--please-Login");
				responseStructure.setData(owner);
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("check--your--Password");
				responseStructure.setDescription("");
				responseStructure.setData(null);
			}
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("check-your-email");
			responseStructure.setDescription("");
			responseStructure.setData(null);
		}
		return responseStructure;
	}

	/*
	 * loginWithProductOwner
	 */
	public ResponseStructure<ProductOwner> loginWithProductOwner(String email, String password) {

		ProductOwner productOwner = ownerDao.getProductOwnerByEmail(email);

		if (productOwner != null) {
			if (productOwner.getAdminVerify().equalsIgnoreCase("yes")) {
				if (productOwner.getProductOwnerPassword().equals(password)) {
					httpSession.setAttribute("email", productOwner.getProductOwnerEmail());
					httpSession.setMaxInactiveInterval(200);
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setStatusMsg("login successfully....");
					responseStructure.setDescription("Now you can add your product details");
					responseStructure.setData(productOwner);
				} else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setStatusMsg("password is incorrect please check it");
					responseStructure.setDescription("please check your password... or forget your password");
					responseStructure.setData(null);
				}
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("your are not verified");
				responseStructure.setDescription("please contact with admin....55854782");
				responseStructure.setData(null);
			}
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("check-your-email");
			responseStructure.setDescription("your email is incorrect");
			responseStructure.setData(null);
		}
		return responseStructure;
	}
}
