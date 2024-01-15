package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertTrue;
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
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RelationServiceTest {

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
	private List<RelationEntity> listRelationUserFirst;

	@BeforeAll
	void createUser() {

		listRelationUserFirst = new ArrayList<RelationEntity>();

		userFirst = new UserEntity();
		userSecond = new UserEntity();

		userFirst.setUserName("Marty Mcfly");
		userFirst.setUserMail("marty@example.com");
		userFirst.setUserPassword("88miles");
		userFirst.setRelation(listRelationUserFirst);

		userSecond.setUserName("Doc. Emmet Brown");
		userSecond.setUserMail("Emmet@example.com");
		userSecond.setUserPassword("88miles");

		userService.save(userFirst);
		userService.save(userSecond);

		relationUserFirst  = new RelationEntity();

		relationUserFirst.setUser(userSecond);
		relationUserFirst.setUserFkIdOwnerRelation(userFirst.getUserId());

		relationService.save(relationUserFirst);
		listRelationUserFirst.add(relationUserFirst);
		
		

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

		RelationEntity relation2  = new RelationEntity();

		relation2.setUser(userFirst);
		relation2.setUserFkIdOwnerRelation(userSecond.getUserId());

		relationService.save(relation2);

		assertEquals(userSecond.getUserId(), relation2.getUserFkIdOwnerRelation());
	}

	@Test
	@Order(2)
	public void testGetRelationsById() {

		List<RelationDTO> relationList = relationService.getRelationsById(userFirst.getUserId());
		assertTrue(relationList.size() > 0);

	}

}
