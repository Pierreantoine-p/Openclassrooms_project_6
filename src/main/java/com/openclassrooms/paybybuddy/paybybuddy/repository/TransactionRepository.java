package com.openclassrooms.paybybuddy.paybybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paybybuddy.paybybuddy.model.TransactionModel;



public interface TransactionRepository extends CrudRepository<TransactionModel, Integer> {

}
