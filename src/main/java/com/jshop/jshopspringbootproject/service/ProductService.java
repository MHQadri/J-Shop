package com.jshop.jshopspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringbootproject.dao.ProductDao;
import com.jshop.jshopspringbootproject.dao.ProductOwnerDao;
import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.response.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

	@Autowired
	private ResponseStructure<Product> responseStructure;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductOwnerDao ownerDao;

	/*
	 * save product
	 */
	public ResponseStructure<Product> saveProductService(Product product) {

		if (httpSession.getAttribute("email") != null) {

			ProductOwner productOwner = ownerDao.getProductOwnerById(ownerDao.getProductOwnerId());
			if (productOwner != null) {
				product.setProductOwner(productOwner);
			}
			product.setProductVerified("no");
			Product product2 = productDao.saveProduct(product);
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMsg("Product Added");
			responseStructure.setDescription("now your product added... your login session is valid for 200 seconds");
			responseStructure.setData(product2);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Your session is out ....please login");
			responseStructure.setDescription("only product owner can add product details if he/she is logged in");
			responseStructure.setData(null);
		}

		return responseStructure;
	}
}
