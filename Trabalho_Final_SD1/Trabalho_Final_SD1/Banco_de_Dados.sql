DROP DATABASE banco;
CREATE DATABASE banco;
USE banco;

create table conta(
 nome_cliente varchar(255)not null,
 numero_conta int not null auto_increment,
 senha varchar(255) not null,
 saldo decimal(10,2) not null default 0,
 primary key(numero_conta)
);

create table movimentacao(
 id_transacao int not null auto_increment,
 numero_conta int not null ,
 data_transacao TIMESTAMP not null,
 valor decimal(10,2) not null default 0,
 tipo_transacao varchar(1) not null,
 tipo varchar(1) not null,
 conta_destino int,
 primary key(id_transacao)
);

INSERT INTO conta VALUES ('Daniel', 1, '12345', 10 );
INSERT INTO conta VALUES ('Luiggi', 2, '123456', 12 );
INSERT INTO conta VALUES ('Edson', 3, '1234567', 14);
INSERT INTO conta VALUES ('Deziderio', 4, '12345678',16);
INSERT INTO CONTA VALUES ('Marcorio', 5, '123456789',18);

INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (3, '2019-01-01 00:00:51', 100, 'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (3, '2019-01-01 00:00:51', 120, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (1, '2019-01-02 00:00:01', 200, 's', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (1, '2019-01-05 00:00:01', 150, 't', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (2, '2019-01-06 00:00:01', 900, 'd', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (4, '2019-01-01 00:00:51', 145, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (2, '2019-02-05 00:00:01', 180, 't', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (4, '2019-02-06 00:00:01', 550, 's', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (4, '2019-02-07 00:00:01', 300, 'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (1, '2019-01-01 00:00:51', 43, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (3, '2019-02-08 00:00:01', 480, 't', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-03-05 00:00:01', 10,  's', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-03-09 00:00:01', 345, 'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-01-01 00:00:51', 999, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (3, '2019-03-11 00:00:01', 990, 'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (4, '2019-01-01 00:00:51', 458, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (1, '2019-03-19 00:00:01', 150, 's', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (2, '2019-04-02 00:00:01', 50,  'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (2, '2019-01-01 00:00:51', 66, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-04-05 00:00:01', 75,  't', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (1, '2019-04-08 00:00:01', 425, 's', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-04-10 00:00:01', 50,  's', 'd', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (3, '2019-04-15 00:00:01', 100001,  'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-01-01 00:00:51', 99, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (1, '2019-05-05 00:00:01', 1001,  't', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-05-09 00:00:01', 1500,  'd', 'c', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (4, '2019-01-01 00:00:51', 222, 'c', 't', NULL);
INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (2, '2019-05-11 00:00:01', 1050,  's', 'd', NULL);

INSERT INTO movimentacao (numero_conta, data_transacao, valor, tipo_transacao, tipo, conta_destino) VALUES (5, '2019-04-10 00:00:01', 50,  's', 'd', NULL);
