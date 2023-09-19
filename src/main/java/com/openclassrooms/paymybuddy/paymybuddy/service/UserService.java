package com.openclassrooms.paymybuddy.paymybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.paymybuddy.model.UserModel;
import com.openclassrooms.paymybuddy.paymybuddy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserModel> getAll(){
		return (List<UserModel>) userRepository.findAll();
	}
	
	
	public Optional<UserModel> getUserById(Integer id){
		return userRepository.findById(id);
	}
	
}
