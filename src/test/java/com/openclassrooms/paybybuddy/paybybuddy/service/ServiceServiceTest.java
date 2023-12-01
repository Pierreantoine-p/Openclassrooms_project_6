package com.openclassrooms.paybybuddy.paybybuddy.service;
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
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.TransfertModelDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceServiceTest {
	
	@Autowired
	private ServiceService serviceService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SoldRepository soldRepository;
	
	@Autowired
	private RelationRepository relationRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private SoldService soldService;

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
		relationRepository.deleteAll();
		transactionRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	
	@Test
	@Order(1)
	public void testTransfert() {
		
		TransfertModelDTO transfertModelDTO = new TransfertModelDTO();
		double amount = 40;

		soldService.update(userFirst.getUserId(),amount);
		
		transfertModelDTO.setUseridOwner(userFirst.getUserId());
		transfertModelDTO.setUserfkIdRelation(userSecond.getUserId());
		transfertModelDTO.setAmount(3.99);
		transfertModelDTO.setDescription("Sample_desciption");

		TransactionEntity transactionEntity = serviceService.transfert(transfertModelDTO);

		assertEquals(userFirst.getUserId(), transactionEntity.getUserIdOwner());

	}
	
}
