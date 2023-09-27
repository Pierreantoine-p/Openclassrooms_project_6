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
USER_PASSWORD varchar(250) NOt NULL,
UNIQUE (USER_MAIL)
);

create table relation(
RELATION_ID integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
USER_FK_ID_RELATION integer NOT NULL,
USER_FK_ID_OWNER_RELATION integer NOT NULL, 
RELATION_USER_MAIL varchar(250) NOT NULL,
FOREIGN KEY (USER_FK_ID_RELATION) REFERENCES user(USER_ID),
FOREIGN KEY (USER_FK_ID_OWNER_RELATION) REFERENCES user(USER_ID)
);

create table sold(
SOLD_ID integer PRIMARY KEY AUTO_INCREMENT  NOT NULL,
USER_FK_ID_SOLD integer NOT NULL,
SOLD_SUM decimal(20,2),
FOREIGN KEY (USER_FK_ID_SOLD) REFERENCES user(USER_ID)
);

create table transaction(
TRANSACTION_ID integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
USER_FK_ID_TRANSACTION integer NOT NULL,
USER_FK_ID_OWNER_TRANSACTION integer NOT NULL, 
TRANSACTION_DATE  timestamp,
TRANSACTION_SUM decimal,
TRANSACTION_FEE decimal,
TRANSACTION_SUM_FINAL decimal,
TRANSACTION_DESCRIPTION text,
FOREIGN KEY (USER_FK_ID_TRANSACTION) REFERENCES user(USER_ID),
FOREIGN KEY (USER_FK_ID_OWNER_TRANSACTION) REFERENCES user(USER_ID)
);

insert into user (USER_ID, USER_NAME, USER_MAIL, USER_PASSWORD) values (1, 'toto','toto@mail.fr', '123456');
insert into user (USER_ID, USER_NAME, USER_MAIL, USER_PASSWORD) values (2, 'tata','tata@mail.fr', '123456');
insert into user (USER_ID, USER_NAME, USER_MAIL, USER_PASSWORD) values (3, 'tutu','tutu@mail.fr', '123456');

insert into relation(TRANSACTION_ID, USER_FK_ID_TRANSACTION, USER_FK_ID_OWNER_TRANSACTION, USER_MAIL_TRANSACTION) values (1,1,2,'tata@mail.fr');
insert into relation(TRANSACTION_ID, USER_FK_ID_TRANSACTION, USER_FK_ID_OWNER_TRANSACTION, USER_MAIL_TRANSACTION) values (2,1,2,'toto@mail.fr');
insert into relation(TRANSACTION_ID, USER_FK_ID_TRANSACTION, USER_FK_ID_OWNER_TRANSACTION, USER_MAIL_TRANSACTION) values (3,1,3,'toto@mail.fr');
insert into relation(TRANSACTION_ID, USER_FK_ID_TRANSACTION, USER_FK_ID_OWNER_TRANSACTION, USER_MAIL_TRANSACTION) values (4,3,2,'tata@mail.fr');

insert into sold(SOLD_ID, USER_FK_ID_SOLD, SOLD_SUM) values (1,1,30);
insert into sold(SOLD_ID, USER_FK_ID_SOLD, SOLD_SUM) values (2,2,40);
insert into sold(SOLD_ID, USER_FK_ID_SOLD, SOLD_SUM) values (3,3,50);

insert into transaction(TRANSACTION_ID, USER_FK_ID_TRANSACTION, USER_FK_ID_OWNER_TRANSACTION, TRANSACTION_DATE, TRANSACTION_SUM, TRANSACTION_FEE, TRANSACTION_SUM_FINAL, TRANSACTION_DESCRIPTION) values (3,3,50);


commit;