package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;


@Service
public class RelationService {

	@Autowired
	private RelationRepository relationRepository;

	@Autowired
	private ModelMapper modelMapper;

	public RelationDTO convertToDTO(RelationEntity relationEntity, Set<Integer> processedEntities) {
		if (relationEntity == null || processedEntities.contains(relationEntity.getRelationId())) {
			return new RelationDTO();
		}
		processedEntities.add(relationEntity.getRelationId()); // Marquer la relation comme traitée

		RelationDTO relationDTO = new RelationDTO();
		relationDTO.setRelationId(relationEntity.getRelationId());
		relationDTO.setUserIdOwner(relationEntity.getUserFkIdOwnerRelation());

		if(relationEntity.getUser() != null) {
			relationDTO.setUserFkIdRelation(relationEntity.getUser().getUserId());;
		}
		return relationDTO;
	}
/*
	if(relationEntity.getUser() != null) {
		relationDTO.setUserFkIdOwnerRelation(relationEntity.getUser().getUserId());
	}
	*/

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
		List<RelationEntity> relationList = relationRepository.findAllByUserFkIdOwnerRelation (id);
		List<RelationDTO> relationDTO = new ArrayList<>();
	    Set<Integer> processedEntities = new HashSet<>(); // Créez un ensemble vide

		for (RelationEntity relation : relationList) {
			relationDTO.add(convertToDTO(relation,processedEntities));
		}

		return relationDTO;	
	}

	/**
	 *Get one relation by id_user
	 *@Param String : id
	 * @return One relation sort by id_user
	 */
	/*
	public RelationDTO getRelationById(Integer userIdOwner, Integer userUserId){
		RelationEntity relationEntity = relationRepository.findByUserIdOwnerAndUserUserId(userIdOwner, userUserId);
		RelationDTO relationDTO = modelMapper.map(relationEntity, RelationDTO.class);
		return relationDTO;
	}
	*/

}

