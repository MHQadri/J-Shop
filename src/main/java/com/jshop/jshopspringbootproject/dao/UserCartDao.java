package com.jshop.jshopspringbootproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.UserCart;
import com.jshop.jshopspringbootproject.repository.UserCartRepository;

@Repository
public class UserCartDao {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserCartRepository cartRepository;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserCart userCart;

	/*
	 * add product in userCart
	 */
	public UserCart addProductInUserCart(int productQauntity, int productId) {

		Product product = productDao.getProductByIdDao(productId);

		if (product != null) {
			userCart.setProduct(product);
			userCart.setProductQauntity(productQauntity);
			userCart.setUser(userDao.getUser());

			return cartRepository.save(userCart);
		} else {
			return null;
		}

	}
}
