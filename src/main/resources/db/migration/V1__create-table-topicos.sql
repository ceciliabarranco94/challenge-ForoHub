create table topicos(

    id bigint not null auto_increment,
    titulo varchar(150) not null unique,
    mensaje varchar(500) not null unique,
    fecha_creacion datetime not null default current_timestamp,
    status varchar(20) not null default 'ABIERTO',
    autor varchar(100) not null,
    curso varchar(250) not null,

    primary key(id)

);

