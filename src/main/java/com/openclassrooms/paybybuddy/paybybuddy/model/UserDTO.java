package com.openclassrooms.paybybuddy.paybybuddy.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	private Integer userId;

	private String userName;

	private String userMail;

	private String userPassword;
	
	public UserDTO(Integer userId, String userName, String userMail, String userPassword) {
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userPassword = userPassword;
	}
}
