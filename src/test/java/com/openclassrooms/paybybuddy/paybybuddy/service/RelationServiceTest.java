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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.PaybybuddyApplication;
import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = PaybybuddyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RelationServiceTest {


	
	@Autowired
	private RelationService relationService;
	
	@Autowired
	private UserService userService;
	
	private Integer userFirstId = 1;
	private Integer userSecondId = 2;

	private String userFirstMail = "new@mail.fr";
	private String userSecondMail = "toto@mail.fr";
	
	private UserEntity userEntity1;
	private UserEntity userEntity2;
	
	private RelationEntity	relation1;

	
	@BeforeAll
	@Transactional
	void createUser() {

		userEntity1 = new UserEntity();
		userEntity2 = new UserEntity();
		List<TransactionEntity> transaction1 = new ArrayList<TransactionEntity>();
		List<TransactionEntity> transaction2 = new ArrayList<TransactionEntity>();
		List<RelationEntity> relationAdd1 = new ArrayList<RelationEntity>();
		List<RelationEntity> relationAdd2 = new ArrayList<RelationEntity>();
		SoldEntity sold1 = new SoldEntity();
		SoldEntity sold2 = new SoldEntity();

		
		userEntity1.setUserId(userFirstId);
		userEntity1.setUserName("new");
		userEntity1.setUserMail(userFirstMail);
		userEntity1.setUserPassword("123456");
		userEntity2.setRelation(relationAdd1);
		userEntity1.setSold(sold1);
		userEntity1.setTransactions(transaction1);

		userEntity2.setUserId(userSecondId);
		userEntity2.setUserName("toto");
		userEntity2.setUserMail(userSecondMail);
		userEntity2.setUserPassword("123456");
		userEntity2.setRelation(relationAdd2);
		userEntity2.setSold(sold2);
		userEntity2.setTransactions(transaction2);
		
		userService.save(userEntity1);
		userService.save(userEntity2);
		
		
		RelationEntity relation1  = new RelationEntity();
	

		relation1.setRelationId(userFirstId);
		relation1.setUser(userEntity1);
		relation1.setUserFkIdOwnerRelation(userEntity2.getUserId());
		
		relationService.save(relation1);


		
		//userEntity1.getRelation().add(relation1);


	
		System.out.println("userEntity1 : " + userEntity1);

		System.out.println("userEntity2 : " + userEntity2);


	}
	@Test
	@Order(1)
	public void testSave() {
		
		RelationEntity relation2  = new RelationEntity();

		relation2.setRelationId(userSecondId);
		relation2.setUser(userEntity2);
		relation2.setUserFkIdOwnerRelation(userEntity1.getUserId());
		relationService.save(relation2);
		
		assertEquals(userEntity2.getUserId(), relation2.getUser().getUserId());
	}
	
	@Test
	@Order(2)
	public void testGetRelationsById() {

		List<RelationDTO> relationList = relationService.getRelationsById(userSecondId);
		System.out.println("relationList : " + relationList);
        assertTrue(relationList.size() > 0);
        assertEquals(userSecondId, relationList.get(0).getUserIdOwner());

	}
	
}
