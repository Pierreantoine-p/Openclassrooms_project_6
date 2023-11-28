package com.openclassrooms.paybybuddy.paybybuddy.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;

@Repository
public interface RelationRepository extends CrudRepository<RelationEntity, Integer> {


	List<RelationEntity> findAllByUserFkIdOwnerRelation (Integer userId);


}
