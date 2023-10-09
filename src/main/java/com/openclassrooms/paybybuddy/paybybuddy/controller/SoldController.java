package com.openclassrooms.paybybuddy.paybybuddy.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
import com.openclassrooms.paybybuddy.paybybuddy.service.SoldService;


@RestController
@RequestMapping("/sold")
public class SoldController {
	
	@Autowired
	private SoldService soldService;
	
    private static final Logger logger = LogManager.getLogger(SoldController.class);

    public SoldController(SoldService soldService) {
		this.soldService = soldService;
	}
    
    //récupérer un sold d'u iduser

	/**
	 * Get all transaction sort by id
	 * @Param Integer : id
	 * @return One all transaction sort by id  
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SoldEntity> getById (@PathVariable Integer id){
		logger.info("getallById, params: id={}", id);
		SoldEntity result = soldService.getById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	/**
	 * Update a sold
	 * @RequestBody soldModel
	 * @return sold update 
	 */
	@PutMapping("/id/amount")
	public SoldEntity update(@RequestParam(name = "id")Integer id,@RequestParam(name = "amount")double amount)  {
		//logger.info("update, params: id={}, RequestBody: SoldModel={} ", soldModel );
		soldService.saveOrUpdate(id, amount);
		return soldModel;
	}
    //TODO: change method for update sold with just idowner and amount
}
