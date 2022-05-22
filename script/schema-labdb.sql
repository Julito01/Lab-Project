CREATE DATABASE IF NOT EXISTS LabDB;

CREATE TABLE IF NOT EXISTS LabDB.users (
	userId int NOT NULL AUTO_INCREMENT,
    username varchar(200) NOT NULL,
    password varchar(200) NOT NULL,
    userType varchar(50) NOT NULL,
    PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS LabDB.patients (
	patientId varchar(8) NOT NULL,
    name varchar(250) NOT NULL,
    address varchar(250) NULL,
    mail varchar(200) NULL,
    genre varchar(10) NOT NULL,
    age varchar(3) NULL,
    CONSTRAINT Pk_Patient PRIMARY KEY (patientId, name)
);


