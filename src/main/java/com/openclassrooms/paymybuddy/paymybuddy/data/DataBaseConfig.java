package com.openclassrooms.paymybuddy.paymybuddy.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.openclassrooms.paymybuddy.paymybuddy.model.UserModel;
import com.openclassrooms.paymybuddy.paymybuddy.service.UserService;

@SpringBootApplication
public class DataBaseConfig implements CommandLineRunner{
	
	@Autowired
	private UserService userService;

	public static void main (String [] args) {
		SpringApplication.run(DataBaseConfig.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		Iterable<UserModel> users = userService.getAll();
		users.forEach(user->
		System.out.println(user.getUserMail()));
	}

}
