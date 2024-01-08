package com.jshop.jshopspringbootproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.repository.ProductOwnerRepository;

@Repository
public class ProductOwnerDao {

	int productOwnerId = 0;

	public int getProductOwnerId() {
		return productOwnerId;
	}

	public void setProductOwnerId(int productOwnerId) {
		this.productOwnerId = productOwnerId;
	}

	@Autowired
	private ProductOwnerRepository productOwnerRepository;

	/*
	 * productOwner Register
	 */
	public ProductOwner saveProductOwnerDao(ProductOwner productOwner) {

		productOwner.setAdminVerify("no");

		return productOwnerRepository.save(productOwner);
	}

	/*
	 * getProductOwnerByEmail
	 */
	public ProductOwner getProductOwnerByEmail(String email) {

		ProductOwner owner = productOwnerRepository.findByProductOwnerEmail(email);
		if (owner != null) {
			setProductOwnerId(owner.getProductOwnerId());
		}
		return owner;
	}

	/*
	 * getAllProductOwner
	 */
	public List<ProductOwner> getAllProductOwner() {

		return productOwnerRepository.findAll();
	}

	/*
	 * getProductOwnerById
	 */
	public ProductOwner getProductOwnerById(int productOwnerId) {
		Optional<ProductOwner> optional = productOwnerRepository.findById(productOwnerId);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			return null;
		}
	}

}
