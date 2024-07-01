create table pedidos (
    id char(100) not null,
    product_id char(100) not null,
    user_id char(100) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    canceled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo tinyint,
    
    primary key(id)
);


