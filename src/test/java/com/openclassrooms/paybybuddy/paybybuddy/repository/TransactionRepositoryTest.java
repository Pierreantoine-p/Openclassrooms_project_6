package com.openclassrooms.paybybuddy.paybybuddy.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionRepositoryTest {

	@Autowired
	private TransactionRepository transactionRepository;

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
			
			transactionEntity.setTransactionDate(null);
			transactionEntity.setTransactionDescription(description);
			transactionEntity.setTransactionFee(fee);
			transactionEntity.setTransactionId(idTransaction);
			transactionEntity.setTransactionSum(sum);
			transactionEntity.setTransactionSumFinal(sumFinal);
			transactionEntity.setUserIdOwner(idOwner);
			transactionEntity.setUserIdTransaction(idTransaction);

			TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);

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
			List<TransactionEntity> expectedTransaction = transactionRepository.findByUserIdOwner(transactionId);
	        assertTrue(expectedTransaction.size() > 0);
		}
		
}
