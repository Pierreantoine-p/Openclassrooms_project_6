package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;



@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByuserMail (String user_mail);
	
	void deleteByuserMail(String user_mail);
	
	 
}
