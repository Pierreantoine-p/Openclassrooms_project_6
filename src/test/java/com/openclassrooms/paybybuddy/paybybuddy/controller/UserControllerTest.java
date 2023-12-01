package com.openclassrooms.paybybuddy.paybybuddy.controller;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SoldRepository soldRepository;

	private UserEntity userFirst;
	private UserEntity userSecond;


	@BeforeAll
	void createUser() {

		userFirst = new UserEntity();
		userSecond = new UserEntity();

		userFirst.setUserName("Marty Mcfly");
		userFirst.setUserMail("marty@example.com");
		userFirst.setUserPassword("88miles");

		userSecond.setUserName("Doc. Emmet Brown");
		userSecond.setUserMail("Emmet@example.com");
		userSecond.setUserPassword("88miles");

		userService.save(userFirst);
		userService.save(userSecond);

	}

	@AfterAll
	void cleanUp() {
		soldRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	public void testGetAll() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<List<UserDTO>> actualResponse = userController.getAll();
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	
	@Test
	@Order(2)
	public void testSave() {
		HttpStatus expectedResponse = HttpStatus.OK;
		
		UserEntity savedUser = new UserEntity();

		savedUser.setUserName("Biff Tannen");
		savedUser.setUserMail("Biff@example.com");
		savedUser.setUserPassword("88miles");

		 ResponseEntity<UserEntity> actualResponse = userController.save(savedUser);
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	@Test
	@Order(3)
	public void testGetOneById() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<UserDTO> actualResponse = userController.getOneById(userFirst.getUserId());
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	@Test
	@Order(4)
	public void testGetOneByMail() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<UserDTO> actualResponse = userController.getOneByMail(userFirst.getUserMail());
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	

}
