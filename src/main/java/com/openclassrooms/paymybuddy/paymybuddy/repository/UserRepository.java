package com.openclassrooms.paymybuddy.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paymybuddy.paymybuddy.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
