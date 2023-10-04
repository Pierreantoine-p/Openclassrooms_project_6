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
		 Optional<UserDTO> optionalUser = userService.getUserById(transfertModelDTO.getId());
		 Optional<UserDTO> optionalUserRelation = userService.getUserById(transfertModelDTO.getUserfkIdRelation());
		 
	}
	
}
/*
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