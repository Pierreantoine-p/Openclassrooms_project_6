package com.openclassrooms.paybybuddy.paybybuddy.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class TransfertModelDTO {


	private Integer useridOwner ;
	private Integer userfkIdRelation;
	private double amount;
	private String description;


}
