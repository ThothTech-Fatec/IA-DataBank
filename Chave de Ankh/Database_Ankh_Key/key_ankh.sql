create database K_ankh;
use K_ankh;

CREATE TABLE users (
    user_id INT auto_increment,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(60) NOT NULL,
    PRIMARY KEY (`user_id`)
    )

CREATE TABLE pedidos (
    pedido_id INT auto_increment,
    user_id INT NOT NULL,
    data_pedido DATE NOT NULL,
    PRIMARY KEY (pedido_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE produtos (
    produto_id INT auto_increment,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (produto_id)
);

CREATE TABLE itens_pedido (
    item_id INT auto_increment,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (item_id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(pedido_id),
    FOREIGN KEY (produto_id) REFERENCES produtos(produto_id)
);




SELECT * FROM itens_pedido;
SELECT * FROM produtos;
SELECT * FROM pedidos;

INSERT INTO users (username, password) VALUES
('Alice', 'alicespassword'),
('Bob', 'bobspassword'),
('Charlie', 'charliespassword'),
('Dave', 'davespassword'),
('Eve', 'evespassword'),
('Frank', 'frankspassword'),
('Grace', 'gracespassword'),
('Hannah', 'hannahspassword'),
('Ian', 'ianspassword'),
('Jane', 'janespassword');





-- Inserir dados aleatórios na tabela pedidos
INSERT INTO pedidos (user_id, data_pedido) VALUES
(1, '2024-04-01'),
(2, '2024-04-02'),
(3, '2024-04-03'),
(4, '2024-04-04'),
(5, '2024-04-05'),
(6, '2024-04-06'),
(7, '2024-04-07'),
(8, '2024-04-08'),
(9, '2024-04-09'),
(10, '2024-04-10');


-- Inserir dados aleatórios na tabela produtos
INSERT INTO produtos (nome, preco) VALUES
('Camiseta', 29.99),
('Calça Jeans', 39.99),
('Tênis', 49.99),
('Relógio', 99.99),
('Óculos de Sol', 19.99),
('Bolsa', 59.99),
('Chapéu', 14.99),
('Jaqueta', 79.99),
('Bermuda', 24.99),
('Sapato', 69.99);
  

-- Inserindo dados na tabela itens_pedido com SELECT

-- Inserindo dados de pedido_id, produto_id e quantidade
INSERT INTO itens_pedido (pedido_id, produto_id, quantidade)
-- Selecionando dados para inserção
SELECT
    -- Seleciona o pedido_id da tabela pedidos
    pedidos.pedido_id,
    -- Gera um número aleatório entre 1 e o máximo produto_id na tabela produtos
    FLOOR(RAND() * (SELECT MAX(produto_id) FROM produtos)) + 1,
    -- Gera um número aleatório entre 1 e 5 para a quantidade de produtos
    FLOOR(RAND() * 5) + 1
-- Seleciona da tabela pedidos
FROM
    pedidos;




    
    
