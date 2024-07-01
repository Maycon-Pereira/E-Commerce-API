CREATE TABLE carrinho_itens (
    id CHAR(100) NOT NULL,
    carrinho_id CHAR(100),
    produto_id CHAR(100),
    quantidade INT,
    ativo TINYINT,
    PRIMARY KEY (id),
    FOREIGN KEY (carrinho_id) REFERENCES carrinhos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);