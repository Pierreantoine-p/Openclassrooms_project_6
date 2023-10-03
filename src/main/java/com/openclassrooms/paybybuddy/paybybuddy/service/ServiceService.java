package com.openclassrooms.paybybuddy.paybybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.model.DTOTransfertModel;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;

@Service
public class ServiceService {
	
	@Autowired
	private SoldRepository soldRepository;
	
	public void transfert(DTOTransfertModel dTOTransfertModel) {
		
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