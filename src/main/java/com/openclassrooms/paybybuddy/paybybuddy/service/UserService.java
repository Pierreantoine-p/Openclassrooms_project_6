package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.model.UserModel;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 *Get a list of users
	 * @return List of users
	 */
	public List<UserModel> getAll(){
		return (List<UserModel>) userRepository.findAll();
	}
	
	/**
	 *Get a user by id
	 *@Param Integer : id
	 * @return single user 
	 */
	public Optional<UserModel> getUserById(Integer id){
		return userRepository.findById(id);
	}
	
	
	/**
	 *Get a user by mail
	 *@Param String : user_mail
	 * @return single user 
	 */
	public Optional<UserModel> getUserByMail(String user_mail){
		return userRepository.findByuserMail(user_mail);
	}
	
	/**
	 * Created new user
	 * @RequestBody userModel
	 */
	public UserModel save (UserModel userModel){
		return userRepository.save(userModel);
	}
	
	/**
	 * Update a user
	 * @Param String : mail, 
	 * @RequestBody userModel
	 * @return Person update 
	 */
	public void saveOrUpdate( UserModel userModel) {
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
