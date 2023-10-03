package com.openclassrooms.paybybuddy.paybybuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.model.DTOTransfertModel;
import com.openclassrooms.paybybuddy.paybybuddy.service.RelationService;
import com.openclassrooms.paybybuddy.paybybuddy.service.ServiceService;

@RestController
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private DTOTransfertModel dTOTransfertModel;

	@PutMapping()
	@RequestMapping("/transfer")
	public ResponseEntity<DTOTransfertModel> transfer(@RequestBody DTOTransfertModel dTOTransfertModel){
		logger.info("save, RequestBody: dTOTransfertModel={} ", dTOTransfertModel );
		ServiceService.transfert(dTOTransfertModel);
		return new ResponseEntity<>(dTOTransfertModel, HttpStatus.OK);

	}

}
