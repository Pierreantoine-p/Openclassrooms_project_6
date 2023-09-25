package com.openclassrooms.paybybuddy.paybybuddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
