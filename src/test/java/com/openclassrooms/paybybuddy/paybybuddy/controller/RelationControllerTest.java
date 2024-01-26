package com.openclassrooms.paybybuddy.paybybuddy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;
import com.openclassrooms.paybybuddy.paybybuddy.service.RelationService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;

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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SoldRepository soldRepository;

	@Autowired
	private RelationRepository relationRepository;


	private UserEntity userFirst;
	private UserEntity userSecond;
	private RelationEntity	relationUserFirst;
	private RelationEntity	relationUserSecond;
	//private List<RelationEntity> listRelationUserFirst;
	//private List<RelationEntity> listRelationUserSecond;

	private RelationEntity relation  = new RelationEntity();

	@BeforeAll
	void createUser() {

		//listRelationUserFirst = new ArrayList<RelationEntity>();
		//listRelationUserSecond = new ArrayList<RelationEntity>();


		userFirst = new UserEntity();
		userSecond = new UserEntity();

		userFirst.setUserName("Marty Mcfly");
		userFirst.setUserMail("marty@example.com");
		userFirst.setUserPassword("88miles");
		//userFirst.setRelation(listRelationUserFirst);

		userSecond.setUserName("Doc. Emmet Brown");
		userSecond.setUserMail("Emmet@example.com");
		userSecond.setUserPassword("88miles");
		//userFirst.setRelation(listRelationUserSecond);

		userService.save(userFirst);
		userService.save(userSecond);

		
		relationUserFirst  = new RelationEntity();

		relationUserFirst.setUser(userSecond);
		relationUserFirst.setUserFkIdOwnerRelation(userFirst.getUserId());

		relationService.save(relationUserFirst);
		//listRelationUserFirst.add(relationUserFirst);

	}

	@AfterAll
	void cleanUp() {
		soldRepository.deleteAll();
		relationRepository.deleteAll();
		userRepository.deleteAll();

	}
	
	@Test
	@Order(1)
	public void testSave() {
		HttpStatus expectedResponse = HttpStatus.OK;
		//RelationEntity relation  = new RelationEntity();
		relationUserSecond  = new RelationEntity();

		
		relationUserSecond.setUser(userFirst);
		relationUserSecond.setUserFkIdOwnerRelation(userSecond.getUserId());
		ResponseEntity<RelationEntity> relationEntity = relationController.save(relationUserSecond);
		
		assertEquals(expectedResponse, relationEntity.getStatusCode());
		
		
	}
	
	@Test
	@Order(2)
	public void testGetRelationsById() {
		HttpStatus expectedResponse = HttpStatus.OK;

		ResponseEntity<List<RelationDTO>> relationDTO= relationController.getAllRelationById(userSecond.getUserId());
		assertEquals(expectedResponse, relationDTO.getStatusCode());

	}
	
}
