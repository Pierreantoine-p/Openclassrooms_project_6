package com.openclassrooms.paybybuddy.paybybuddy.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class SoldEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sold_id")
	private Integer soldId;

	@Column(name = "user_fk_id_sold")
	private Integer userId;

	@Column(name = "sold_sum")
	private double soldSum;

	@JsonCreator
	public SoldEntity(
			@JsonProperty("soldId")Integer soldId,
			@JsonProperty("userId")Integer userId,
			@JsonProperty("soldSum")double soldSum
			) {
		this.soldId = soldId;
		this.userId = userId;
		this.soldSum = soldSum;

	}

	

}
