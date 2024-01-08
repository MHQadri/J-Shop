package com.jshop.jshopspringbootproject.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOwner {

	@Id
	@Column(name = "ownerid")
	private int productOwnerId;

	@Column(name = "ownername")
	private String productOwnerName;

	@Column(name = "owneremail")
	private String productOwnerEmail;

	@Column(name = "ownerpassword")
	private String productOwnerPassword;

	@Column(name = "adminverify")
	private String adminVerify;

	@ManyToOne
	private Admin admins;
	
	@OneToMany(mappedBy = "productOwner")
	@JsonIgnore
	private List<Product> products;
}
