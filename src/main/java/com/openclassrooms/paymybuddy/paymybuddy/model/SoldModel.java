package com.openclassrooms.paymybuddy.paymybuddy.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sold")
@Data
@NoArgsConstructor
public class SoldModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sold_id")
    private Integer soldDd;
	
	@Column(name = "user_fk_id")
	private Integer userId;
	
	@Column(name = "sold_sum")
	private BigDecimal soldSum;
	

}
