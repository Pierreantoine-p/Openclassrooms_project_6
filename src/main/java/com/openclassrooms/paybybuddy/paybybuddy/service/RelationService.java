package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;


@Service
public class RelationService {

	@Autowired
	private RelationRepository relationRepository;

	public RelationDTO convertToDTO(RelationEntity relationEntity) {
		RelationDTO relationDTO = new RelationDTO();
		relationDTO.setRelationId(relationEntity.getRelationId());
		relationDTO.setUseridOwner(relationEntity.getUseridOwner());
		
		if(relationEntity.getUser() != null) {
			UserDTO userDTO = convertUserEntityToDTO(relationEntity.getUser());
			relationDTO.setUser(userDTO);
		}
		return relationDTO;
	}
	
	private UserDTO convertUserEntityToDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userEntity.getUserId());
		return userDTO;
	}
	
	/**
	 * Created new relation
	 * @RequestBody relationModel
	 */
	public RelationEntity save (RelationEntity relationModel){
		return relationRepository.save(relationModel);
	}

	/**
	 *Get all relations by id_user
	 *@Param String : id
	 * @return all relations sort by id_user
	 */
	public List<RelationDTO> getRelationsById(Integer id){
		List<RelationEntity> relationList = relationRepository.findAllByUserUserId(id);
		List<RelationDTO> relationDTO = new ArrayList<>();
		for (RelationEntity relation : relationList) {
			relationDTO	.add(convertToDTO(relation));
		}
		return relationDTO;	
	}

	/**
	 *Get one relation by id_user
	 *@Param String : id
	 * @return One relation sort by id_user
	 */
	public Optional<RelationDTO> getRelationById(Integer id){
		Optional<RelationEntity> relationEntity = relationRepository. findByUserUserId(id);
		return relationEntity.map(this::convertToDTO);
	}

}

