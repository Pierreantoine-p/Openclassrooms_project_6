package com.openclassrooms.paybybuddy.paybybuddy.entity;

import java.sql.Timestamp;
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
@Table(name = "transaction")
@Data
@NoArgsConstructor
@DynamicUpdate
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "user_fk_id_owner_transaction")
	private Integer userIdOwner;
	
	@Column(name = "user_fk_id_transaction")
	private Integer userIdTransaction;
	
	@Column(name = "transaction_date")
	private Timestamp transactionDate;

	@Column(name = "transaction_sum")
	private double transactionSum;

	@Column(name = "transaction_fee")
	private double transactionFee;

	@Column(name = "transaction_sum_final")
	private double transactionSumFinal;

	@Column(name = "transaction_description")
	private String transactionDescription;
/*
	@OneToOne(
			cascade = CascadeType.DETACH, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk_id_transaction",referencedColumnName = "user_id")
	private UserModel user;
	*/
}
