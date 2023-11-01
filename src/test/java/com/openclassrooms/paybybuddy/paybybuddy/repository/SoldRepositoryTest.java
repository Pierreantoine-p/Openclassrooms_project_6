package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.openclassrooms.paybybuddy.paybybuddy.service.SoldService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@DirtiesContext
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoldRepositoryTest {

	@Autowired
	private SoldRepository soldRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private SoldService soldService;

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

		userFirst = new UserEntity();
		userSecond = new UserEntity();
		List<TransactionEntity> transactionUserFirst = new ArrayList<TransactionEntity>();
		List<TransactionEntity> transactionUserSecond = new ArrayList<TransactionEntity>();
		List<RelationEntity> listRelationUserFirst = new ArrayList<RelationEntity>();
		List<RelationEntity> listRelationUserSecond = new ArrayList<RelationEntity>();
		SoldEntity soldUserSecond = new SoldEntity();

		userFirst.setUserId(firstId);
		userFirst.setUserName("new");
		userFirst.setUserMail(userFirstMail);
		userFirst.setUserPassword("123456");
		userFirst.setRelation(listRelationUserFirst);
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

		soldUserFirst =  soldService.save(userFirst.getUserId());

	}


	@Test
	@Order(1)
	public void testGetSoldById() {	
		SoldEntity expectedSold = soldRepository.findByUserId(secondId);
		assertEquals(secondId,expectedSold.getUserId() );
	}

	@Test
	@Order(2)
	public void testSave() {
		
		Integer soldId = 4;
		SoldEntity soldExpected = new SoldEntity();
		soldExpected.setSoldId(soldId);
		soldExpected.setUserId(soldId);
		soldExpected.setSoldSum(0);
		SoldEntity expectedSold = soldRepository.save(soldExpected);
		
		assertEquals(soldId, expectedSold.getUserId());
	}

	@Test
	@Order(3)
	public void testUpdate() {
		SoldEntity expectedSold = soldService.getById(secondId);
		double soldAmount = 20.0;
		expectedSold.setSoldSum(soldAmount);
		SoldEntity soldExpected = soldRepository.save(expectedSold);
	
		assertEquals(secondId, soldExpected.getUserId());

	}
}
