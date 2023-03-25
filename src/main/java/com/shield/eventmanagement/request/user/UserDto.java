package com.shield.eventmanagement.request.user;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@NotEmpty
    private String userName;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;
		
	@NotEmpty
	private String role;
}
