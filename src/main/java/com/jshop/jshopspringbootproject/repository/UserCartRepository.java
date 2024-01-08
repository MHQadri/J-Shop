package com.jshop.jshopspringbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jshop.jshopspringbootproject.dto.UserCart;

public interface UserCartRepository extends JpaRepository<UserCart, Integer> {

}
