CREATE TABLE carrinho (
    id char(100) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ativo tinyint,
    user_id char(100) not null,
    primary key (id),
    index (id)
);
