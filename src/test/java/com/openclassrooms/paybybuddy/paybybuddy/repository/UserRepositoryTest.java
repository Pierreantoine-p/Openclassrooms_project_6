package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
/*
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Order(1)
	public void testGetAll() {
		
		Iterable<UserEntity> getAllUser = userRepository.findAll();
		List<UserEntity> userList = new ArrayList<>();
		getAllUser.forEach(userList::add);
        assertTrue(userList.size() > 0);
	}
	
	@Test
	@Order(2)
	public void testGetUserById() {
		Integer userId = 2;
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		assertEquals(userId,userOptional.get().getUserId() );
		}
	
	@Test
	@Order(3)
	public void testGetUserByMail() {
		String userMail = "toto@mail.fr";
		Optional<UserEntity> userOptional = userRepository.findByuserMail(userMail);
		assertEquals(userMail,userOptional.get().getUserMail() );
	}
	
	@Test
	@Order(4)
	public void testSave() {
		
		List<RelationEntity> relation = new ArrayList<RelationEntity>();
		SoldEntity sold = new SoldEntity();
		Integer userId = 12;
		sold.setSoldSum(0);
		sold.setUserId(userId);

		List<TransactionEntity> transaction = new ArrayList<TransactionEntity>();
		UserEntity expectedUser = new UserEntity();
		expectedUser.setUserId(userId);
		expectedUser.setUserName("new");
		expectedUser.setUserMail("new@mail.fr");
		expectedUser.setUserPassword("123456");
		expectedUser.setRelation(relation);
		expectedUser.setSold(sold);
		expectedUser.setTransactions(transaction);
		UserEntity savedUser = userRepository.save(expectedUser);
		
		assertEquals("new", savedUser.getUserName());
		assertEquals("new@mail.fr", savedUser.getUserMail());

	}
	*/
}
