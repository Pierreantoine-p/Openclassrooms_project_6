package com.openclassrooms.paymybuddy.paymybuddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paymybuddy.paymybuddy.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	
    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
}
