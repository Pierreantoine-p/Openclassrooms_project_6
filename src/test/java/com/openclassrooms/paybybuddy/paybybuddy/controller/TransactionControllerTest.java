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
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;
import com.openclassrooms.paybybuddy.paybybuddy.service.TransactionService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionControllerTest {
	
	@Autowired
	private TransactionController transactionController;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;

	@Autowired
	private SoldRepository soldRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private static final double DELTA = 1e-15;
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

		transactionService.save(userFirst.getUserId(), userSecond.getUserId(), 10, 5, 15, "Sample_Description");
	
	}
	
	@AfterAll
	void cleanUp() {
		soldRepository.deleteAll();
		transactionRepository.deleteAll();
		userRepository.deleteAll();
	}

	
	@Test
	@Order(1)
	public void testGetAllById() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<List<TransactionEntity>> transactionEntity = transactionController.getAllById(userFirst.getUserId());
		assertEquals(expectedResponse,transactionEntity.getStatusCode());

	}
}
