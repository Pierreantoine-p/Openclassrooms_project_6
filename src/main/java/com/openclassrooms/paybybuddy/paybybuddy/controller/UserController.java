package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.model.UserModel;
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
	 *Get a list of all user
	 * @return List of all users
	 */
	@GetMapping
	public ResponseEntity <List<UserModel>> getAll(){
		logger.info("getAll");
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}

	/**
	 * Get one user by id
	 * @Param Integer : id
	 * @return One user  
	 */
	
	@GetMapping("/id/{id}")
	public ResponseEntity<UserModel> getOne (@PathVariable Integer id){
		logger.info("getOneById, params: id={}", id);
		 Optional<UserModel> optionalValue = userService.getUserById(id);
		if(optionalValue.isPresent()) {
			System.out.println("here");
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
	public ResponseEntity<UserModel> getOne (@PathVariable String user_mail){
		System.out.println(" here");

		logger.info("getOneByMail, params: user_mail={}", user_mail);
		Optional<UserModel> optionalValue = userService.getUserByMail(user_mail);
		List<UserModel> resultList = optionalToList(optionalValue);
		if(!resultList.isEmpty()) {

			System.out.println("resultList.get(0)");
			return new ResponseEntity<>(resultList.get(0),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	/**
	 * Created new user
	 * @RequestBody userModel
	 */
	@PostMapping
	public  ResponseEntity<UserModel> save(@RequestBody UserModel userModel)  {
		logger.info("save, RequestBody: userModel={} ", userModel );
		userService.save(userModel);
		return new ResponseEntity<>(userModel,HttpStatus.OK);	 
	}

	/**
	 * Update a user
	 * @Param String : user_mail, 
	 * @return user update 
	 */
	@PutMapping
	public UserModel update(@RequestBody UserModel userModel)  {
		logger.info("update, params: mail={}, RequestBody: userModel={} ", userModel );
		userService.saveOrUpdate(userModel);
		return userModel;
	}

	/**
	 * Delete a person
	 * @Param String : mail, 
	 */
	@DeleteMapping("/{user_mail}")
	public ResponseEntity<String> delete (@PathVariable String user_mail)  {
		System.out.println("here");

		logger.info("delete, params: mail={}", user_mail);

		Optional<UserModel> optionalValue = userService.getUserByMail(user_mail);
		if(optionalValue.isPresent()) {
			System.out.println("here");
			userService.delete(user_mail);
			 return ResponseEntity.ok("Suppression effectuée");
		}else {
			 return ResponseEntity.notFound().build();
		}
	}
	
	//TODO: try put 
	/*
	@PutMapping("/delete/{user_mail}")
	public ResponseEntity<String> delete (@PathVariable String user_mail)  {
		System.out.println("here");

		logger.info("delete, params: mail={}", user_mail);

		Optional<UserModel> optionalValue = userService.getUserByMail(user_mail);
		if(optionalValue.isPresent()) {
			System.out.println("here");
			userService.delete(user_mail);
			 return ResponseEntity.ok("Suppression effectuée");
		}else {
			 return ResponseEntity.notFound().build();
		}
	}
	*/
	//TODO: try put 

}
