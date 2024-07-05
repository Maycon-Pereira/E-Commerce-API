CREATE TABLE carrinho_item (
    id char(100) not null,
    carrinho_id char(100) not null,
    product_id char(100) not null,
    name varchar(255) not null,
    quantity int not null,
    price double not null,
    description text,
    category_id char(100) not null,
    ativo tinyint,
    imagem mediumtext,
    primary key(id),
    FOREIGN KEY (carrinho_id) REFERENCES carrinho(id),
    index (carrinho_id)
);
