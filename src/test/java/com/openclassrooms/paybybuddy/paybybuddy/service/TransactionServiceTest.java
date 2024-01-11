package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

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
	private List<TransactionEntity> listTransaction = new ArrayList<TransactionEntity>();



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
		transactionRepository.deleteAll();
		userRepository.deleteAll();
	}

		@Test
		@Order(1)
		public void testSave() {

			TransactionEntity savedTransaction = transactionService.save(userSecond.getUserId(), userFirst.getUserId(), 20, 5, 20, "Sample_Description");

			assertEquals(userSecond.getUserId(), savedTransaction.getUserIdOwner(), DELTA);
			assertEquals(userFirst.getUserId(), savedTransaction.getUserIdTransaction(), DELTA);
			assertEquals(20, savedTransaction.getTransactionSum(), DELTA);
			assertEquals(5, savedTransaction.getTransactionFee(), DELTA);
			assertEquals(20, savedTransaction.getTransactionSumFinal(), DELTA);
			assertEquals("Sample_Description", savedTransaction.getTransactionDescription());

		}

		@Test
		@Order(2)
		public void testGetAllById() {

			TransactionEntity transaction = transactionService.save(userFirst.getUserId(), userSecond.getUserId(), 10, 5, 15, "Sample_Description");
			listTransaction.add(transaction);
			
			userFirst.setTransactions(listTransaction);

			List<TransactionEntity> expectedTransaction = transactionService.getAllById(userFirst.getUserId());

			assertTrue(expectedTransaction.size() > 0);
		}

	}
