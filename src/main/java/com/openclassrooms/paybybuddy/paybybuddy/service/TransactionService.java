package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.TransactionRepository;


@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	/**
	 * Created new user
	 * @RequestBody userModel
	 */
	public TransactionEntity save (TransactionEntity transactionModel){
		return transactionRepository.save(transactionModel);
	}
	
	/**
	 *Get a user by id
	 *@Param Integer : id
	 * @return single user 
	 */
	public List<TransactionEntity> getAllById(Integer id){
		return transactionRepository.findByUserIdTransaction(id);
	}
}
