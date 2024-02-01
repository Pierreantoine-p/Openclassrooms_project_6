package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.service.SoldService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class SoldRepositoryTest {

	@Autowired
	private SoldRepository soldRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private SoldService soldService;

	@Autowired
	private UserRepository userRepository;


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
		SoldEntity expectedSold = soldRepository.findByUserId(userSecond.getUserId());
		assertEquals(userSecond.getUserId(),expectedSold.getUserId() );
	}

	@Test
	@Order(2)
	public void testSave() {

		UserEntity savedUser = new UserEntity();

		savedUser.setUserName("Biff Tannen");
		savedUser.setUserMail("Biff@example.com");
		savedUser.setUserPassword("88miles");

		 userService.save(savedUser);
		
		 SoldEntity expected = soldService.getById(savedUser.getUserId());

		assertEquals(savedUser.getUserId(), expected.getUserId());
	}

}
