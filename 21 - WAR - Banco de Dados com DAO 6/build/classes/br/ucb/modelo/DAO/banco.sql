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

CREATE TABLE aluno (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  estCivil varchar(12) NOT NULL,
  email varchar(40) DEFAULT NULL,
  dtaNasc datetime NOT NULL,
  matricula int(10) unsigned DEFAULT NULL,
  ativo tinyint(1) DEFAULT NULL,
  idCurso int(10) unsigned NOT NULL,
  PRIMARY KEY (id),
  KEY fk_aluno_curso (idCurso),
  CONSTRAINT fk_aluno_curso FOREIGN KEY (idCurso) REFERENCES curso(id)
);

CREATE TABLE professor (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  estCivil varchar(12) NOT NULL,
  email varchar(40) DEFAULT NULL,
  dtaNasc datetime NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE curso_professor (
  idCurso int(10) unsigned NOT NULL,
  idProfessor int(10) unsigned NOT NULL,
  PRIMARY KEY (idCurso, idProfessor),
  KEY fk_curso (idCurso),
  KEY fk_professor (idProfessor),
  CONSTRAINT fk_curso FOREIGN KEY (idCurso) REFERENCES curso(id),
  CONSTRAINT fk_professor FOREIGN KEY (idProfessor) REFERENCES professor(id)
);
