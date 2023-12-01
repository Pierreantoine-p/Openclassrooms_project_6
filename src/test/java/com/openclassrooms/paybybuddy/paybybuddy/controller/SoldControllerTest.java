package com.openclassrooms.paybybuddy.paybybuddy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;
import com.openclassrooms.paybybuddy.paybybuddy.service.SoldService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoldControllerTest {

	@Autowired
	private SoldController soldController;
	
	@Autowired
	private UserService userService;

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
	public void testGetSoldById() {	
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<SoldEntity> soldEntity = soldController.getById(userFirst.getUserId());
		
		assertEquals(expectedResponse,soldEntity.getStatusCode() );
	}
	
	@Test
	@Order(2)
	public void testUpdate() {	
		double soldAmount = 20.0;
		HttpStatus expectedResponse = HttpStatus.OK;

		SoldController.update(userFirst.getUserId(),soldAmount);
	
		assertEquals(expectedResponse, SoldController.getById(userFirst.getUserId()).getStatusCode());
	}
	
}
