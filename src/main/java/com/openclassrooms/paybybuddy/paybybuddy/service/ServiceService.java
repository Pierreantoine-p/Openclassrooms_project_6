package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.paybybuddy.paybybuddy.controller.TransactionController;
import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.TransfertModelDTO;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;

@Service
@Component
public class ServiceService {

	private UserService userService;

	private TransactionService transactionService;

	private SoldService soldService;

	private RelationService relationService;

	private SoldRepository soldRepository;

	private static final Logger logger = LogManager.getLogger(ServiceService.class);


	public ServiceService(UserService userService, TransactionService transactionService, SoldService soldService, RelationService relationService,SoldRepository soldRepository) {
		this.userService = userService;
		this.transactionService = transactionService;
		this.soldService = soldService;
		this.relationService = relationService;
		this.soldRepository = soldRepository;
	}

	@Transactional
	public TransactionEntity transfert(TransfertModelDTO transfertModelDTO) {

		SoldEntity existingSoldOwner = soldRepository.findByUserId(transfertModelDTO.getUseridOwner());		
		SoldEntity existingSoldRelation = soldRepository.findByUserId(transfertModelDTO.getUserfkIdRelation());	


		Integer taxValue = 5;
		double sum = transfertModelDTO.getAmount();
		System.out.println("sum : " + sum);

		double fee = (sum / 100) * taxValue;
		System.out.println("fee : " + fee);

		double sumFinal = sum + fee ;
		System.out.println("sumFinal : " + sumFinal);

		TransactionEntity result = new TransactionEntity();
		
		if(existingSoldOwner.getSoldSum() >= sumFinal) {
			double newValueOwner = existingSoldOwner.getSoldSum() - sumFinal ;
			System.out.println("newValueOwner: " + newValueOwner);
			soldService.update(transfertModelDTO.getUseridOwner(), newValueOwner);
			
			double newValueRelation = existingSoldRelation.getSoldSum() + sum;
			System.out.println("newValueRelation : " + newValueRelation);
			soldService.update(transfertModelDTO.getUserfkIdRelation(), newValueRelation);
			result = transactionService.save(transfertModelDTO.getUseridOwner(),transfertModelDTO.getUserfkIdRelation(),sum,fee,sumFinal,transfertModelDTO.getDescription());
		}else {
			logger.info("Sold insufficient");
		}
		return result;
	}


}
/*
id owner 
id relation 
montant
je modifie le montant pour le mettre dans une variable qui sera 5% plus cher 
je modifie le sold de l'idowner avec le nouveau montant 
je modifie le sold de l'id relation avec l'ancien montant

historique transfert
j'ai l'id de l'owner 
j'ai l'id de ala relation 
timestamp pour la date
ancien montant 
tax
nouveau montant 
description 

modifie le sold de l'utilisateur qui envoie + 5%
je modifie 


l'id de l'owner 
l'id de la relation
le montant 
appel au transfert

 */
/*
récupere le sum et l'id de l'autre et l'id ower 
j'updatele sum de cette id
j'update le sum de l'autre id
j'écris mon transgert
 */