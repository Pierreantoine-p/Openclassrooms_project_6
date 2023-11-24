package com.openclassrooms.paybybuddy.paybybuddy.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.RelationService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

@DirtiesContext
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RelationControllerTest {

	@Autowired
	private RelationController relationController;
	@Autowired
	private RelationService relationService;
	
	@Autowired
	private UserService userService;
	
	private Integer firstId = 1;
	private Integer secondId = 2;

	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";
	
	private UserEntity userFirst;
	private UserEntity userSecond;
	
	private RelationEntity	relationUserFirst;

	
	@BeforeAll
	@Transactional
	void createUser() {

		userFirst = new UserEntity();
		userSecond = new UserEntity();
		List<TransactionEntity> transactionUserFirst = new ArrayList<TransactionEntity>();
		List<TransactionEntity> transactionUserSecond = new ArrayList<TransactionEntity>();
		List<RelationEntity> listRelationUserFirst = new ArrayList<RelationEntity>();
		List<RelationEntity> listRelationUserSecond = new ArrayList<RelationEntity>();
		SoldEntity soldUserFirst = new SoldEntity();
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
		
		relationUserFirst  = new RelationEntity();

		relationUserFirst.setRelationId(firstId);
		relationUserFirst.setUser(userFirst);
		relationUserFirst.setUserFkIdOwnerRelation(userSecond.getUserId());
		
		relationService.save(relationUserFirst);

	}
	
	@Test
	@Order(1)
	public void testSave() {
		HttpStatus expectedResponse = HttpStatus.OK;

		RelationEntity relation2  = new RelationEntity();

		relation2.setRelationId(secondId);
		relation2.setUser(userSecond);
		relation2.setUserFkIdOwnerRelation(userFirst.getUserId());
		ResponseEntity<RelationEntity> relationEntity = relationController.save(relation2);
		
		assertEquals(expectedResponse, relationEntity.getStatusCode());
	}
	@Test
	
	@Order(2)
	public void testGetRelationsById() {
		HttpStatus expectedResponse = HttpStatus.OK;

		ResponseEntity<List<RelationDTO>> relationDTO= relationController.getAllRelationById(secondId);
		assertEquals(expectedResponse, relationDTO.getStatusCode());

	}
}
