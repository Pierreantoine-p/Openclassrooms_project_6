package com.openclassrooms.paybybuddy.paybybuddy.model;

import java.math.BigDecimal;
import java.util.List;

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
@Table(name = "sold")
@Data
@NoArgsConstructor
public class SoldModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sold_id")
	private Integer soldDd;

	@Column(name = "user_fk_id_sold")
	private Integer userId;

	@Column(name = "sold_sum")
	private BigDecimal soldSum;

	
	
}
