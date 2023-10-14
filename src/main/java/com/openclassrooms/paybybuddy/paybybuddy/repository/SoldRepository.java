package com.openclassrooms.paybybuddy.paybybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;



public interface SoldRepository extends CrudRepository<SoldEntity, Integer>{
	
	SoldEntity findByUserId(Integer id);

}
