package com.jshop.jshopspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jshop.jshopspringbootproject.dao.ProductDao;
import com.jshop.jshopspringbootproject.dao.ProductOrderDao;
import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.ProductOrder;
import com.jshop.jshopspringbootproject.response.ResponseStructure;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductOrderService {

	@Autowired
	private ProductOrderDao productOrderDao;

	@Autowired
	private ResponseStructure<ProductOrder> responseStructure;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private HttpSession session;

	/*
	 * Save productOrderMthod
	 */
	public ResponseStructure<ProductOrder> saveProductOrderService(int productId, int qauntity, String address) {

		Product product = productDao.getProductByIdDao(productId);

		if ((product != null) && (session.getAttribute("password") != null)) {
			if (product.getProductVerified().equalsIgnoreCase("yes")) {

				if (qauntity <= product.getProuductQauntity()) {

					ProductOrder productOrder = productOrderDao.saveProductOrderDao(productId, qauntity, address);

					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setStatusMsg("Order successfully");
					responseStructure.setDescription("Product order succesfully ");
					responseStructure.setData(productOrder);
				} else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setStatusMsg("Product Order not successfully");
					responseStructure.setDescription("Product is not available");
					responseStructure.setData(null);
				}
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMsg("Product Order not successfully");
				responseStructure.setDescription("Product is not available");
				responseStructure.setData(null);
			}
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMsg("Session Expired");
			responseStructure.setDescription("Please Login Again");
			responseStructure.setData(null);

		}
		return responseStructure;
	}
}
