package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.paybybuddy.paybybuddy.model.UserModel;



@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

	public Optional<UserModel> findByuserMail (String user_mail);
	
	void deleteByuserMail(String user_mail);
	
	 
}
