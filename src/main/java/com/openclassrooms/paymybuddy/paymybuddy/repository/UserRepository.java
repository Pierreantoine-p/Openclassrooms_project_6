package com.openclassrooms.paymybuddy.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.paymybuddy.paymybuddy.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

}
