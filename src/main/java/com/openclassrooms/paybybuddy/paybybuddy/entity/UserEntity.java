package com.openclassrooms.paybybuddy.paybybuddy.entity;


import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonCreator;import com.fasterxml.jackson.annotation.JsonProperty;

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
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id") 
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_mail")
	private String userMail;

	@Column(name = "user_password")
	private String userPassword;

	@OneToMany(
			cascade = CascadeType.DETACH, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Fk_Id_Owner_relation")
	private List<RelationEntity> relation;

	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_fk_id_sold")
	private SoldEntity sold;

	@OneToMany(
			cascade = CascadeType.DETACH, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk_id_owner_transaction")
	private List<TransactionEntity> transactions;


	@JsonCreator
	public UserEntity(
			@JsonProperty("userId")Integer userId,
			@JsonProperty("userName")String userName,
			@JsonProperty("userMail")String userMail,
			@JsonProperty("userPassword")String userPassword,
			@JsonProperty("relation")List<RelationEntity> relation,
			@JsonProperty("sold")SoldEntity sold,
			@JsonProperty("transactions")List<TransactionEntity> transactions
			) {
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userPassword = userPassword;
		this.relation = relation;
		this.sold = sold;
		this.transactions = transactions;
	}

}
