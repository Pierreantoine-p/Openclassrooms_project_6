package com.openclassrooms.paybybuddy.paybybuddy.repository;

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
import com.openclassrooms.paybybuddy.paybybuddy.service.RelationService;
import com.openclassrooms.paybybuddy.paybybuddy.service.UserService;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RelationRepositoryTest {

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
	@Transactional
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
		
		RelationEntity relation  = new RelationEntity();
		
		relation.setUser(userFirst);
		relation.setUserFkIdOwnerRelation(userSecond.getUserId());
		RelationEntity expected = relationRepository.save(relation);
		
		assertEquals(userSecond.getUserId(), expected.getUserFkIdOwnerRelation());
	}
	
	@Test
	@Order(2)
	public void testGetRelationsById() {

		List<RelationEntity> relationList = relationRepository.findAllByUserFkIdOwnerRelation(userFirst.getUserId());
        assertTrue(relationList.size() > 0);

	}
	
}
