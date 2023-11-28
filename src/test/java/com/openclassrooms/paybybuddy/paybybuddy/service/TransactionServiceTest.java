package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;

	private static final double DELTA = 1e-15;

	private Integer firstId = 1;
	private Integer secondId = 2;
	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";

	private UserEntity userFirst;
	private UserEntity userSecond;

	private String description = "Sample_Description";
	private double sum = 10;
	private double fee = 5;
	private double sumFinal = 15;
	

	@BeforeAll
	@Transactional
	void createUser() {

		userFirst = new UserEntity();
		userSecond = new UserEntity();
		
		List<TransactionEntity> listTransactionUserFirst = new ArrayList<TransactionEntity>();
		List<TransactionEntity> listTransactionUserSecond = new ArrayList<TransactionEntity>();
		List<RelationEntity> listRelationUserFirst = new ArrayList<RelationEntity>();
		List<RelationEntity> listRelationUserSecond = new ArrayList<RelationEntity>();
		SoldEntity soldUserFirst = new SoldEntity();
		SoldEntity soldUserSecond = new SoldEntity();

/*
		userFirst.setUserId(firstId);
		userFirst.setUserName("new");
		userFirst.setUserMail(userFirstMail);
		userFirst.setUserPassword("123456");
		userFirst.setRelation(listRelationUserFirst);
		userFirst.setSold(soldUserFirst);
		userFirst.setTransactions(listTransactionUserFirst);

		userSecond.setUserId(secondId);
		userSecond.setUserName("toto");
		userSecond.setUserMail(userSecondMail);
		userSecond.setUserPassword("123456");
		userSecond.setRelation(listRelationUserSecond);
		userSecond.setSold(soldUserSecond);
		userSecond.setTransactions(listTransactionUserSecond);

		userService.save(userFirst);
		userService.save(userSecond);
				*/
		
		  UserEntity userOne = new UserEntity();
		  UserEntity userTwo = new UserEntity();

		  
		  userOne.setUserName("toto");
		  userOne.setUserMail("toto@example.com");
		  userOne.setUserPassword("123");
		  
		  userTwo.setUserName("titi");
		  userTwo.setUserMail("titi@example.com");
		  userTwo.setUserPassword("123");
		  
	        userService.save(userOne);
	        userService.save(userTwo);
	        
		
		transactionService.save(1, 2, 10, 5, 15, "description");
	
	}

		@Test
		@Order(1)
		public void testSave() {

			TransactionEntity savedTransaction = transactionService.save(2, 1, 20, 5, 20, "description");

			assertEquals(2, savedTransaction.getUserIdOwner(), DELTA);
			assertEquals(1, savedTransaction.getUserIdTransaction(), DELTA);
			assertEquals(20, savedTransaction.getTransactionSum(), DELTA);
			assertEquals(5, savedTransaction.getTransactionFee(), DELTA);
			assertEquals(20, savedTransaction.getTransactionSumFinal(), DELTA);
			assertEquals("description", savedTransaction.getTransactionDescription());

		}

		@Test
		@Order(2)
		public void testGetAllById() {
			
			List<TransactionEntity> expectedTransaction = transactionService.getAllById(1);
			assertTrue(expectedTransaction.size() > 0);
		}

	}
