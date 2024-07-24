create table produtos (
    id char(100) not null,
    name varchar(255) not null,
    quantity int not null,
    price double not null,
    color varchar(255),
    size varchar(255),
    discount varchar(255),
    rating int,
    description text,
    category_id char(100) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    canceled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo tinyint,
    
    primary key(id)
);


