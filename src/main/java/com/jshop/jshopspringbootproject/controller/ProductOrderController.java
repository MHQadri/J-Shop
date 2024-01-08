package com.jshop.jshopspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshop.jshopspringbootproject.dto.ProductOrder;
import com.jshop.jshopspringbootproject.response.ResponseStructure;
import com.jshop.jshopspringbootproject.service.ProductOrderService;

@RestController
@RequestMapping("/order")
public class ProductOrderController {

	@Autowired
	private ProductOrderService service;

	/*
	 * Save productOrderMthod
	 */
	@PutMapping("/productOrder/{productId}/{qauntity}/{address}")
	public ResponseStructure<ProductOrder> saveProductOrderService(@PathVariable int productId,
			@PathVariable int qauntity, @PathVariable String address) {

		return service.saveProductOrderService(productId, qauntity, address);
	}
}
