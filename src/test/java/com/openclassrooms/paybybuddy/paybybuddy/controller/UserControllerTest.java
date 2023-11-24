package com.openclassrooms.paybybuddy.paybybuddy.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@DirtiesContext
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

	@Autowired
	private UserController userController;
	
	@Autowired
	private UserService userService;

	private Integer firstId = 1;
	private Integer secondId = 2;

	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";
	
	private UserEntity userFirst;
	private UserEntity userSecond;
		
	@BeforeAll
	@Transactional
	void createUser() {
		userFirst = new UserEntity();
		userSecond = new UserEntity();
		
		List<TransactionEntity> transactionUserFirst = new ArrayList<TransactionEntity>();
		List<TransactionEntity> transactionUserSecond = new ArrayList<TransactionEntity>();
		
		List<RelationEntity> listRelationUserFirst = new ArrayList<RelationEntity>();
		List<RelationEntity> listRelationUserSecond = new ArrayList<RelationEntity>();
		
		SoldEntity soldUserFirst = new SoldEntity();
		SoldEntity soldUserSecond = new SoldEntity();

		userFirst.setUserId(firstId);
		userFirst.setUserName("new");
		userFirst.setUserMail(userFirstMail);
		userFirst.setUserPassword("123456");
		userSecond.setRelation(listRelationUserFirst);
		userFirst.setSold(soldUserFirst);
		userFirst.setTransactions(transactionUserFirst);

		userSecond.setUserId(secondId);
		userSecond.setUserName("toto");
		userSecond.setUserMail(userSecondMail);
		userSecond.setUserPassword("123456");
		userSecond.setRelation(listRelationUserSecond);
		userSecond.setSold(soldUserSecond);
		userSecond.setTransactions(transactionUserSecond);
		
		userService.save(userFirst);
		userService.save(userSecond);

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
		
		List<RelationEntity> relation = new ArrayList<RelationEntity>();
		SoldEntity sold = new SoldEntity();

		List<TransactionEntity> transaction = new ArrayList<TransactionEntity>();
		UserEntity expectedUser = new UserEntity();
		expectedUser.setUserName("tata");
		expectedUser.setUserMail("tata@mail.fr");
		expectedUser.setUserPassword("123456");
		expectedUser.setRelation(relation);
		expectedUser.setSold(sold);
		expectedUser.setTransactions(transaction);

		
		 ResponseEntity<UserEntity> actualResponse = userController.save(expectedUser);
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	@Test
	@Order(3)
	public void testGetOneById() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<UserDTO> actualResponse = userController.getOneById(firstId);
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	@Test
	@Order(4)
	public void testGetOneByMail() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<UserDTO> actualResponse = userController.getOneByMail(userFirstMail);
		
		assertEquals(expectedResponse,actualResponse.getStatusCode());

	}
	

}
