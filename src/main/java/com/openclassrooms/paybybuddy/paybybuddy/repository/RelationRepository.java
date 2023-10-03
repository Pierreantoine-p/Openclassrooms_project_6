package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;


public interface RelationRepository extends CrudRepository<RelationEntity, Integer> {

	//public List<RelationModel> findAllByUserUserId (Integer id);
	public List<RelationEntity> findAllByUserUserId (Integer id);
	
	//@Query("SELECT r FROM RelationModel r WHERE r.userFkIdRelation = :userId")
    //List<RelationModel> findAllByUserFkIdRelation(@Param("userId") Integer userId);
	
	//public RelationModel findByUserFkIdRelation (Integer id);
	public RelationEntity findByUserUserId (Integer id);


	
}
