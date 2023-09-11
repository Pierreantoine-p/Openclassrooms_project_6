package com.openclassrooms.paymybuddy.paymybuddy.data;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

@Configuration
public class DataBaseConfig {
	
    private static final Logger logger = LogManager.getLogger(DataBaseConfig.class);
    /*
    @Bean
    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException, ConfigurationException{
    	logger.info("Create DB connection");
    	return("hello");
    }
    */

}
