package com.openclassrooms.paybybuddy.paybybuddy.service;



import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;


@Service
public class SoldService {

	@Autowired
	private SoldRepository soldRepository;
	
	//getById
	//saveOrUpdate
	/**
	 *Get a user by id
	 *@Param Integer : id
	 * @return single user 
	 */
	public SoldEntity getById(Integer id){
		return soldRepository.findByUserId(id);
	}
	
	/**
	 * Update a sum for sold 
	 * @RequestBody soldModel
	 * @return Sold update 
	 */
	public void Update( Integer id, double amount) {
		SoldEntity sold = soldRepository.findByUserId(id);
		sold.getSoldSum().add(amount);
		soldRepository.save(sold);
	}
}
