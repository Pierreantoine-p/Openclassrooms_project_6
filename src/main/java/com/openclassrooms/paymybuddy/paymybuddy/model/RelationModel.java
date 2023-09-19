package com.openclassrooms.paymybuddy.paymybuddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "relation")
@Data
@NoArgsConstructor
public class RelationModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "relation_id")
    private Integer id;
	
	@Column(name = "user_fk_id")
	private Integer userId;
	
	@Column(name = "user_fk_id_owner")
	private Integer useridOwner;
	
	@Column(name = "user_mail")
	private String mail;
}
