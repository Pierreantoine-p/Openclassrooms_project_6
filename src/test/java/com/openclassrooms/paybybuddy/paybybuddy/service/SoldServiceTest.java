package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertEquals;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoldServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private SoldService soldService;

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
		SoldEntity expectedSold = soldService.getById(userFirst.getUserId());
		assertEquals(userFirst.getUserId(),expectedSold.getUserId() );
	}
	
	@Test
	@Order(2)
	public void testSave() {
		
		 UserEntity userThree = new UserEntity();
		  
		 userThree.setUserName("Biff Tannen");
		 userThree.setUserMail("Biff@example.com");
		  userThree.setUserPassword("88miles");
		  
	        userService.save(userThree);
	        
		SoldEntity expectedSold = soldService.getById(userThree.getUserId());
		assertEquals(userThree.getUserId(), expectedSold.getUserId());
	}

	@Test
	@Order(3)
	public void testUpdate() {

		double soldAmount = 40.0;
		
		soldService.update(userFirst.getUserId(),soldAmount);
		SoldEntity expectedSold = soldService.getById(userFirst.getUserId());
		assertEquals(userFirst.getUserId(), expectedSold.getUserId());

	}


}