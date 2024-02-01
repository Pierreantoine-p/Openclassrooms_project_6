

CREATE DATABASE IF NOT EXISTS paymybuddy;

USE paymybuddy;

create table user(
USER_ID integer PRIMARY KEY AUTO_INCREMENT ,
USER_NAME varchar(250) ,
USER_MAIL varchar(250) NOT NULL ,
USER_PASSWORD varchar(250) NOt NULL,
UNIQUE (USER_MAIL)
);

create table relation(
RELATION_ID integer PRIMARY KEY AUTO_INCREMENT,
USER_FK_ID_RELATION integer ,
USER_FK_ID_OWNER_RELATION integer NOT NULL,
FOREIGN KEY (USER_FK_ID_RELATION) REFERENCES user(USER_ID),
FOREIGN KEY (USER_FK_ID_OWNER_RELATION) REFERENCES user(USER_ID),
CONSTRAINT UC_User_Relation UNIQUE (USER_FK_ID_RELATION, USER_FK_ID_OWNER_RELATION)
);

create table sold(
SOLD_ID integer PRIMARY KEY AUTO_INCREMENT,
USER_FK_ID_SOLD integer NOT NULL UNIQUE,
SOLD_SUM double(20,2) DEFAULT 0,
FOREIGN KEY (USER_FK_ID_SOLD) REFERENCES user(USER_ID) ON DELETE CASCADE ,
CONSTRAINT CHK_SOLD_NON_NEGATIVE CHECK (SOLD_SUM >= 0)
);

create table transaction(
TRANSACTION_ID integer PRIMARY KEY AUTO_INCREMENT ,
USER_FK_ID_TRANSACTION integer NOT NULL,
USER_FK_ID_OWNER_TRANSACTION integer NOT NULL, 
TRANSACTION_DATE timestamp,
TRANSACTION_SUM double,
TRANSACTION_FEE double,
TRANSACTION_SUM_FINAL double,
TRANSACTION_DESCRIPTION text,
FOREIGN KEY (USER_FK_ID_TRANSACTION) REFERENCES user(USER_ID),
FOREIGN KEY (USER_FK_ID_OWNER_TRANSACTION) REFERENCES user(USER_ID)
);


commit;

