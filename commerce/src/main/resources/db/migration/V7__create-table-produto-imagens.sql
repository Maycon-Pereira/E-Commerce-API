CREATE TABLE produto_imagens (
    id CHAR(100) NOT NULL,
    produto_id CHAR(100) NOT NULL,
    imagem MEDIUMTEXT NOT NULL,
    ativo tinyint,
    PRIMARY KEY (id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);
