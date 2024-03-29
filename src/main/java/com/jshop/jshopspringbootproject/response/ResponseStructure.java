package com.jshop.jshopspringbootproject.response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {

	private int statusCode;
	private String statusMsg;
	private String description;
	private T data;
}
