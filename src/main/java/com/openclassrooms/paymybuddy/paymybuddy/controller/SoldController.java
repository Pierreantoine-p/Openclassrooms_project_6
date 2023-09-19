package com.openclassrooms.paymybuddy.paymybuddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paymybuddy.paymybuddy.service.SoldService;

@RestController
@RequestMapping("/sold")
public class SoldController {
	
	@Autowired
	private SoldService soldService;
	
    private static final Logger logger = LogManager.getLogger(SoldController.class);

    public SoldController(SoldService soldService) {
		this.soldService = soldService;
	}
}
