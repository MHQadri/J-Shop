package com.jshop.jshopspringbootproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jshop.jshopspringbootproject.dto.Admin;
import com.jshop.jshopspringbootproject.dto.Product;
import com.jshop.jshopspringbootproject.dto.ProductOwner;
import com.jshop.jshopspringbootproject.repository.AdminRepository;
import com.jshop.jshopspringbootproject.repository.ProductOwnerRepository;
import com.jshop.jshopspringbootproject.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author Mohd Hashim we perform here sign-up and login code of admin
 */
@Repository
public class AdminDao {

	int adminId = 0;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ProductOwnerRepository productOwnerRepository;

	@Autowired
	private ProductOwnerDao ownerDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductRepository productRepository;

	/*
	 * sign-up code for admin save method
	 */
	public Admin saveAdminDao(Admin admin) {

		return adminRepository.save(admin);
	}

	/*
	 * login with admin
	 */
	public Admin loginWithAdminDao(String email) {
		Admin admin = adminRepository.findByAdminEmail(email);
		if (admin != null) {
			adminId = admin.getAdminId();
		}
		return admin;
	}

	/*
	 * getAllProductOwnerAdminDao
	 */
	public List<ProductOwner> getAllProductOwnerAdminDao() {

		return ownerDao.getAllProductOwner();
	}

	/*
	 * getProductOwnerById
	 */
	public ProductOwner getProductOwnerById(int productOwnerId) {
		return ownerDao.getProductOwnerById(productOwnerId);
	}

	/*
	 * verify product owner from no to yes and unverified from yes to no
	 */
	public ProductOwner verifyProductOwnerDao(int productOwnerId) {

		ProductOwner productOwner = getProductOwnerById(productOwnerId);
		/*
		 * Get admin data by adminId
		 */
		Optional<Admin> optional = adminRepository.findById(adminId);
		Admin admin = null;
		if (optional.isPresent()) {
			admin = optional.get();
		}

		if (productOwner != null) {
			if (productOwner.getAdminVerify().equalsIgnoreCase("no")) {

				productOwner.setAdminVerify("yes");
				productOwner.setAdmins(admin);
			} else {
				admin = null;
				productOwner.setAdmins(admin);
				productOwner.setAdminVerify("no");

			}
			productOwnerRepository.save(productOwner);
		}

		return productOwner;
	}

	/*
	 * verify Product by admin
	 */
	public Product verifyProductDetailsByAdminDao(int productId) {

		Product product = productDao.getProductByIdDao(productId);

		if (product != null) {

			if (product.getProductVerified().equalsIgnoreCase("no")) {

				product.setProductVerified("yes");
			} else {
				product.setProductVerified("no");
			}
		}

		productRepository.save(product);
		return product;

	}

}
