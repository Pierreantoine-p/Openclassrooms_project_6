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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoldServiceTest {

	Integer userId = 1;
	Integer userIdTwo = 2;

	String userMail1 = "new@mail.fr";
	String userMail2 = "toto@mail.fr";

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());


	@Autowired
	private UserService userService;

	@Autowired
	private RelationService relationService;

	@Autowired
	private SoldService soldService;

	@Autowired
	private TransactionService transactionService;



	@Test
	@Order(1)
	public void testGetSoldById() {	
		SoldEntity expectedSold = soldService.getById(1);
		System.out.println("expectedSold getID : " + expectedSold);

		assertEquals(userIdTwo,expectedSold.getUserId() );
	}

	@Test
	@Order(2)
	public void testSave() {
		Integer soldId = 3;
		SoldEntity expectedSold = soldService.save(soldId);
		System.out.println("expectedSold save : " + expectedSold);
		assertEquals(soldId, expectedSold.getUserId());
	}

	@Test
	@Order(3)
	public void testUpdate() {

		double soldAmount = 10.0;
		SoldEntity existingSold = new SoldEntity();
		existingSold.setSoldId(userId);
		existingSold.setSoldSum(20.0);
		Double expectedNewSum = soldAmount + existingSold.getSoldSum();

		soldService.update(userId,soldAmount);

		assertEquals(userId, existingSold.getUserId());
		assertEquals(expectedNewSum, existingSold.getSoldSum());

	}


}
