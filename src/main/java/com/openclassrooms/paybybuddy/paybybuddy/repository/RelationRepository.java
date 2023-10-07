package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;

@Repository
public interface RelationRepository extends CrudRepository<RelationEntity, Integer> {

	//public List<RelationEntity> findAllByUserUserId (Integer id);	
    List<RelationEntity> findAllByUserFkIdOwnerRelation (Integer userId);
	//List<RelationEntity> findAllByUserFkIdRelation(Integer id);
	
	//@Query("SELECT r FROM RelationModel r WHERE r.userFkIdRelation = :userId")
    //List<RelationModel> findAllByUserFkIdRelation(@Param("userId") Integer userId);
	
	//public RelationModel findByUserFkIdRelation (Integer id);
	//@Query("select r1_0.relation_id,r1_0.user_fk_id_relation,r1_0.user_fk_id_owner_relation from relation r1_0 where r1_0.user_fk_id_owner_relation=?")
	//public RelationEntity findByUserIdOwnerAndUserUserId (Integer userIdOwner, Integer userUserId);


	
}
