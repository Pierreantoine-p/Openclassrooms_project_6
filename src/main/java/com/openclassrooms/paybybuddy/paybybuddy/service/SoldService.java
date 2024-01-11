package com.openclassrooms.paybybuddy.paybybuddy.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;


@Service
public class SoldService {

	@Autowired
	private SoldRepository soldRepository;
	//	@Before all create new user
 
	/**
	 * Get a user by id
	 * @Param Integer : id
	 * @return single user 
	 */
	public SoldEntity getById(Integer id){
		return soldRepository.findByUserId(id);
	}

	/**
	 * Update a sum for sold 
	 * @Param Integer : id
	 * @Param double : amount
	 * @return Sold update 
	 */
	public void update( Integer id, double amount) {
		SoldEntity existingSold = soldRepository.findByUserId(id);		
		if(existingSold != null) {
			existingSold.setSoldSum(amount);
			soldRepository.save(existingSold);
		}
	}
	
	/**
	 * create new sold when user is create
	 * @Param Integer : id
	 * @return new sold default 0 
	 */
	public SoldEntity save (Integer id) {
		SoldEntity soldEntity = new SoldEntity();
		soldEntity.setUserId(id);
		return soldRepository.save(soldEntity);
	}
}
