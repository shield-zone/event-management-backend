package com.shield.eventmanagement.payload;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationsErrorResponse {

	private Map<String, String> errors;
	private HttpStatus httpStatus;
	
}
