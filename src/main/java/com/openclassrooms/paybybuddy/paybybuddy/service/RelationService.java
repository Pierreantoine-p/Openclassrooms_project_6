package com.openclassrooms.paybybuddy.paybybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paybybuddy.paybybuddy.repository.RelationRepository;


@Service
public class RelationService {
	
	@Autowired
	private RelationRepository relationRepository;
}
