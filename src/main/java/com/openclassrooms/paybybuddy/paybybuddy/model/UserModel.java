package com.openclassrooms.paybybuddy.paybybuddy.model;


import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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


	@OneToMany(
			cascade = CascadeType.DETACH, 
			//TODO :  à voir si c'est encore utile
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "relation_id")
	private List<RelationModel> relations;

	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "sold_id")
	private SoldModel  sold;

	@OneToMany(
			cascade = CascadeType.DETACH, 
			orphanRemoval = true, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_id")
	private List<TransactionModel> transactions;


}
