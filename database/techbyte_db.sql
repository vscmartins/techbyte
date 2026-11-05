CREATE DATABASE IF NOT EXISTS techbyte_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE techbyte_db;

CREATE TABLE IF NOT EXISTS techbyte_db (
                                          id BIGINT NOT NULL AUTO_INCREMENT,
                                          nome VARCHAR(120) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
    );

INSERT INTO techbyte_db (nome, descricao, preco)
SELECT 'Notebook', 'Notebook para trabalho e estudo', 3500.00
    WHERE NOT EXISTS (SELECT 1 FROM techbyte_db WHERE nome = 'Notebook');

INSERT INTO techbyte_db (nome, descricao, preco)
SELECT 'Mouse sem fio', 'Mouse óptico com conexão USB', 99.90
    WHERE NOT EXISTS (SELECT 1 FROM techbyte_db WHERE nome = 'Mouse sem fio');

