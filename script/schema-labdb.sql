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

CREATE TABLE IF NOT EXISTS LabDB.petitions (
    petitionId int AUTO_INCREMENT NOT NULL,
    loadDate datetime NOT NULL,
    deliveryDate datetime NOT NULL,
    PRIMARY KEY (petitionId)
);

CREATE TABLE IF NOT EXISTS LabDB.practices (
    practiceId int AUTO_INCREMENT NOT NULL,
    practiceCode int NOT NULL,
    practiceName varchar(255) NOT NULL,
    practiceLength datetime NOT NULL,
    PRIMARY KEY (practiceId)
);

CREATE TABLE IF NOT EXISTS LabDB.petitions_patients (
    patientId int NOT NULL,
    petitionId int NOT NULL,
    practiceId int NOT NULL,
    CONSTRAINT PK_Petition_Patient PRIMARY KEY (patientId, petitionId, practiceId),
    FOREIGN KEY (patientId) REFERENCES patients(patientId),
    FOREIGN KEY (petitionId) REFERENCES petitions(petitionId),
    FOREIGN KEY (practiceId) REFERENCES practices(practiceId)
);