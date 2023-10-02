package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.model.SoldModel;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserModel;
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
	public SoldModel getById(Integer id){
		return soldRepository.findByUserId(id);
	}
	
	/**
	 * Update a sum for sold 
	 * @RequestBody soldModel
	 * @return Sold update 
	 */
	public void saveOrUpdate( SoldModel soldModel) {
		soldRepository.save(soldModel);
	}
}
