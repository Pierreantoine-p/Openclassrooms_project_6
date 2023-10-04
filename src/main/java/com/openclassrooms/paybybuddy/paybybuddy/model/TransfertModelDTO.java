package com.openclassrooms.paybybuddy.paybybuddy.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransfertModelDTO {

	private Integer id ;
	private Integer useridOwner ;
	private Integer userfkIdRelation;
	private String description;
	
	/*
	@JsonCreator
	public DTOTransfertModel(@JsonProperty("id")Integer id,@JsonProperty("useridOwner")Integer useridOwner, @JsonProperty("userfkIdRelation")Integer userfkIdRelation,@JsonProperty("description")String description) {
		this.id = id;
		this.useridOwner = useridOwner;
		this.userfkIdRelation = userfkIdRelation;
		this.description = description;
	}
	*/
}
