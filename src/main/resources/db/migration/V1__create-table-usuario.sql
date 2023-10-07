create table usuario(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    login varchar(20) not null,
    senha varchar(100) not null,
    role varchar(10) not null,
    primary key(id)
);

insert into usuario (nome, login, senha, role) value("Daniel","utzDaniel","$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.","ADMIN")