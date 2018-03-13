CREATE DATABASE IF NOT EXISTS Pais;

USE Pais;

CREATE TABLE IF NOT EXISTS paises (
    id int auto_increment primary key not null,
    nome varchar(100) not null,
    populacao bigint not null,
    area decimal(15,2) not null
    );
    

INSERT INTO paises(nome,populacao,area) VALUES('Brasil',45670000,5464564574.00);
INSERT INTO paises(nome,populacao,area) VALUES('Espanha',21334678,231245435.00);
INSERT INTO paises(nome,populacao,area) VALUES('África do Sul',53453477,23124354.00);
INSERT INTO paises(nome,populacao,area) VALUES('China',534543657767,38787655.00);
INSERT INTO paises(nome,populacao,area) VALUES('Colombia',243254645645,4356767.00);

SELECT * FROM paises;