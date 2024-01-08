package com.jshop.jshopspringbootproject.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	private String adminName;
	private String adminEmail;
	private String adminPassword;

	@OneToMany(mappedBy = "admins")
	@JsonIgnore
	private List<ProductOwner> owners;
}
