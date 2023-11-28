package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.service.TransactionService;



@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;


	private static final Logger logger = LogManager.getLogger(TransactionController.class);

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}


	/**
	 * Get all transaction sort by userId
	 * @Param Integer : id
	 * @return One all transaction sort by userId
	 */
	@GetMapping("/{id}")
	public ResponseEntity<List<TransactionEntity>> getAllById (@PathVariable Integer id){
		logger.info("getallById, params: id={}", id);
		List<TransactionEntity> result = transactionService.getAllById(id);
		logger.info("result: result={}", result );
		return new ResponseEntity<>(result,HttpStatus.OK);
	}


}
