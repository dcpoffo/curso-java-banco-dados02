CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `unidadefederativa` varchar(25) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `entradas_saidas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modelo` varchar(100) DEFAULT NULL,
  `placa` varchar(8) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

create table vendas (
id int primary key auto_increment,
id_cliente int,
foreign key(id_cliente) references cliente(id),
valor double,
registro_Ativo bit
);

CREATE TABLE `contatos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `valor` varchar(100) DEFAULT NULL,
  `registro_ativo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `contatos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


