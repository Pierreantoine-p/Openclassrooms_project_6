package com.openclassrooms.paybybuddy.paybybuddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.model.TransfertModelDTO;
import com.openclassrooms.paybybuddy.paybybuddy.service.ServiceService;

@RestController
@RequestMapping("/transfert")
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
    private static final Logger logger = LogManager.getLogger(ServiceController.class);

	/**
	 * Create one transfert between two users
	 * @RequestBody TransfertModelDTO : transfertModelDTO
	 * @return transfertModelDTO
	 */
	@PostMapping
	public ResponseEntity<TransfertModelDTO> transfer(@RequestBody TransfertModelDTO transfertModelDTO){
		logger.info("save, RequestBody: dTOTransfertModel={} ", transfertModelDTO );
		serviceService.transfert(transfertModelDTO);
		return new ResponseEntity<>(transfertModelDTO, HttpStatus.OK);

	}

}
