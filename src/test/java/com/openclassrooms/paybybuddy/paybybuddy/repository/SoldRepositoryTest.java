package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SoldRepositoryTest {
	
	@Autowired
	private SoldRepository soldRepository;
	
	@Test
	@Order(2)
	public void testSave() {
		SoldEntity sold = new SoldEntity();
		Integer soldId = 1;
		
		sold.setUserId(soldId);
		sold.setSoldSum(0);
		
		SoldEntity expectedSold = soldRepository.save(sold);
		assertEquals(soldId, expectedSold.getUserId());
	}
	
}
