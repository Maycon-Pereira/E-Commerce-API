create table usuarios (

    id bigint not null auto_increment,
    username varchar(255) not null,
    user_password varchar(255) not null,
    email varchar(255) not null unique,
    tipo varchar(100) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ativo tinyint,

    primary key(id)
)