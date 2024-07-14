DROP TABLE IF EXISTS EZ_FASTFOOD.CUSTOMER;
CREATE SCHEMA IF NOT EXISTS EZ_FASTFOOD;

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CUSTOMERS(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14),
    email VARCHAR(255)
);

INSERT INTO EZ_FASTFOOD.CUSTOMERS (name, cpf, email)
VALUES 
('Thaynara da Silva', '406.000.738-08', 'thaynara@example.com'),
('Flavio da Silva', '121.000.000-01', 'flavio@hotmail.com'),
('Flavio Fernando da Silva', '000.000.000-00', 'fernando@hotmail.com');
