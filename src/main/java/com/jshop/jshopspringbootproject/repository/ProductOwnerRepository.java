package com.jshop.jshopspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringbootproject.dto.ProductOwner;

public interface ProductOwnerRepository extends JpaRepository<ProductOwner, Integer> {

	public ProductOwner findByProductOwnerEmail(String email);

}
