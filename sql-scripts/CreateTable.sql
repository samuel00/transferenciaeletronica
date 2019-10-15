create table TAB_REQUISICAO (tar_id bigint not null auto_increment, tar_codigo integer not null, tar_contexto varchar(125) not null, tar_data date not null, tar_ip_origem varchar(15) not null, tar_path varchar(125) not null, tar_tempo_execucao bigint not null, tar_tipo varchar(6) not null, tar_uuid varchar(125) not null, primary key (tar_id));

create table TAB_REQUISICAO_ERRO (tae_id bigint not null auto_increment, tae_classe_ocorrencia varchar(255), tae_metodo_ocorrencia varchar(255), tae_motivo_ocorrencia longtext, tae_stacktrace longtext, tae_requisicao_id bigint not null, primary key (tae_id));

create table TAB_REQUISICAO_IP_BLOQUEADO (tarib_id bigint not null, tarib_data_insercao date not null, tarib_ip_bloqueado varchar(15) not null, primary key (tarib_id));

create table TAB_REQUISICAO_PARAMETRO (tap_id bigint not null auto_increment, tap_classe_invocada varchar(255) not null, tap_entrada longtext not null, tap_header longtext not null, tap_metodo_invocado varchar(255) not null, tap_saida longtext, tap_requisicao_id bigint not null, primary key (tap_id));

create table tab_transferencia (id bigint not null auto_increment, conta_destino varchar(255), conta_origem varchar(255), data_agendamento datetime, data_transferencia datetime, status integer, valor decimal(19,2), valorTaxa decimal(19,2), primary key (id));
