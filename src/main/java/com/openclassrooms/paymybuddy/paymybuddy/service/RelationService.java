package com.openclassrooms.paymybuddy.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.paymybuddy.repository.RelationRepository;

@Service
public class RelationService {
	
	@Autowired
	private RelationRepository relationRepository;
}
