package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;

@DirtiesContext
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RelationServiceTest {


	
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
		
		RelationEntity relation2  = new RelationEntity();

		relation2.setRelationId(secondId);
		relation2.setUser(userSecond);
		relation2.setUserFkIdOwnerRelation(userFirst.getUserId());
		relationService.save(relation2);
		
		assertEquals(userSecond.getUserId(), relation2.getUser().getUserId());
	}
	
	@Test
	@Order(2)
	public void testGetRelationsById() {

		List<RelationDTO> relationList = relationService.getRelationsById(secondId);
        assertTrue(relationList.size() > 0);
        assertEquals(secondId, relationList.get(0).getUserIdOwner());

	}
	
}
