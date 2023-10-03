package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;



public interface SoldRepository extends CrudRepository<SoldEntity, Integer>{
	
	SoldEntity findByUserId(Integer id);

}
