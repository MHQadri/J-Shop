package com.jshop.jshopspringbootproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductOrder {

	@Id
	private long orderId;
	@UpdateTimestamp
	@Column(name = "bookingDate")
	private LocalDateTime bookingDateTime;
	@UpdateTimestamp
	private LocalDate deliverdDate;
	private int qauntity;
	private double price;
	private String deliveredAddress;
	
	@ManyToOne
	@JoinColumn(name ="productId")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

}
