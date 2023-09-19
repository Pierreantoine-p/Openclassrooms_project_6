package com.openclassrooms.paymybuddy.paymybuddy.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paymybuddy.paymybuddy.model.UserModel;
import com.openclassrooms.paymybuddy.paymybuddy.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	
    private static final Logger logger = LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
		this.userService = userService;
	}
    
    /**
	 *Get a list of all user
	 * @return List of all users
	 */
	@GetMapping
	public ResponseEntity <List<UserModel>> getAll(){
		logger.info("getAll");
			return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
 	}
	
/*
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getOne (@PathVariable Integer id){
		logger.info("getOne, params: id={}", id);
		List<UserModel> user = userService.getUserById(id);
		if(!user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
*/

}
