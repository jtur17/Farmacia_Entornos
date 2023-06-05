CREATE DATABASE farmacia;

USE farmacia;

CREATE TABLE doctor (
    mail VARCHAR(50) PRIMARY KEY,
    pass VARCHAR(100),
    name VARCHAR(100),
    last_log DATE,
    session INT(10)
);

CREATE TABLE patient (
    mail VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE xip (
    id INT(10) PRIMARY KEY,
    doctor_mail VARCHAR(50),
    id_medicine INT,
    id_patient VARCHAR(50),
    date DATE,
    FOREIGN KEY (doctor_mail) REFERENCES doctor(mail),
    FOREIGN KEY (id_patient) REFERENCES patient(mail)
);

CREATE TABLE medicine (
    id INT(10) PRIMARY KEY,
    name VARCHAR(50),
    tmax FLOAT(50),
    tmin FLOAT(50)
);