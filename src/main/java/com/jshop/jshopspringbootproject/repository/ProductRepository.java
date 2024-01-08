package com.jshop.jshopspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringbootproject.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
