package com.openclassrooms.paymybuddy.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paymybuddy.paymybuddy.model.SoldModel;
import com.openclassrooms.paymybuddy.paymybuddy.model.UserModel;

public interface SoldRepository extends CrudRepository<SoldModel, Integer>{

}
