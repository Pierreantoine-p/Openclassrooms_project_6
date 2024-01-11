package com.openclassrooms.paybybuddy.paybybuddy.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "relation")
@Data
@NoArgsConstructor
@DynamicUpdate
public class RelationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relation_id")
	private Integer relationId;

	@Column(name = "user_Fk_Id_Owner_relation")
	private Integer userFkIdOwnerRelation;
	
	@OneToOne(
			cascade = CascadeType.DETACH, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk_id_relation", referencedColumnName = "user_id")
	private UserEntity user;

	@JsonCreator
	public RelationEntity(
			@JsonProperty("relationId")Integer relationId,
			@JsonProperty("userFkIdOwnerRelation")Integer userFkIdOwnerRelation,
			@JsonProperty("user")UserEntity user
			) {
		this.relationId = relationId;
		this.userFkIdOwnerRelation = userFkIdOwnerRelation;
		this.user = user;

	}
	
}
