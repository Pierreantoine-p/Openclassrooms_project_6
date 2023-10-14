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

	
	/* @JsonCreator
	public DTOTransfertModel(@JsonProperty("id")Integer id,@JsonProperty("useridOwner")Integer useridOwner, @JsonProperty("userfkIdRelation")Integer userfkIdRelation,@JsonProperty("description")String description) {
		this.id = id;
		this.useridOwner = useridOwner;
		this.userfkIdRelation = userfkIdRelation;
		this.description = description;
	} */
}
