package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



@ActiveProfiles
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

	


	@Autowired
	private UserService userService;
/*
	@Autowired
	private RelationService relationService;

	@Autowired
	private SoldService soldService;

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionRepository ;

	@Autowired
	private UserRepository userRepository ;

	@Autowired
	private SoldRepository soldRepository ;

	@Autowired
	private RelationRepository relationRepository ;
	
*/
	
	@BeforeEach
	@Transactional
	void createUser() {

		Integer userId = 1;
		Integer userIdTwo = 2;

		String userMail1 = "new@mail.fr";
		String userMail2 = "toto@mail.fr";

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		SoldEntity sold1 = new SoldEntity();
		SoldEntity sold2 = new SoldEntity();

		List<TransactionEntity> transaction1 = new ArrayList<TransactionEntity>();
		List<TransactionEntity> transaction2 = new ArrayList<TransactionEntity>();
		List<RelationEntity> relationAdd1 = new ArrayList<RelationEntity>();
		List<RelationEntity> relationAdd2 = new ArrayList<RelationEntity>();

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setUserId(userId);
		userEntity1.setUserName("new");
		userEntity1.setUserMail(userMail1);
		userEntity1.setUserPassword("123456");
		userEntity1.setRelation(relationAdd1);
		userEntity1.setSold(sold1);
		userEntity1.setTransactions(transaction1);

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setUserId(userIdTwo);
		userEntity2.setUserName("toto");
		userEntity2.setUserMail(userMail2);
		userEntity2.setUserPassword("123456");
		userEntity2.setRelation(relationAdd2);
		userEntity2.setSold(sold2);
		userEntity2.setTransactions(transaction2);

		//userService.save(userEntity1);
		//userService.save(userEntity2);

		RelationEntity relation1  = new RelationEntity();
		RelationEntity relation2  = new RelationEntity();


		relation1.setRelationId(1);
		relation1.setUser(userEntity1);
		relation1.setUserFkIdOwnerRelation(2);
		//relationAdd1.add(relation1);
		
		relation2.setRelationId(2);
		relation2.setUser(userEntity2);
		relation2.setUserFkIdOwnerRelation(1);
		//relationAdd2.add(relation2);

	
		
		//System.out.println("relationAdd1 : " + relationAdd1);
		//System.out.println("relationAdd2 : " + relationAdd2);
		
		//relationService.save(relation1);
		//relationService.save(relation2);

		sold1.setSoldId(userId);
		sold1.setSoldSum(55.10);
		sold1.setUserId(userId);

		sold2.setSoldId(userIdTwo);
		sold2.setSoldSum(23.65);
		sold2.setUserId(userIdTwo);

		//soldService.save(userId);
		//soldService.save(userIdTwo);


		TransactionEntity transactionEntity1 = new TransactionEntity();
		transactionEntity1.setTransactionId(userId);
		transactionEntity1.setTransactionDate(timestamp);
		transactionEntity1.setTransactionDescription("description");
		transactionEntity1.setTransactionFee(10);
		transactionEntity1.setTransactionSum(5);
		transactionEntity1.setTransactionSumFinal(15);
		transactionEntity1.setUserIdOwner(userId);
		transactionEntity1.setUserIdTransaction(userIdTwo);
		transaction1.add(transactionEntity1);

		TransactionEntity transactionEntity2 = new TransactionEntity();
		transactionEntity2.setTransactionId(userIdTwo);
		transactionEntity2.setTransactionDate(timestamp);
		transactionEntity2.setTransactionDescription("description");
		transactionEntity2.setTransactionFee(15);
		transactionEntity2.setTransactionSum(5);
		transactionEntity2.setTransactionSumFinal(20);
		transactionEntity2.setUserIdOwner(userIdTwo);
		transactionEntity2.setUserIdTransaction(userId);
		transaction2.add(transactionEntity2);

		//transactionService.save(userId, userIdTwo, 10, 5, 15, "description");
		//transactionService.save(userIdTwo, userId, 7.50, 3.50, 12, "description");
		
		System.out.println("userEntity1 : " + userEntity1);
		System.out.println("userEntity2 : " + userEntity2);
		System.out.println("relation1 : " + relation1);
		System.out.println("relation2 : " + relation2);

	}
	/*
	@AfterEach
	public void cleanUp() {
		transactionRepository.deleteAll();
		relationRepository.deleteAll();
		soldRepository.deleteAll();
		userRepository.deleteAll();
	}
	*/
	@Test
	@Order(1)
	public void testGetAll() {
		Iterable<UserDTO> getAllUser = userService.getAll();
		List<UserDTO> userList = new ArrayList<>();
		getAllUser.forEach(userList::add);
		System.out.println("Nom de zeus : " + userList);

		assertTrue(userList.size() > 0);
	}

	@Test
	@Order(2)
	public void testGetUserById() {
		Optional<UserDTO> userOptional = userService.getUserById(1);
		System.out.println("Nom de zeus : " + userOptional);

		assertEquals(1,userOptional.get().getUserId() );
	}

	@Test
	@Order(3)
	public void testGetUserByMail() {
		Optional<UserDTO> userOptional = userService.getUserByMail("new@mail.fr");
		System.out.println("Nom de zeus : " + userOptional);
		assertEquals("new@mail.fr",userOptional.get().getUserMail() );
	}

	@Test
	@Order(4)
	public void testSave() {

		List<RelationEntity> relation = new ArrayList<RelationEntity>();
		SoldEntity sold = new SoldEntity();
		Integer userId = 2;
		sold.setSoldSum(0);
		sold.setUserId(userId + 1 );

		List<TransactionEntity> transaction = new ArrayList<TransactionEntity>();
		UserEntity expectedUser = new UserEntity();
		expectedUser.setUserId(userId + 1);
		expectedUser.setUserName("tata");
		expectedUser.setUserMail("tata@mail.fr");
		expectedUser.setUserPassword("123456");
		expectedUser.setRelation(relation);
		expectedUser.setSold(sold);
		expectedUser.setTransactions(transaction);
		UserEntity savedUser = userService.save(expectedUser);

		assertEquals("tata", savedUser.getUserName());
		assertEquals("tata@mail.fr", savedUser.getUserMail());

	}

	/*
	 * 
#Configuration connection data
spring.datasource.url=jdbc:mysql://localhost:3306/paymybuddy?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=rootroot
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
	 */
}
