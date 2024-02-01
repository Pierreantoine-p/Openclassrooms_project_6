package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.sql.Timestamp;
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
import org.springframework.test.context.ActiveProfiles;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.service.TransactionService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class TransactionRepositoryTest {
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
		List<TransactionEntity> listTransaction = new ArrayList<TransactionEntity>();
		
		userFirst = new UserEntity();
		userSecond = new UserEntity();

		userFirst.setUserName("Marty Mcfly");
		userFirst.setUserMail("marty@example.com");
		userFirst.setUserPassword("88miles");
		userFirst.setTransactions(listTransaction);

		userSecond.setUserName("Doc. Emmet Brown");
		userSecond.setUserMail("Emmet@example.com");
		userSecond.setUserPassword("88miles");

		userService.save(userFirst);
		userService.save(userSecond);

		TransactionEntity expected = transactionService.save(userFirst.getUserId(), userSecond.getUserId(), 10, 5, 15, "Sample_Description");
		
		listTransaction.add(expected);

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

		TransactionEntity transactionEntity = new TransactionEntity();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		transactionEntity.setUserIdOwner(userSecond.getUserId());
		transactionEntity.setUserIdTransaction(userFirst.getUserId());
		transactionEntity.setTransactionDate(timestamp);
		transactionEntity.setTransactionSum(20);
		transactionEntity.setTransactionFee(5);
		transactionEntity.setTransactionSumFinal(25);
		transactionEntity.setTransactionDescription("Sample_Description");
		TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);

		assertEquals(userSecond.getUserId(), savedTransaction.getUserIdOwner());
		assertEquals(userFirst.getUserId(), savedTransaction.getUserIdTransaction());
		assertEquals(timestamp, savedTransaction.getTransactionDate());
		assertEquals(20, savedTransaction.getTransactionSum(),DELTA);
		assertEquals(5, savedTransaction.getTransactionFee(), DELTA);
		assertEquals(25, savedTransaction.getTransactionSumFinal(), DELTA);
		assertEquals("Sample_Description", savedTransaction.getTransactionDescription());

	}

	@Test
	@Order(2)
	public void testGetAllById() {
		List<TransactionEntity> expectedTransaction = transactionRepository.findByUserIdOwner(userFirst.getUserId());
		 if (!expectedTransaction.isEmpty()) {
		        assertEquals(userFirst.getUserId(), expectedTransaction.get(0).getUserIdOwner());
		    } else {
			    assertNull(expectedTransaction.get(0));

		    }
		}
}
