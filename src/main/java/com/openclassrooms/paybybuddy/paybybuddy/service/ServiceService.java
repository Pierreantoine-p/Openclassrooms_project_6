package com.openclassrooms.paybybuddy.paybybuddy.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.paybybuddy.paybybuddy.entity.SoldEntity;
import com.openclassrooms.paybybuddy.paybybuddy.entity.TransactionEntity;
import com.openclassrooms.paybybuddy.paybybuddy.model.TransfertModelDTO;
import com.openclassrooms.paybybuddy.paybybuddy.repository.SoldRepository;

@Service
@Component
public class ServiceService {

	private TransactionService transactionService;

	private SoldService soldService;

	private SoldRepository soldRepository;

	private static final Logger logger = LogManager.getLogger(ServiceService.class);


	public ServiceService(UserService userService, TransactionService transactionService, SoldService soldService, RelationService relationService,SoldRepository soldRepository) {
		this.transactionService = transactionService;
		this.soldService = soldService;
		this.soldRepository = soldRepository;
	}
	
	/**
	 * create new transfert between two users
	 * @Param TransfertModelDTO : transfertModelDTO
	 * @return new transfert result 
	 */
	@Transactional
	public TransactionEntity transfert(TransfertModelDTO transfertModelDTO) {

		SoldEntity existingSoldOwner = soldRepository.findByUserId(transfertModelDTO.getUseridOwner());		
		SoldEntity existingSoldRelation = soldRepository.findByUserId(transfertModelDTO.getUserfkIdRelation());	

		Integer taxValue = 5;
		double sum = transfertModelDTO.getAmount();

		double fee = (sum / 100) * taxValue;

		double sumFinal = sum + fee ;

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
