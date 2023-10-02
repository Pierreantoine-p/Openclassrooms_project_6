package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paybybuddy.paybybuddy.model.SoldModel;
import com.openclassrooms.paybybuddy.paybybuddy.model.TransactionModel;



public interface SoldRepository extends CrudRepository<SoldModel, Integer>{
	
	SoldModel findByUserId(Integer id);

}
