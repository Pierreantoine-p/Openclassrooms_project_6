package com.openclassrooms.paymybuddy.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.paymybuddy.repository.SoldRepository;

@Service
public class SoldService {

	@Autowired
	private SoldRepository soldRepository;
}
