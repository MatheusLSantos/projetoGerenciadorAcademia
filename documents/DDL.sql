create database bdtpacademia;

create table aluno(
	id int auto_increment primary key,
    cpf varchar(14) not null unique,
    nome varchar(50) not null,
    dataNascimento char(10),
    telefone varchar(15),
    email varchar(50),
    endereco varchar(60)
);

create table treino(
	id_Aluno int,
    id char(3),
    tipoTreino char(1) not null,
    grupoMuscular varchar(20),
    primary key(id_Aluno, id),
    constraint alunoTreino foreign key (id_Aluno) references aluno(id)
);