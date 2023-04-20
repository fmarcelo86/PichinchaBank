drop table if exists PERSON;
drop table if exists CUSTOMER;
drop table if exists ACCOUNT;
drop table if exists MOVEMENT;

create table PERSON(
  ID                int not null AUTO_INCREMENT,
  NAME              varchar(100) not null,
  GENDER            varchar(100) not null,
  AGE               int,
  IDENTIFICATION    varchar(100) not null,
  ADDRESS           varchar(100) not null,
  PHONE             varchar(100) not null,
  PRIMARY KEY ( ID )
);

CREATE TABLE CUSTOMER (
  ID   INTEGER      NOT NULL AUTO_INCREMENT,
  PASSWORDS         varchar(100) not null,
  STATUS BOOL       NOT null,
  PRIMARY KEY (id)
);

create table ACCOUNT(
  ID                int not null AUTO_INCREMENT,
  ACCOUNT_NUMBER    INT not null,
  TYPE              varchar(100) not null,
  INITIAL_BALANCE   INT NOT NULL,
  STATUS BOOL       NOT null,
  CLIENT_ID         INT not null,
  PRIMARY KEY ( ID )
);

create table MOVEMENT(
  ID                int not null AUTO_INCREMENT,
  ACCOUNT_NUMBER    INT not null,
  TYPE              varchar(100) not null,
  INITIAL_BALANCE   INT NOT NULL,
  AMOUNT            INT not null,
  AVAILABLE_BALANCE INT NOT NULL,
  STATUS BOOL       NOT null,
  CLIENT_ID         INT not null,
  CLIENT            VARCHAR(100) not null,
  PRIMARY KEY ( ID )
);
