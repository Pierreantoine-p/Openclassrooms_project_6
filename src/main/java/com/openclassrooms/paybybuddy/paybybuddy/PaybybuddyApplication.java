package com.openclassrooms.paybybuddy.paybybuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaybybuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaybybuddyApplication.class, args);
	}

}
//TODO : Faire le chemin de donnée pour voir quel service toucher

//TODO : Ce crée un compte
//TODO : modifier l'utilisateur
//TODO : Ce connecter à un compte

//TODO : Ajouter une relation
//TODO : Consulter la liste
//TODO : Supprimer une relation

//TODO : remplire table sold à partir de son compte bancaire
//TODO : faire un virement vers un autre utilisateur (chaque transfert est sauvegarder dans l'historique)
//TODO : virement du sold sur le compte bancaire

//TODO : Voir l'historique des virements



/*

crée un compte
param : mail, password
=> save user ok


update user
param mail + requestbody
=> get user by mail ok
=>update user


connexion
param : mail, password
=> get user

Ajouter une relation
param : mail_user + userIdOwner
=> get id user
=> get user by mail 
=> create relation

consulter la liste
=> get userIdOwner
=> get all by id_user

supprimer une relation
=> get id user
=> find user by mail who want delete
=> delete user

remplire table sold à partir de son compte bancaire
=> get idowner 
update sold

faire un virement 
idowner 
relation 
get sold idowner
mail de la relation 
l'id du mail
update sold idowner 
update sold id_relation
=>create new transfert 


virement du sold sur le compte bancaire
=> get idowner 
update sold




transaction 
récupérer toute les transaction d'un iduser
crée une transaction

sold
récupérer un sold d'un iduser
update un sold

relation
récupère toute les relations à partir de l'iduser
récupère une relation par l'iduser
crée une relation

user
récupère un user par son id 
récupèrer tous les utilisateurs
récupèrer un utilisateur par son mail
crée un user




*/
// endpoint façon angular
// tester 