package com.openclassrooms.paymybuddy.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paymybuddy.paymybuddy.model.TransactionModel;
import com.openclassrooms.paymybuddy.paymybuddy.model.UserModel;

public interface TransactionRepository extends CrudRepository<TransactionModel, Integer> {

}
