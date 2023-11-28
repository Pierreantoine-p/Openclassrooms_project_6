package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertEquals;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class SoldServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private SoldService soldService;

	private static final double DELTA = 1e-15;
	
	private Integer firstId = 1;
	private Integer secondId = 2;
	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";

	private UserEntity userFirst;
	private UserEntity userSecond;

	private SoldEntity	soldUserFirst;


	@BeforeAll
	@Transactional
	void createUser() {

		  UserEntity userOne = new UserEntity();
		  userOne.setUserName("toto");
		  userOne.setUserMail("toto@example.com");
		  userOne.setUserPassword("123");
		  
	        userService.save(userOne);

		  
	}

	@Test
	@Order(1)
	public void testGetSoldById() {	
		SoldEntity expectedSold = soldService.getById(1);
		assertEquals(1,expectedSold.getUserId() );
	}
	
	@Test
	@Order(2)
	public void testSave() {
		
		 UserEntity userTwo = new UserEntity();
		  
		  userTwo.setUserName("titi");
		  userTwo.setUserMail("titi@example.com");
		  userTwo.setUserPassword("123");
		  
	        userService.save(userTwo);
	        
		SoldEntity expectedSold = soldService.getById(2);
		assertEquals(2, expectedSold.getSoldId());
	}

	@Test
	@Order(3)
	public void testUpdate() {

		double soldAmount = 40.0;
		
		soldService.update(1,soldAmount);
		SoldEntity expectedSold = soldService.getById(1);
		assertEquals(1, expectedSold.getUserId());

	}


}