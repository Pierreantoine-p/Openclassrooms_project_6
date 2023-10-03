package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserDTO convertToDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userEntity.getUserId());
		userDTO.setUserName(userEntity.getUserName());
		userDTO.setUserMail(userEntity.getUserMail());
		userDTO.setUserPassword(userEntity.getUserPassword());
		return userDTO;
	}
	
	/**
	 *Get a list of users
	 * @return List of users
	 */
	public List<UserDTO> getAll(){
		Iterable<UserEntity> users = userRepository.findAll();
		List<UserDTO> userDTO = new ArrayList<>();
		
		for(UserEntity userEntity : users) {
			userDTO.add(convertToDTO(userEntity));
		}
		return userDTO;
	}
	
	/**
	 *Get a user by id
	 *@Param Integer : id
	 * @return single user 
	 */
	public Optional<UserDTO> getUserById(Integer id){
		Optional<UserEntity> userOptional = userRepository.findById(id);
		return userOptional.map(this::convertToDTO);		
	}
	
	
	/**
	 *Get a user by mail
	 *@Param String : user_mail
	 * @return single user 
	 */
	public Optional<UserDTO> getUserByMail(String user_mail){
		Optional<UserEntity> userOptional = userRepository.findByuserMail(user_mail);
		return userOptional.map(this::convertToDTO);		
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
