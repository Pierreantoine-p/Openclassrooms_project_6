package com.openclassrooms.paybybuddy.paybybuddy.model;

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
@Table(name = "relation")
@Data
@NoArgsConstructor
@DynamicUpdate
public class RelationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relation_id")
	private Integer id;

	@Column(name = "user_fk_id_owner_relation")
	private Integer useridOwner;

	@OneToOne(
			cascade = CascadeType.DETACH, 
			orphanRemoval = true, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "user_fk_id_relation", referencedColumnName = "user_id")
	private UserModel user;

}
