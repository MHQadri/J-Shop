package com.jshop.jshopspringbootproject.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {

	@Id
	private int userId;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userPassword;

	@OneToMany(mappedBy = "user")
	private List<UserCart> userCarts;
}
