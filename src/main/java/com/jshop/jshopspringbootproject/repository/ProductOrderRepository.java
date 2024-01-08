package com.jshop.jshopspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringbootproject.dto.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{

}
