package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	private Integer firstId = 1;
	private Integer secondId = 2;

	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";
	
	private UserEntity userFirst;
	private UserEntity userSecond;
		
	@BeforeAll
	@Transactional
	void createUser() {
		
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
	        
	}
	
	@AfterEach
	@Transactional
    public void cleanUp() {
        userRepository.deleteAll();; 
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
		Optional<UserDTO> userOptional = userService.getUserById(firstId);
		assertEquals(firstId,userOptional.get().getUserId() );
	}

	@Test
	@Order(3)
	public void testGetUserByMail() {
		Optional<UserDTO> userOptional = userService.getUserByMail("toto@example.com");
		assertEquals("toto@example.com",userOptional.get().getUserMail() );
	}

	@Test
	@Order(4)
	public void testSave() {

		  UserEntity savedUser = new UserEntity();
		  
		  savedUser.setUserName("tata");
		  savedUser.setUserMail("tata@mail.com");
		  savedUser.setUserPassword("123");

	

	        userService.save(savedUser);

		assertEquals("tata", savedUser.getUserName());
		assertEquals("tata@mail.com", savedUser.getUserMail());

	}


}
