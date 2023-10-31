package com.openclassrooms.paybybuddy.paybybuddy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;

	 private static final double DELTA = 1e-15;

	@Test
	@Order(1)
	public void testSave() {
		TransactionEntity transactionEntity = new TransactionEntity();

		Integer idOwner = 1;
		Integer idTransaction = 2;
		double sum = 1;
		double fee = 1.5;
		double sumFinal = 2.5;
		String description = "Sample description";

		TransactionEntity savedTransaction = transactionService.save(idOwner, idTransaction, sum, fee, sumFinal, description);

		assertEquals(idOwner, savedTransaction.getUserIdOwner());
		assertEquals(idTransaction, savedTransaction.getUserIdTransaction());
		assertEquals(sum, savedTransaction.getTransactionSum(), DELTA);
		assertEquals(fee, savedTransaction.getTransactionFee(), DELTA);
		assertEquals(sumFinal, savedTransaction.getTransactionSumFinal(), DELTA);
		assertEquals(description, savedTransaction.getTransactionDescription());

	}
	
	@Test
	@Order(2)
	public void testGetAllById() {
		Integer transactionId = 1;
		List<TransactionEntity> expectedTransaction = transactionService.getAllById(transactionId);
        assertTrue(expectedTransaction.size() > 0);
	}
	
}
