package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import com.openclassrooms.paybybuddy.paybybuddy.model.TransactionModel;
import com.openclassrooms.paybybuddy.paybybuddy.service.TransactionService;



@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;


	private static final Logger logger = LogManager.getLogger(TransactionController.class);

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	//TODO : revoir l'ordre de creation 
	/**
	 * Created new transaction
	 * @RequestBody transactionModel
	 */
	@PostMapping
	public  ResponseEntity<TransactionModel> save(@RequestBody TransactionModel transactionModel)  {
		logger.info("save, RequestBody: transactionModel={} ", transactionModel );
		transactionService.save(transactionModel);
		return new ResponseEntity<>(transactionModel,HttpStatus.OK);	 
	}


	/**
	 * Get all transaction sort by id
	 * @Param Integer : id
	 * @return One all transaction sort by id  
	 */
	@GetMapping("/{id}")
	public ResponseEntity<List<TransactionModel>> getAllById (@PathVariable Integer id){
		logger.info("getallById, params: id={}", id);
		List<TransactionModel> result = transactionService.getAllById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
		/*
		Optional<TransactionModel> optionalValue = transactionService.getUserById(id);
		List<TransactionModel> resultList = optionalToList(optionalValue);
		if(!resultList.isEmpty()) {
			System.out.println("here");
			return new ResponseEntity<>(resultList,HttpStatus.OK);
		}else {

			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	*/

	/**
	 * Get one transaction by id
	 * @Param Integer : id
	 * @return One transaction  
	 */
	/*
	@GetMapping("/{id}")
	public ResponseEntity<TransactionModel> getOne (@PathVariable Integer id){
		logger.info("getOneById, params: id={}", id);
		 Optional<TransactionModel> optionalValue = transactionService.getUserById(id);
		 List<TransactionModel> resultList = optionalToList(optionalValue);
		if(!resultList.isEmpty()) {
			System.out.println("here");
			return new ResponseEntity<>(resultList.get(0),HttpStatus.OK);
		}else {

			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	 */






	 
}
