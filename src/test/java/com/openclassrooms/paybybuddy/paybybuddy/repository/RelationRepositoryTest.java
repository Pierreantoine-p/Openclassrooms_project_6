package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RelationRepositoryTest {
/*
	@Autowired
	private RelationRepository relationRepository;
	
	@Test
	@Order(1)
	public void testSave() {
		
		List<RelationEntity> relation = new ArrayList<RelationEntity>();
		SoldEntity sold = new SoldEntity();
		List<TransactionEntity> transaction = new ArrayList<TransactionEntity>();

		UserEntity expectedUser = new UserEntity(1, "toto", "toto@mail.fr", "123456",relation, sold, transaction);
		
		RelationEntity expectedRelation = new RelationEntity();
		Integer userFkIdOwnerRelation = 2;
		expectedRelation.setUser(expectedUser);
		expectedRelation.setUserFkIdOwnerRelation(userFkIdOwnerRelation);
		relationRepository.save(expectedRelation);
		
		assertEquals(1, expectedRelation.getUser().getUserId());
		assertEquals(userFkIdOwnerRelation, expectedRelation.getUserFkIdOwnerRelation());
	}
	
	@Test
	@Order(2)
	public void testGetRelationsById() {
		Integer relationId = 1;
		List<RelationEntity> relationList = relationRepository.findAllByUserFkIdOwnerRelation(relationId);
        assertTrue(relationList.size() > 0);
	}
	*/
}