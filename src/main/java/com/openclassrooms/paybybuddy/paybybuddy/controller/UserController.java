package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	private static final Logger logger = LogManager.getLogger(UserController.class);

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public static <T> List<T> optionalToList(Optional<T> optional) {
		return optional.map(Collections::singletonList)
				.orElse(Collections.emptyList());
	}

	/**
	 * Created new user
	 * @RequestBody userEntity
	 */
	@PostMapping
	public  ResponseEntity<UserEntity> save(@RequestBody UserEntity userEntity)  {
		logger.info("save, RequestBody: UserEntity={} ", userEntity );
		userService.save(userEntity);
		return new ResponseEntity<>(userEntity,HttpStatus.OK);	 
	}

	/**
	 *Get a list of all user
	 * @return List of all users
	 */
	@GetMapping
	public ResponseEntity <List<UserDTO>> getAll(){
		logger.info("getAll");
		List<UserDTO> result = userService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * Get one user by id
	 * @Param Integer : id
	 * @return One user  
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<UserDTO> getOne (@PathVariable Integer id){
		logger.info("getOneById, params: id={}", id);
		Optional<UserDTO> optionalValue = userService.getUserById(id);
		if(optionalValue.isPresent()) {
			return new ResponseEntity<>(optionalValue.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}


	/**
	 * Get one user by mail
	 * @Param String : user_mail
	 * @return One user  
	 */
	@GetMapping("/mail/{user_mail}")
	public ResponseEntity<UserDTO> getOne (@PathVariable String user_mail){
		logger.info("getOneByMail, params: user_mail={}", user_mail);
		Optional<UserDTO> optionalValue = userService.getUserByMail(user_mail);
		List<UserDTO> resultList = optionalToList(optionalValue);
		if(!resultList.isEmpty()) {
			return new ResponseEntity<>(resultList.get(0),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}


	
}
