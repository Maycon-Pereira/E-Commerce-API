create table produtos (
    id char(100) not null,
    name varchar(255) not null,
    quantity int not null,
    price double not null,
    color varchar(255),
    size varchar(255),
    discount varchar(255),
    description text,
    category_id char(100) not null,
    ativo tinyint,
    
    primary key(id)
);


