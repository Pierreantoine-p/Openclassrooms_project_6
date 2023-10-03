package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;


@Service
public class RelationService {
	
	@Autowired
	private RelationRepository relationRepository;
	
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
	public List<RelationEntity> getRelationsById(Integer id){
		return relationRepository.findAllByUserUserId(id);
	}
	
	/**
	 *Get one relation by id_user
	 *@Param String : id
	 * @return One relation sort by id_user
	 */
	public RelationEntity getRelationById(Integer id){
		return relationRepository. findByUserUserId(id);
	}
	
}

