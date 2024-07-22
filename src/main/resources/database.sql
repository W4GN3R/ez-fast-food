DROP TABLE IF EXISTS EZ_FASTFOOD.CUSTOMER;
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


CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CUSTOMER(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255)
);


INSERT INTO EZ_FASTFOOD.CUSTOMER (name, cpf, email)
VALUES 
('Thaynara da Silva', '406.000.738-08', 'thaynara@example.com'),
('Flavio da Silva', '121.000.000-01', 'flavio@hotmail.com'),
('Wagner Cruz', '888.000.000-08', 'wagner@hotmail.com'),
('Flavio Fernando da Silva', '000.000.000-00', 'fernando@hotmail.com');


CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.CATEGORY (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.PRODUCT (
    id BIGSERIAL PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    description TEXT,
    unit_price DECIMAL,
    FOREIGN KEY (category_id) REFERENCES Category(ID)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.ORDER (
    id BIGSERIAL PRIMARY KEY,
    customer_id INT NULL,
    date DATE,
    total_price DECIMAL,
    status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES Customer(ID)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.ORDERITEM (
    id BIGSERIAL PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL,
    FOREIGN KEY (order_id) REFERENCES Order(ID),
    FOREIGN KEY (product_id) REFERENCES Product(ID)
);

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD.PAYMENT (
    id BIGSERIAL PRIMARY KEY,
    order_id INT,
    customer_id INT NULL,
    payment_date DATE,
    payment_price DECIMAL,
    payment_status VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES Order(ID),
    FOREIGN KEY (customer_id) REFERENCES Customer(ID)
);


