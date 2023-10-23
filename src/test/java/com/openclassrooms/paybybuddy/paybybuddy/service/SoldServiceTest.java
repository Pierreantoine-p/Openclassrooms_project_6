package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertEquals;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoldServiceTest {
	
	@Autowired
	private SoldService soldService;
	
	@Test
	@Order(1)
	public void testGetSoldById() {	
		
		Integer soldId = 3;
		soldService.save(soldId);
		
		SoldEntity expectedSold = soldService.getById(soldId);
		assertEquals(soldId,expectedSold.getUserId() );
	}
	
	@Test
	@Order(2)
	public void testSave() {
		Integer soldId = 1;
		SoldEntity expectedSold = soldService.save(soldId);
		assertEquals(soldId, expectedSold.getUserId());
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		Integer soldId = 1;
		double soldAmount = 10.0;
		SoldEntity existingSold = new SoldEntity();
		existingSold.setSoldId(soldId);
		existingSold.setSoldSum(20.0);
		Double expectedNewSum = soldAmount + existingSold.getSoldSum();
		
		soldService.update(soldId,soldAmount);

		assertEquals(soldId, existingSold.getUserId());
		assertEquals(expectedNewSum, existingSold.getSoldSum());

	}


}
