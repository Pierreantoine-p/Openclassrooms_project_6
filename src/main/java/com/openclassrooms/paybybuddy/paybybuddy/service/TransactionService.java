package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;


@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * Created new transaction
	 * @Param Integer idOwner
	 * @Param Integer idTransaction
	 * @Param double sum
	 * @Param double fee
	 * @Param double sumFinal
	 * @Param String description
	 * @return transactionEntity
	 */
	public TransactionEntity save (Integer idOwner, Integer idTransaction, double sum, double fee, double sumFinal, String description){
		TransactionEntity transactionEntity = new TransactionEntity();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		transactionEntity.setUserIdOwner(idOwner);
		transactionEntity.setUserIdTransaction(idTransaction);
		transactionEntity.setTransactionDate(timestamp);
		transactionEntity.setTransactionSum(sum);
		transactionEntity.setTransactionFee(fee);
		transactionEntity.setTransactionSumFinal(sumFinal);
		transactionEntity.setTransactionDescription(description);
		return transactionRepository.save(transactionEntity);
	}
	
	/**
	 *Get all transaction by userId
	 *@Param Integer : id
	 * @return transactions sort by userId 
	 */
	public List<TransactionEntity> getAllById(Integer id){
		return transactionRepository.findByUserIdOwner(id);
	}
}
