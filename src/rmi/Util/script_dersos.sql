create database dersos;
use dersos;

    
create table pessoa (
	idPessoa int not null auto_increment primary key,
    nome varchar(35) not null,
    cpf varchar(11) not null unique,
    rg varchar(10) not null unique,
    telefone varchar(10) ,
    celular varchar(11)
);


create table cliente(
	idCliente int not null auto_increment primary key,
    tipo varchar(20) not null,
    idPessoa int not null,
    constraint FK_CLIENTE 
    foreign key (idPessoa) references pessoa(idPessoa)
);    


create table funcionario(
	idFuncionario int not null auto_increment primary key,
	idPessoa int not null,
    salario float not null,
    especialidade varchar(25) not null,
    senha varchar (16) not null,
    constraint FK_PESSOA_FUNCIONARIO
    foreign key (idPessoa) references pessoa(idPessoa)
);


create table produto(
	idProduto int not null auto_increment primary key,
    nome varchar(20) not null,
    preco float not null,
    quantidade int not null
);     


create table venda(
	idVenda int not null auto_increment primary key,
	idCliente int not null,
	idFuncionario int not null,
    dataVenda date not null,
    valorTotal float,
    constraint FK_VENDA_CLIENTE 
    foreign key (idCliente) references cliente(idCliente),
    constraint FK_VENDA_FUNCIONARIO
    foreign key (idFuncionario) references funcionario(idFuncionario)
);  


create table carrinho(
	idCarrinho int not null primary key,
    idVenda int not null,
    idProduto int not null,
    quantidadeItemVenda int not null,
    
    constraint FK_CARRINHO_VENDA 
    foreign key (idVenda) references venda(idVenda),
    constraint FK_CARRINHO_PRODUTO
    foreign key (idProduto) references produto(idProduto)
); 


create table servico(
	idServico int not null auto_increment primary key,
    descricaoServico varchar(30) not null
);


create table ordem_servico(
	idOrdemServico int not null auto_increment primary key,
    idFuncionarioExecutaOS int not null,
    idVenda int not null,
    idServicoAtual int not null,
    descricaoOrdem varchar(20),
    completado boolean not null,
    dataHoraInicio datetime,
    dataHoraFim datetime,
    
    constraint FK_OS_FUNCIONARIO 
    foreign key (idFuncionarioExecutaOS) references funcionario(idFuncionario),
    constraint FK_OS_VENDA
    foreign key (idVenda) references venda(idVenda),
    constraint FK_OS_SERVICO_EXECUTANDO
    foreign key (idServicoAtual)references servico(idServico)
);

