package com.openclassrooms.paybybuddy.paybybuddy.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.paybybuddy.paybybuddy.entity.UserEntity;
//import com.openclassrooms.paybybuddy.paybybuddy.model.DTOTransfertModel;
import com.openclassrooms.paybybuddy.paybybuddy.model.TransfertModelDTO;
import com.openclassrooms.paybybuddy.paybybuddy.model.UserDTO;

@Service
@Component
public class ServiceService {

	private UserService userService;

	private TransactionService transactionService;

	private SoldService soldService;

	private RelationService relationService;

	public ServiceService(UserService userService, TransactionService transactionService, SoldService soldService, RelationService relationService) {
		this.userService = userService;
		this.transactionService = transactionService;
		this.soldService = soldService;
		this.relationService = relationService;
	}


	public void transfert(@RequestBody TransfertModelDTO transfertModelDTO) {
		Optional<UserDTO> optionalUser = userService.getUserById(transfertModelDTO.getUseridOwner());
		Optional<UserDTO> optionalUserRelation = userService.getUserById(transfertModelDTO.getUserfkIdRelation());
		double tax = 0.05;
		double amountTax = transfertModelDTO.getAmount() * tax;
		soldService.saveOrUpdate(transfertModelDTO.getSoldEntity());

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