DROP DATABASE escola;

CREATE DATABASE escola;

USE escola;

CREATE TABLE curso (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  semestres int(10) unsigned NOT NULL,
  valor float NOT NULL,
  PRIMARY KEY (id)
);
