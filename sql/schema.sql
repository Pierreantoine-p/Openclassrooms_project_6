CREATE DATABASE IF NOT EXISTS paymybuddy;
USE paymybuddy;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `relation`;
DROP TABLE IF EXISTS `sold`;
DROP TABLE IF EXISTS `transaction`;

create table user(
USER_ID integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
USER_NAME varchar(250) NOT NULL,
USER_MAIL varchar(250) NOT NULL ,
USER_PASSWORD varchar(250) NOt NULL
UNIQUE (USER_MAIL)
);

create table relation(
TRANSACTION_ID integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
USER_FK_ID integer NOT NULL,
USER_FK_ID_OWNER integer NOT NULL, 
USER_MAIL varchar(250) NOT NULL
);

create table sold(
SOLD_ID integer PRIMARY KEY AUTO_INCREMEN  NOT NULL,
USER_FK_ID integer NOT NULL,
SOLD_SUM decimal(20,2)
);

create table transaction(
TRANSACTION_ID integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
USER_FK_ID integer NOT NULL,
USER_FK_ID_OWNER integer NOT NULL, 
TRANSACTION_DATE  timestamp,
TRANSACTION_SUM decimal,
TRANSACTION_FEE decimal,
TRANSACTION_SUM_FINAL decimal,
TRANSACTION_DESCRIPTION text
);

insert into user (USER_ID, USER_NAME, USER_MAIL, USER_PASSWORD) values (1, 'toto','toto@mail.fr', '123456');
insert into user (USER_ID, USER_NAME, USER_MAIL, USER_PASSWORD) values (2, 'tata','tata@mail.fr', '123456');
insert into user (USER_ID, USER_NAME, USER_MAIL, USER_PASSWORD) values (3, 'tutu','tutu@mail.fr', '123456');

insert into relation(TRANSACTION_ID, USER_FK_ID, USER_FK_ID_OWNER, USER_MAIL) values (1,1,2,'tata@mail.fr');
insert into relation(TRANSACTION_ID, USER_FK_ID, USER_FK_ID_OWNER, USER_MAIL) values (2,1,2,'toto@mail.fr');
insert into relation(TRANSACTION_ID, USER_FK_ID, USER_FK_ID_OWNER, USER_MAIL) values (3,1,3,'toto@mail.fr');
insert into relation(TRANSACTION_ID, USER_FK_ID, USER_FK_ID_OWNER, USER_MAIL) values (4,3,2,'tata@mail.fr');

insert into sold(SOLD_ID, USER_FK_ID, SOLD_SUM) values (1,1,30);
insert into sold(SOLD_ID, USER_FK_ID, SOLD_SUM) values (2,2,40);
insert into sold(SOLD_ID, USER_FK_ID, SOLD_SUM) values (3,3,50);

insert into transaction(USER_ID, USER_FK_ID, USER_FK_ID_OWNER, TRANSACTION_DATE, TRANSACTION_SUM, TRANSACTION_FEE, TRANSACTION_SUM_FINAL, TRANSACTION_DESCRIPTION) values (3,3,50);


commit;