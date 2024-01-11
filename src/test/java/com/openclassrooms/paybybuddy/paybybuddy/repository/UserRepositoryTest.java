package com.openclassrooms.paybybuddy.paybybuddy.repository;

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
import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SoldRepository soldRepository;

	private UserEntity userFirst;
	private UserEntity userSecond;
	
	public UserDTO convertToDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userEntity.getUserId());
		userDTO.setUserName(userEntity.getUserName());
		userDTO.setUserMail(userEntity.getUserMail());
		userDTO.setUserPassword(userEntity.getUserPassword());
		return userDTO;
	}

	@BeforeAll
	void createUser() {

		userFirst = new UserEntity();
		userSecond = new UserEntity();

		userFirst.setUserId(1);
		userFirst.setUserName("Marty Mcfly");
		userFirst.setUserMail("marty@example.com");
		userFirst.setUserPassword("88miles");

		userSecond.setUserId(2);
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
		Iterable<UserEntity> getAllUser = userRepository.findAll();
		List<UserDTO> userDTO = new ArrayList<>();
		for(UserEntity userEntity : getAllUser) {
			userDTO.add(convertToDTO(userEntity));
		}
		
		assertTrue(userDTO.size() > 0);
	}
	

	@Test
	@Order(2)
	public void testGetUserById() {
		Optional<UserEntity> userOptional = userRepository.findById(userFirst.getUserId());
		
		assertEquals(userFirst.getUserId(),userOptional.get().getUserId() );
	}

	@Test
	@Order(3)
	public void testGetUserByMail() {
		Optional<UserEntity> userOptional = userRepository.findByuserMail(userFirst.getUserMail());

		assertEquals(userFirst.getUserMail(),userOptional.get().getUserMail() );
	}

	@Test
	@Order(4)
	public void testSave() {

		
		UserEntity savedUser = new UserEntity();

		savedUser.setUserName("Biff Tannen");
		savedUser.setUserMail("Biff@example.com");
		savedUser.setUserPassword("88miles");

		UserEntity expected = userRepository.save(savedUser);

		assertEquals("Biff Tannen", expected.getUserName());
		assertEquals("Biff@example.com", expected.getUserMail());

	}

}
