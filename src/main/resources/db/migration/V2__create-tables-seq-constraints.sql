CREATE TABLE tb_clientes (
    id_cliente NUMBER(19,0) PRIMARY KEY,
    nm_cliente VARCHAR2(30) NOT NULL,
    nm_email VARCHAR2(100) UNIQUE NOT NULL,
    nr_telefone VARCHAR2(11) UNIQUE NOT NULL,
    nr_cpf VARCHAR2(11) UNIQUE NOT NULL,
    dt_nascimento DATE NOT NULL,
    ds_senha VARCHAR2(150) NOT NULL,
    st_status CHAR(2),
	st_ativo NUMBER(1,0) NOT NULL,
	id_endereco NUMBER(19,0) NOT NULL
);

CREATE TABLE tb_enderecos (
    id_endereco NUMBER(19,0) PRIMARY KEY,
    nm_rua VARCHAR2(100) NOT NULL,
    nr_numero VARCHAR2(100) NOT NULL,
    nm_bairro VARCHAR2(100) NOT NULL,
    nm_cidade VARCHAR2(100) NOT NULL,
    ds_uf VARCHAR2(2) NOT NULL,
    nr_cep VARCHAR2(8) NOT NULL,
    nm_complemento VARCHAR2(100),
	st_ativo NUMBER(1,0) NOT NULL,
	nr_lat FLOAT(53) NOT NULL,
	nr_long FLOAT(53) NOT NULL
);

CREATE TABLE tb_servicos(
    id_servico NUMBER(19,0) PRIMARY KEY,
    nr_valor NUMBER(38,2) NOT NULL,
    cd_confirmacao VARCHAR2(12),
    dt_agendamento DATE NOT NULL,
    dt_criacao DATE NOT NULL,
    dt_atualizacao DATE NOT NULL,
    dt_vencimento DATE NOT NULL,
    dt_aceitacao DATE,
    dt_cancelamento DATE,
    st_conclusao CHAR(2),
    st_ativo NUMBER(1,0) NOT NULL,
    id_endereco NUMBER(19,0) NOT NULL,
    id_transportador NUMBER(19,0),
    id_cliente NUMBER(19,0) NOT NULL
);

CREATE TABLE tb_transportadores(
    id_transportador NUMBER(19,0) PRIMARY KEY,
    nm_transportador VARCHAR2(30) NOT NULL,
    nm_email VARCHAR2(100) NOT NULL,
    nr_cpf VARCHAR2(11) NOT NULL,
    nr_cnh VARCHAR2(9) NOT NULL,
    nr_telefone VARCHAR2(11) NOT NULL,
    dt_nascimento DATE NOT NULL,
    ds_senha VARCHAR2(150) NOT NULL,
    st_status CHAR(2),
    st_ativo NUMBER(1,0) NOT NULL,
    nr_raio_servico NUMBER(10,0) NOT NULL,
    id_endereco NUMBER(19,0) NOT NULL
);

CREATE TABLE tb_itens(
    id_item NUMBER(19,0) PRIMARY KEY,
    tp_item VARCHAR2(200) NOT NULL,
    nr_altura NUMBER(10,0),
    nr_comprimento NUMBER(10,0),
    nr_largura NUMBER(10,0),
    nr_peso NUMBER(10,0),
    ds_descricao VARCHAR2(255),
    id_servico NUMBER(19,0) NOT NULL
);

CREATE TABLE tb_logs(
    id_log NUMBER(19,0) PRIMARY KEY,
    nm_tabela VARCHAR2(30),
    id_pktabela NUMBER(19,0),
    tp_exec CHAR(6),
    tx_valorantigo CLOB,
    tx_valornovo CLOB,
    dt_criacao DATE
);

CREATE TABLE tb_descricoes(
    id_descricao CHAR(2) NOT NULL,
    nm_atributo VARCHAR2(30) NOT NULL,
    nm_descricao VARCHAR2(30) NOT NULL,
    st_ativo NUMBER(1,0) NOT NULL,
    dt_atualizacao DATE NOT NULL
);

-- O atributo id_endereco em tb_cliente Ã© uma chave estrangeira
-- Ele estÃ¡ ligado com id_endereco da tb_endereco

ALTER TABLE tb_clientes
	ADD CONSTRAINT fk_tb_clientes_tb_enderecos
	FOREIGN KEY (id_endereco)
	REFERENCES tb_enderecos(id_endereco);

-- O atributo id_cliente em tb_servicos Ã© uma chave estrangeira
-- Ele estÃ¡ ligado com id_cliente da tb_cliente

ALTER TABLE tb_servicos
    ADD CONSTRAINT fk_tb_servicos_tb_cliente
    FOREIGN KEY (id_cliente)
    REFERENCES tb_clientes(id_cliente);

-- O atributo id_endereco em tb_servicos Ã© uma chave estrangeira
-- Ele estÃ¡ ligado com id_endereco da tb_enderecos

ALTER TABLE tb_servicos
ADD CONSTRAINT fk_tb_servicos_tb_enderecos
FOREIGN KEY (id_endereco)
REFERENCES tb_enderecos(id_endereco);

-- O atributo id_transportador em tb_servicos Ã© uma chave estrangeira
-- Ele estÃ¡ ligado com id_transportador da tb_transportadores

ALTER TABLE tb_servicos
ADD CONSTRAINT fk_tb_servicos_tb_transportadores
FOREIGN KEY (id_transportador)
REFERENCES tb_transportadores(id_transportador);

-- O atributo id_servico em tb_itens Ã© uma chave estrangeira
-- Ele estÃ¡ ligado com id_servico da tb_servicos

ALTER TABLE tb_itens
ADD CONSTRAINT fk_tb_itens_tb_transportadores
FOREIGN KEY (id_servico)
REFERENCES tb_servicos(id_servico);


-- SEQ tb_clientes
CREATE SEQUENCE seq_tb_clientes
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;

-- SEQ tb_enderecos
CREATE SEQUENCE seq_tb_enderecos
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;

-- SEQ tb_servicos
CREATE SEQUENCE seq_tb_servicos
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;

-- SEQ tb_transportadores
CREATE SEQUENCE seq_tb_transportadores
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;

-- SEQ tb_itens
CREATE SEQUENCE seq_tb_itens
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;

-- SEQ tb_logs
CREATE SEQUENCE seq_tb_logs
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE;
