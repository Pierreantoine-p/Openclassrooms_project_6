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
	 * Created new user
	 * @RequestBody userModel
	 */
	@PostMapping
	public  ResponseEntity<RelationEntity> save(@RequestBody RelationEntity relationModel)  {
		logger.info("save, RequestBody: userModel={} ", relationModel );
		relationService.save(relationModel);
		return new ResponseEntity<>(relationModel,HttpStatus.OK);	 
	}

	/**
	 * Get all relation by user_id 
	 * @Param Integer : id
	 * @return All relation sort by id user  
	 */
	@GetMapping("/relations/{id}")
	public ResponseEntity<List<RelationEntity>> getAllRelationById (@PathVariable Integer id){
		logger.info("getOneById, params: id={}", id);
		List<RelationEntity> relationList = relationService.getRelationsById(id);
		return new ResponseEntity<>(relationList,HttpStatus.OK);
	}

	//TODO: revoir
	/**
	 * Get one relation by id_user
	 * @Param Integer : id
	 * @return One relation  
	 */
	@GetMapping("/relation/{id}")
	public ResponseEntity<RelationEntity> getOneRelationById (@PathVariable Integer id){
		logger.info("getOneByMail, params: id={}", id);
		RelationEntity result = relationService.getRelationById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
