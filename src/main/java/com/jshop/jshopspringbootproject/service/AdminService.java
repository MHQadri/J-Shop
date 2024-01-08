package com.jshop.jshopspringbootproject.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringbootproject.dao.AdminDao;
import com.jshop.jshopspringbootproject.dao.ProductDao;
import com.jshop.jshopspringbootproject.dto.Admin;
import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.repository.ProductOwnerRepository;
import com.jshop.jshopspringbootproject.response.ResponseStructure;

import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author Mohd Hashim
 *
 */
@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ResponseStructure<Admin> responseStructure1;

	@Autowired
	private ResponseStructure<ProductOwner> responseStructure2;

	@Autowired
	private ResponseStructure<List<ProductOwner>> responseStructure3;

	@Autowired
	private ResponseStructure<Product> responseStructureProduct;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductDao productDao;

	/*
	 * password validation
	 */
	public String validationPassword(String password) {

		if ((password.length() > 8) && (password.length() <= 15)) {

			Pattern alphabets = Pattern.compile("[a-zA-Z]");
			Pattern numbers = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$*&%]");

			Matcher alpha = alphabets.matcher(password);
			Matcher num = numbers.matcher(password);
			Matcher spec = special.matcher(password);
			if ((alpha.find()) && (num.find()) && (spec.find())) {

				return password;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	/*
	 * email validation
	 */
	public String emailValidation(String email) {

		Pattern alphabets = Pattern.compile("[a-z]");
		Pattern numbers = Pattern.compile("[0-9]");
		Pattern special = Pattern.compile("[@.]");

		Matcher alpha = alphabets.matcher(email);
		Matcher num = numbers.matcher(email);
		Matcher spec = special.matcher(email);
		if ((alpha.find()) && (num.find()) && (spec.find())) {

			return email;
		} else {
			return null;
		}
	}

	/*
	 * sign-up code for admin saveMethod
	 */
	public ResponseStructure<Admin> saveAdminService(Admin admin) {

		String password = validationPassword(admin.getAdminPassword());
		String email = emailValidation(admin.getAdminEmail());

		if (email != null) {

			if (password != null) {
				admin.setAdminPassword(password);
				admin.setAdminEmail(email);
				Admin admin1 = adminDao.saveAdminDao(admin);

				responseStructure1.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure1.setStatusMsg("Admin----Registered");
				responseStructure1.setDescription("congratulation--please-Login");
				responseStructure1.setData(admin1);
			} else {
				responseStructure1.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure1.setStatusMsg("check--your--Password");
				responseStructure1.setDescription("");
				responseStructure1.setData(null);
			}

		} else {
			responseStructure1.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure1.setStatusMsg("check-your-email");
			responseStructure1.setDescription("");
			responseStructure1.setData(null);
		}
		return responseStructure1;
	}

	/*
	 * login for admin
	 */

	public ResponseStructure<Admin> loginWithAdminService(String email, String password) {

		Admin admin = adminDao.loginWithAdminDao(email);

		if (email != null) {

			if (admin.getAdminPassword().equals(password)) {

				httpSession.setAttribute("password", admin.getAdminPassword());
				httpSession.setMaxInactiveInterval(200);
				responseStructure1.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure1.setStatusMsg("Admin----login");
				responseStructure1.setDescription("you have logged in ...");
				responseStructure1.setData(null);
			} else {
				responseStructure1.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure1.setStatusMsg("login credential failed");
				responseStructure1.setDescription("please check your password...");
				responseStructure1.setData(null);
			}

		} else {
			responseStructure1.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure1.setStatusMsg("login credential failed");
			responseStructure1.setDescription("please check your email...");
			responseStructure1.setData(null);
		}
		return responseStructure1;
	}

	/*
	 * getAllProductOwnerAdminDao
	 */
	public ResponseStructure<List<ProductOwner>> getAllProductOwnerAdminService() {

		List<ProductOwner> owners = new ArrayList<ProductOwner>();

		if (httpSession.getAttribute("password") != null) {

			for (ProductOwner productOwner : adminDao.getAllProductOwnerAdminDao()) {

				if (productOwner.getAdminVerify().equalsIgnoreCase("no")) {

					owners.add(productOwner);

				}
				responseStructure3.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure3.setStatusMsg("Success");
				responseStructure3.setDescription("please find your data below....");
				responseStructure3.setData(owners);

			}
		} else {
			responseStructure3.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure3.setStatusMsg("Your session is  logout ");
			responseStructure3.setDescription("please login again");
			responseStructure3.setData(null);
		}
		return responseStructure3;
	}

	/*
	 * getProductOwnerById
	 */
	public ResponseStructure<ProductOwner> getProductOwnerById(int productOwnerId) {

		ProductOwner owner = adminDao.getProductOwnerById(productOwnerId);

		if (owner != null) {

			if (httpSession.getAttribute("password") != null) {
				responseStructure2.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure2.setStatusMsg("success");
				responseStructure2.setDescription("you have logged in ...");
				responseStructure2.setData(owner);
			} else {
				responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure2.setStatusMsg("your session is logout");
				responseStructure2.setDescription("please login agian...");
				responseStructure2.setData(null);
			}
		} else {
			responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure2.setStatusMsg("productOwner is is not present");
			responseStructure2.setDescription("#############");
			responseStructure2.setData(null);
		}
		return responseStructure2;
	}

	/*
	 * verify product owner from no to yes and unverified from yes to no
	 */
	public ResponseStructure<ProductOwner> verifyProductOwnerService(int productOwnerId) {

		ProductOwner owner = adminDao.getProductOwnerById(productOwnerId);

		if (owner != null) {

			if (httpSession.getAttribute("password") != null) {
				
				ProductOwner productOwner = adminDao.verifyProductOwnerDao(productOwnerId);
				responseStructure2.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure2.setStatusMsg("success");
				responseStructure2.setDescription("produt-owner is verified successfully");
				responseStructure2.setData(productOwner);
			} else {
				responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure2.setStatusMsg("your session is logout");
				responseStructure2.setDescription("please login agian...");
				responseStructure2.setData(null);
			}
		} else {
			responseStructure2.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure2.setStatusMsg("productOwner id is not present");
			responseStructure2.setDescription("#################");
			responseStructure2.setData(null);
		}
		return responseStructure2;
	}

	/*
	 * verify Product by admin
	 */
	public ResponseStructure<Product> verifyProductDetailsAdminService(int productId) {

		Product product = adminDao.verifyProductDetailsByAdminDao(productId);
		if (product != null) {
			if (httpSession.getAttribute("password") != null) {

				Product product2 = adminDao.verifyProductDetailsByAdminDao(productId);

				responseStructureProduct.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructureProduct.setStatusMsg("success");
				responseStructureProduct.setDescription("produt is verified successfully");
				responseStructureProduct.setData(product2);
			} else {
				responseStructureProduct.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructureProduct.setStatusMsg("your session is logout");
				responseStructureProduct.setDescription("please login agian...");
				responseStructureProduct.setData(null);
			}

		} else {
			responseStructureProduct.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructureProduct.setStatusMsg("product id is not present");
			responseStructureProduct.setDescription("#################");
			responseStructureProduct.setData(null);
		}
		return responseStructureProduct;
	}

}
