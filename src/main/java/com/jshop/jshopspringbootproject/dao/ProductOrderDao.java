package com.jshop.jshopspringbootproject.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.ProductOrder;
import com.jshop.jshopspringbootproject.repository.ProductOrderRepository;
import com.jshop.jshopspringbootproject.repository.ProductRepository;

@Repository
public class ProductOrderDao {

	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Product product;

	@Autowired
	private ProductOrder productOrder;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserDao userDao;

	/*
	 * Save productOrderMthod
	 */
	public ProductOrder saveProductOrderDao(int productId, int qauntity, String address) {

		Product product = productDao.getProductByIdDao(productId);
		int qaun=product.getProuductQauntity() - qauntity;
		if (product != null) {
			/*
			 * total price calculation
			 */
			double totolPrice = product.getProductPrice() * qauntity;

			/*
			 * random order id
			 */
			long orderId = (long) Math.floor(Math.random() * 900000000000L) + 100000000000L;

			/*
			 * delivered date
			 */
			LocalDate localDate = LocalDate.now();
			localDate = LocalDate.now().plusDays(3);

			productOrder.setOrderId(orderId);
			productOrder.setProduct(product);
			productOrder.setPrice(totolPrice);
			productOrder.setUser(userDao.getUser());
			productOrder.setDeliverdDate(localDate);
			productOrder.setQauntity(qauntity);
			productOrder.setDeliveredAddress(address);

			product.setProuductQauntity(qaun);

			productOrderRepository.save(productOrder);

		}
		return productOrder;
	}
}
