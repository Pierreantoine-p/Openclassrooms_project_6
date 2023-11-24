package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;



@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;


	private static final Logger logger = LogManager.getLogger(UserController.class);

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Get auth user by mail and password
	 * @Param String : userMail
	 * @Param String : userPassword
	 * @return One user or bad request 
	 */
	@GetMapping("/{userMail}/{userPassword}")
	public ResponseEntity<UserDTO> auth (@PathVariable String userMail, @PathVariable String userPassword){
		logger.info("getOneByMail, params: userMail={}", userMail, "userPassword={}",userPassword);

		UserDTO optionalValue = userService.auth(userMail, userPassword);

		if(optionalValue != null ) {
			logger.info("result: result={}", optionalValue );
			return new ResponseEntity<>(optionalValue ,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


}
