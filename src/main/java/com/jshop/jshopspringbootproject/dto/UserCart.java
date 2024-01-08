package com.jshop.jshopspringbootproject.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private int productQauntity;

	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnore
	private User user;

	@OneToOne
	private Product product;

}
