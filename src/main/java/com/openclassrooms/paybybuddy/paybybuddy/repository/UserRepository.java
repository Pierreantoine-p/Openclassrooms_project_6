package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;



@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public UserDTO save(UserDTO userDTO);

	public Optional<UserEntity> findByuserMail (String userMail);

}
