package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.entity.RelationEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.RelationDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.RelationService;


@RestController
@RequestMapping("/relation")
public class RelationController {

	@Autowired
	private RelationService relationService;


	private static final Logger logger = LogManager.getLogger(RelationController.class);

	public RelationController(RelationService relationService) {
		this.relationService = relationService;
	}

	/**
	 * Created new relation between two users
	 * @RequestBody RelationEntity relationEntity
	 * @return All relation sort by id user 
	 */
	@PostMapping
	public  ResponseEntity<RelationEntity> save(@RequestBody RelationEntity relationEntity)  {
		logger.info("save, RequestBody: RelationEntity={} ", relationEntity );
		RelationEntity result = relationService.save(relationEntity);
		logger.info("result: result={}", result );
		return new ResponseEntity<>(relationEntity,HttpStatus.OK);	 
	}
	
	/**
	 * Get all relation by userId 
	 * @Param Integer : id
	 * @return All relation sort by userId  
	 */
	@GetMapping("/relations/{id}")
	public ResponseEntity<List<RelationDTO>> getAllRelationById (@PathVariable Integer id){
		logger.info("getOneById, params: id={}", id);
		List<RelationDTO> relationList = relationService.getRelationsById(id);
		logger.info("result: result={}", relationList );
		return new ResponseEntity<>(relationList,HttpStatus.OK);
	}

	
	/**
	 * Get one relation by id_user
	 * @Param Integer : id
	 * @return One relation  
	 */
	/*
	@GetMapping("/relation/{userIdOwner}/{userUserId}")
	public ResponseEntity<RelationDTO> getOneRelationById (@PathVariable Integer userIdOwner, @PathVariable Integer userUserId){
		logger.info("getOneByMail, params: id={}", userIdOwner);
		RelationDTO result = relationService.getRelationById(userIdOwner,userUserId);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	*/

}
