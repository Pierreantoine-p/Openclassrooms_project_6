package com.openclassrooms.paybybuddy.paybybuddy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationDTO {
	
	private Integer relationId;

	private Integer userFkIdRelation;
	
	private Integer userIdOwner;

}
