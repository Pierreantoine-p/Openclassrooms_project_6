package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SoldService soldService;

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
	 *Get a user by userId
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
	public Optional<UserDTO> getUserByMail(String userMail){
		Optional<UserEntity> userOptional = userRepository.findByuserMail(userMail);
		return userOptional.map(this::convertToDTO);		
	}

	/**
	 * Created new user
	 * @RequestBody userModel
	 */
	@Transactional
	public UserEntity save (UserEntity userEntity){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String userPassword = userEntity.getUserPassword();
		String hashedPassword = passwordEncoder.encode(userPassword);
		userEntity.setUserPassword(hashedPassword);
		UserEntity newUser = userRepository.save(userEntity);
		newUser.setSold(soldService.save(newUser.getUserId()));
		return userEntity;
	}


	/**
	 * Update a user
	 * @Param String : mail, 
	 * @RequestBody userModel
	 * @return Person update 
	 */
	/*
	public void saveOrUpdate( UserEntity userModel) {
	     System.out.println("Nom de zeus : "  );

		try {
			UserEntity user = new UserEntity();
			user.setUserMail(userModel.getUserMail());
			String userPassword = userModel.getUserPassword();
			String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt(12));
			System.out.println("hashedPassword : " + hashedPassword);
			user.setUserPassword(hashedPassword);

			userRepository.save(user);
		}catch(Exception e) {
			System.out.println("Nom de zeus : " + e);
		}


	}*/


}
