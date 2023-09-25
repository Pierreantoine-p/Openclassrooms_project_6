package com.openclassrooms.paybybuddy.paybybuddy.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@DynamicUpdate
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id") //TODO : change for user_id
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_mail")
	private String userMail;

	@Column(name = "user_password")
	private String userPassword;
	
/*
	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private RelationModel relations ;
	
	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private SoldModel sold;
	
	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private TransactionModel transactions;
	*/
}
