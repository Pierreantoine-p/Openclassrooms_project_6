package com.openclassrooms.paymybuddy.paymybuddy.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

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
public class TransactionModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
    private Integer transactionId;
	
	@Column(name = "user_fk_id_owner")
	private Integer userIdOwner;
	
	@Column(name = "user_fk_id")
	private Integer userId;
	
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
}
