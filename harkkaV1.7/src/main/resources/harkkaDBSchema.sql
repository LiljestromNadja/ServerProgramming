SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS tuoteluokka; 
DROP TABLE IF EXISTS tuote; 
DROP TABLE IF EXISTS application_user;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE tuoteluokka
(id BIGINT NOT NULL AUTO_INCREMENT 
, tuoteluokkanimi VARCHAR(100) NOT NULL
,PRIMARY KEY (id)
);


INSERT INTO tuoteluokka (tuoteluokkanimi) 
VALUES ("Elektroniikka"),
,("Kodinkoneet")
,("Sisustus")
, ("Huonekalut");



CREATE TABLE tuote (
id BIGINT NOT NULL AUTO_INCREMENT
, tuotenimi VARCHAR(50) NOT NULL
, sijainti VARCHAR(50) NOT NULL
, kuvaus VARCHAR(300) NOT NULL
, hinta BIGINT
, tuoteluokkaid BIGINT
, PRIMARY KEY (id)
, FOREIGN KEY (tuoteluokkaid) REFERENCES tuoteluokka(id));

INSERT INTO tuote (tuotenimi, sijainti, kuvaus, hinta, tuoteluokkaid) 
VALUES ("Rannekello", "Helsinki", "Hieno digitaalinen rannekello", 25, 1)
,("Kuulokkeet", "Helsinki", "Langattomat pelikuulokkeet mikrofonilla, OVH 120 €.", 40, 1)
,("Pyykinpesukone", "Vantaa", "Candy-merkkinen päältä täytettävä pyykinpesukone, 7kg rumpu.", 120, 2)
,("Sohva", "Espoo", "3-istuttava harmaa sohva", 100,3);



CREATE TABLE application_user
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
, firstname VARCHAR(100) NOT NULL
, lastname VARCHAR(100) NOT NULL
,username VARCHAR(250) NOT NULL
,password_hash VARCHAR(250) NOT NULL)
, role  VARCHAR(100) NOT NULL;
INSERT INTO application_user (firstname, lastname, username, password_hash, role) 
VALUES ("","","user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER"),
("", "", "admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN"), 
("", "", "huolto", "$2a$10$lDtQP3VTBBHPocsCga.a6.iqXrQq2S3.nlaWJieniRwYlOUjpttUS", "ADMIN");


SELECT * FROM tuote;
SELECT * FROM tuoteluokka;
SELECT * FROM application_user;

