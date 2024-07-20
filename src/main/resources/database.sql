DROP TABLE IF EXISTS EZ_FASTFOOD.CUSTOMERS;
CREATE SCHEMA IF NOT EXISTS EZ_FASTFOOD;


-- Sera utilizado futuramente quando for habilitar a camada de seguranca (login com cpf e senha)
/*CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CUSTOMERS(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255),
    password VARCHAR (15)
);

INSERT INTO EZ_FASTFOOD.CUSTOMERS (name, cpf, email,password)
VALUES 
('Thaynara da Silva', '406.000.738-08', 'thaynara@example.com','senhA123'),
('Flavio da Silva', '121.000.000-01', 'flavio@hotmail.com','fl@vio123'),
('Wagner Cruz', '888.000.000-08', 'wagner@hotmail.com','w@gner123'),
('Flavio Fernando da Silva', '000.000.000-00', 'fernando@hotmail.com','f$rnando123');

*/


CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CUSTOMERS(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255)
);

INSERT INTO EZ_FASTFOOD.CUSTOMERS (name, cpf, email)
VALUES 
('Thaynara da Silva', '406.000.738-08', 'thaynara@example.com'),
('Flavio da Silva', '121.000.000-01', 'flavio@hotmail.com'),
('Wagner Cruz', '888.000.000-08', 'wagner@hotmail.com'),
('Flavio Fernando da Silva', '000.000.000-00', 'fernando@hotmail.com');
