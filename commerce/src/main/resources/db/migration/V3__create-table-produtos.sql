create table produtos (
    id char(100) not null,
    name varchar(255) not null,
    quantity int not null,
    price double not null,
    description text,
    category_id char(100) not null,
    user_id char(100) not null,
    ativo tinyint,
    
    primary key(id)
);


