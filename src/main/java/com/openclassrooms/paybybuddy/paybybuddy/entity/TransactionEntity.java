package com.openclassrooms.paybybuddy.paybybuddy.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
import jakarta.persistence.OneToOne;
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
	private BigDecimal transactionSum;

	@Column(name = "transaction_fee")
	private BigDecimal transactionFee;

	@Column(name = "transaction_sum_final")
	private BigDecimal transactionSumFinal;

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
