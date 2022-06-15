CREATE DATABASE IF NOT EXISTS LabDB;

CREATE TABLE IF NOT EXISTS LabDB.users (
	userId int NOT NULL AUTO_INCREMENT,
    username varchar(200) NOT NULL,
    password varchar(200) NOT NULL,
    userType varchar(50) NOT NULL,
    CONSTRAINT Pk_User PRIMARY KEY (userId)
);

CREATE TABLE IF NOT EXISTS LabDB.patients (
    patientId int NOT NULL,
	dni varchar(8) NOT NULL,
    name varchar(250) NOT NULL,
    address varchar(250) NULL,
    mail varchar(200) NULL,
    genre varchar(10) NOT NULL,
    age varchar(3) NULL,
    CONSTRAINT Pk_Patient PRIMARY KEY (patientId)
);

CREATE TABLE IF NOT EXISTS LabDB.petitions (
    petitionId int AUTO_INCREMENT NOT NULL,
    loadDate datetime NOT NULL,
    deliveryDate datetime NOT NULL,
    CONSTRAINT Pk_Petition PRIMARY KEY (petitionId)
);

CREATE TABLE IF NOT EXISTS LabDB.results (
    resultId int NOT NULL,
    resultValue varchar(200) NOT NULL,
    resultType varchar(20) NOT NULL,
    CONSTRAINT Pk_Result PRIMARY KEY (resultId)
);

CREATE TABLE IF NOT EXISTS LabDB.practices (
    practiceId int AUTO_INCREMENT NOT NULL,
    practiceCode int NOT NULL,
    practiceName varchar(255) NOT NULL,
    practiceLength varchar(250) NOT NULL,
    CONSTRAINT Pk_Practice PRIMARY KEY (practiceId)
);

CREATE TABLE IF NOT EXISTS LabDB.stations (
    stationId int NOT NULL,
    address varchar(250) NOT NULL,
    techUserId int NOT NULL,
    CONSTRAINT Pk_Station PRIMARY KEY Pk_Station (stationId),
    FOREIGN KEY (techUserId) REFERENCES users(userId)
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


CREATE TABLE IF NOT EXISTS LabDB.users_stations (
    userId int NOT NULL,
    stationId int NOT NULL,
    CONSTRAINT PK_Users_Stations PRIMARY KEY (userId, stationId),
    FOREIGN KEY (userId) REFERENCES users(userId),
    FOREIGN KEY (stationId) REFERENCES stations(stationId)
);

CREATE TABLE IF NOT EXISTS LabDB.results_petitions (
    petitionId int NOT NULL,
    resultId int NOT NULL,
    FOREIGN KEY (petitionId) REFERENCES petitions(petitionId),
    FOREIGN KEY (resultId) REFERENCES results(resultId)
);

CREATE INDEX idx_patient_name_dni ON LabDB.patients(name, dni);
CREATE INDEX idx_petition ON LabDB.petitions(petitionId);
CREATE INDEX idx_practices ON LabDB.practices(practiceId);
CREATE INDEX idx_user ON LabDB.users(userId);