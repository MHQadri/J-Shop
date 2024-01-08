package com.jshop.jshopspringbootproject.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Product {

	@Id
	private int productId;
	private String productName;
	private double productPrice;
	private String productBrand;
	private int prouductQauntity;
	private String productVerified;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private ProductOwner productOwner;

}
