package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 *Get a list of users
	 * @return List of users
	 */
	public List<UserEntity> getAll(){
		return (List<UserEntity>) userRepository.findAll();
	}
	
	/**
	 *Get a user by id
	 *@Param Integer : id
	 * @return single user 
	 */
	public Optional<UserEntity> getUserById(Integer id){
		return userRepository.findById(id);
	}
	
	
	/**
	 *Get a user by mail
	 *@Param String : user_mail
	 * @return single user 
	 */
	public Optional<UserEntity> getUserByMail(String user_mail){
		return userRepository.findByuserMail(user_mail);
	}
	
	/**
	 * Created new user
	 * @RequestBody userModel
	 */
	public UserEntity save (UserEntity userModel){
		return userRepository.save(userModel);
	}
	
	/**
	 * Update a user
	 * @Param String : mail, 
	 * @RequestBody userModel
	 * @return Person update 
	 */
	public void saveOrUpdate( UserEntity userModel) {
		userRepository.save(userModel);
	}
	
	/**
	 * Delete a user
	 * @Param String : mail, 
	 */
	public void delete(String  user_mail)  {
		 userRepository.deleteByuserMail(user_mail);
		
	}
	
}
