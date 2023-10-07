package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;



public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
	
 //List<TransactionEntity> findByUserIdTransaction(Integer id);
	 List<TransactionEntity> findByUserIdOwner(Integer id);

}
