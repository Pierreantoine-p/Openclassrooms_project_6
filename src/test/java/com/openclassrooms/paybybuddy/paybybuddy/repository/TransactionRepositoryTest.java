package com.openclassrooms.paybybuddy.paybybuddy.repository;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.service.TransactionService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@DirtiesContext
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionRepositoryTest {
	
	@Autowired
	private TransactionRepository TransactionRepository;
	
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;

	private static final double DELTA = 1e-15;

	private Integer firstId = 1;
	private Integer secondId = 2;
	private Integer thirdId = 3;

	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";
	private String userThirdMail = "tata@mail.fr";


	private UserEntity userFirst;
	private UserEntity userSecond;
	private UserEntity userThird;

	private String description = "Sample_Description";
	private double sum = 10;
	private double fee = 5;
	private double sumFinal = 15;
	

	@BeforeAll
	@Transactional
	void createUser() {

		userFirst = new UserEntity();
		userSecond = new UserEntity();
		userThird = new UserEntity();

		
		List<TransactionEntity> listTransactionUserFirst = new ArrayList<TransactionEntity>();
		List<TransactionEntity> listTransactionUserSecond = new ArrayList<TransactionEntity>();
		List<TransactionEntity> listTransactionUserThird = new ArrayList<TransactionEntity>();
		List<RelationEntity> listRelationUserFirst = new ArrayList<RelationEntity>();
		List<RelationEntity> listRelationUserSecond = new ArrayList<RelationEntity>();
		List<RelationEntity> listRelationUserThird = new ArrayList<RelationEntity>();
		SoldEntity soldUserFirst = new SoldEntity();
		SoldEntity soldUserSecond = new SoldEntity();
		SoldEntity soldUserThird = new SoldEntity();

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
		
		userThird.setUserId(thirdId);
		userThird.setUserName("tata");
		userThird.setUserMail(userThirdMail);
		userThird.setUserPassword("123456");
		userThird.setRelation(listRelationUserThird);
		userThird.setSold(soldUserThird);
		userThird.setTransactions(listTransactionUserThird);

		userService.save(userFirst);
		userService.save(userSecond);
		userService.save(userThird);

				
		transactionService.save(userFirst.getUserId(), userSecond.getUserId(), sum, fee, sumFinal, description);
	
	}
	
	@Test
	@Order(1)
	public void testSave() {

		//TransactionEntity savedTransaction = TransactionRepository.save(firstId, secondId, sum, fee, sumFinal, description);
		
		TransactionEntity transactionEntity = new TransactionEntity();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		transactionEntity.setUserIdOwner(secondId);
		transactionEntity.setUserIdTransaction(thirdId);
		transactionEntity.setTransactionDate(timestamp);
		transactionEntity.setTransactionSum(sum);
		transactionEntity.setTransactionFee(fee);
		transactionEntity.setTransactionSumFinal(sumFinal);
		transactionEntity.setTransactionDescription(description);
		TransactionEntity savedTransaction = TransactionRepository.save(transactionEntity);

		assertEquals(secondId, savedTransaction.getUserIdOwner());
		assertEquals(thirdId, savedTransaction.getUserIdTransaction());
		assertEquals(timestamp, savedTransaction.getTransactionDate());
		assertEquals(sum, savedTransaction.getTransactionSum(),DELTA);
		assertEquals(fee, savedTransaction.getTransactionFee(), DELTA);
		assertEquals(sumFinal, savedTransaction.getTransactionSumFinal(), DELTA);
		assertEquals(description, savedTransaction.getTransactionDescription());

	}

	@Test
	@Order(2)
	public void testGetAllById() {
		
		List<TransactionEntity> expectedTransaction = TransactionRepository.findByUserIdOwner(firstId);


		assertEquals(firstId, expectedTransaction.get(0).getUserIdOwner());
	}
}
