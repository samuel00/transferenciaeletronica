create table TAB_REQUISICAO (tar_id bigint not null auto_increment, tar_codigo integer not null, tar_contexto varchar(125) not null, tar_data date not null, tar_ip_origem varchar(15) not null, tar_path varchar(125) not null, tar_tempo_execucao bigint not null, tar_tipo varchar(6) not null, tar_uuid varchar(125) not null, primary key (tar_id));

create table TAB_REQUISICAO_ERRO (tae_id bigint not null auto_increment, tae_classe_ocorrencia varchar(255), tae_metodo_ocorrencia varchar(255), tae_motivo_ocorrencia longtext, tae_stacktrace longtext, tae_requisicao_id bigint not null, primary key (tae_id));

create table TAB_REQUISICAO_IP_BLOQUEADO (tarib_id bigint not null, tarib_data_insercao date not null, tarib_ip_bloqueado varchar(15) not null, primary key (tarib_id));

create table TAB_REQUISICAO_PARAMETRO (tap_id bigint not null auto_increment, tap_classe_invocada varchar(255) not null, tap_entrada longtext not null, tap_header longtext not null, tap_metodo_invocado varchar(255) not null, tap_saida longtext, tap_requisicao_id bigint not null, primary key (tap_id));

create table tab_transferencia (id bigint not null auto_increment, conta_destino varchar(255), conta_origem varchar(255), data_agendamento date, data_transferencia date, status integer, valor decimal(19,2), valorTaxa decimal(19,2), primary key (id));

create table TAB_SEGURANCA_USUARIO (id bigint not null auto_increment, login varchar(255) not null, senha varchar(255) not null, admin boolean, primary key (id));

insert into TAB_SEGURANCA_USUARIO values (1, 'adm', '$2a$10$aUfiyUIpdfhD3Kko6vzZqOUtn2zanHRzVolnGjNrIK14zg/C9zBky', true);

insert into TAB_SEGURANCA_USUARIO values (2, 'front-user', '$2a$10$aUfiyUIpdfhD3Kko6vzZqOUtn2zanHRzVolnGjNrIK14zg/C9zBky', false);