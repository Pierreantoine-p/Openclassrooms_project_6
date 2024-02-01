package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired
	private UserService userService;

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
	public void testGetAll() {
		Iterable<UserDTO> getAllUser = userService.getAll();
		List<UserDTO> userList = new ArrayList<>();
		getAllUser.forEach(userList::add);
		assertTrue(userList.size() > 0);
	}

	@Test
	@Order(2)
	public void testGetUserById() {
		System.out.println(userFirst);
		Optional<UserDTO> userOptional = userService.getUserById(userFirst.getUserId());
		assertEquals(userFirst.getUserId(),userOptional.get().getUserId() );
	}

	@Test
	@Order(3)
	public void testGetUserByMail() {
		Optional<UserDTO> userOptional = userService.getUserByMail("marty@example.com");
		assertEquals("marty@example.com",userOptional.get().getUserMail() );
	}

	@Test
	@Order(4)
	public void testSave() {

		UserEntity savedUser = new UserEntity();

		savedUser.setUserName("Biff Tannen");
		savedUser.setUserMail("Biff@example.com");
		savedUser.setUserPassword("88miles");

		UserEntity expected = userService.save(savedUser);

		assertEquals("Biff Tannen", expected.getUserName());
		assertEquals("Biff@example.com", expected.getUserMail());

	}


}
