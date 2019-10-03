# Template

## Build Status
* [![Build Status](http://x-oc-config.sefa.pa.gov.br/jenkins/buildStatus/icon?job=transferencia-eletronica/desenvolvimento)](http://x-oc-config.sefa.pa.gov.br/jenkins/job/transferencia-eletronica/job/desenvolvimento) - desenvolvimento
* [![Build Status](http://x-oc-config.sefa.pa.gov.br/jenkins/buildStatus/icon?job=transferencia-eletronica/homologacao)](http://x-oc-config.sefa.pa.gov.br/jenkins/job/transferencia-eletronica/job/homologacao) - homologacao
* [![Build Status](http://x-oc-config.sefa.pa.gov.br/jenkins/buildStatus/icon?job=transferencia-eletronica/master)](http://x-oc-config.sefa.pa.gov.br/jenkins/job/transferencia-eletronica/job/master) - master/producao

Esse transferencia-eletronica tem por objetivo exemplificar como ficará uma aplicação na arquitetura atual.
Para preparar o transferencia-eletronica para um **NOVO PROJETO** basta alterar as propriedades `nomeDoProjeto`, `dataSource` e `pacote` no arquivo `build.xml`, depois 
executar o seguinte comando na raíz do projeto:
```
$ mvn post-clean
````

- Não importar o projeto no eclipse antes de rodar o comando **post-clean**


## Tecnologias Utilizadas (informações das versões no arquivo `pom.xml`)

* Java 8.X
* [Maven](https://maven.apache.org/) 3.X
* [Spring](https://spring.io/)
* JPA 2.0|[Hibernate](http://hibernate.org/)
* [Jetty](http://www.eclipse.org/jetty/) (desenvolvimento)
* [Weblogic 12c](http://www.oracle.com/technetwork/middleware/weblogic/)
* [AngularJS](https://angularjs.org/)
* [Liquibase](http://www.liquibase.org/)
* [RESTful](https://pt.wikipedia.org/wiki/REST)
* [Docker] (https://www.docker.com/)
* [Fabric8 io] (https://fabric8.io/)


## Estrutura consolidada do projeto

```
transferencia-eletronica
├── transferencia-eletronica-api
|   ├── profiles
|   ├── src
|   └── pom.xml
├── transferencia-eletronica-core
|   ├── profiles
|   ├── src
|   └── pom.xml
├── transferencia-eletronica-db
|   ├── db
|   ├── db.changelog.xml
|   └── pom.xml
├── transferencia-eletronica-ui
|   ├── src
|   └── pom.xml
├── README.md
└── Dockerfile
└── pom.xml

```

### Descrição da estrutura

* transferencia-eletronica:
    * Projeto pai, "guarda-chuva", funciona como envelope dos módulos a seguir. Também utilizado para armazenar configurações Mavem reutilizáveis
* transferencia-eletronica-api (WAR):
    * Nesse módulo, ficará o serviço RESTful do projeto (caso necessário, pode ser SOAP também, Clients (WS)), controladores REST. Além disso pode comportar também implementações de Scheduler e Threads. Testes devem estar presentes também.
* transferencia-eletronica-db:
    * Todas as migrações do banco de dados, separados por changeset...
* transferencia-eletronica-ui:
    * o Nesse módulo ficará a VIEW (AngularJS, HTML, CSS), também terá testes unitários de view, se aplicável. Este módulo por conter apenas arquivos estáticos, não gera empacotamento WAR, podem ser implantado em qualquer web server.
* transferencia-eletronica-core (JAR):
    * o Esse é o módulo principal do projeto, nele consta os objetos de negócio do projeto! Por padrão, para cada model, deve ser criado seu respectivo Repositório (manipulação na base de dados) e Serviço (regras de negócio). Por exemplo para o modelo `Pessoa`, teremos o `PessoaRepositorio` e o `PesoaServico`, ou seja, os objetos de negócio e o regras de negócio ficarão nesse módulo. Este é dependência do módulo transferencia-eletronica-api.


## Subindo a aplicação

1. Após **clonar** o repositório na sua máquina, entre no diretório do projeto e mude para a branch de desenvolvimento **(se já não estiver nela)**:

        $ git branch desenvolvimento origin/desenvolvimento (caso a branch não exista localmente)
        $ git checkout desenvolvimento

2. Baixando as dependências, executanto os testes e subindo no Jetty (caso queira desabilitar o start do Jetty após o install, basta alterar o plugin no arquivo `transferencia-eletronica-api/pom.xml`):

        $ mvn clean install
        Acesse http://localhost:9091/transferencia-eletronica-api/
        Acesse http://localhost:9091/transferencia-eletronica-ui/#

3. Configurando para o Eclipse, execute dentro do diretório do projeto

        Basta importar o projeto como **Maven Project**.

4. Informações para criação dos DataSource se encontram no arquivo `core.properties`.

5. Excutando o projeto

	5.1 Com Fabric8 io:
	
		# Construindo Imagem
		$ mvn clean install
		$ mvn docker:build --non-recursive
		# Executando a imagem
		$ docker run -p 8080:8080 -d transferencia-eletronica/transferencia-eletronica-docker
		# Para testar se está funcionando
		$ curl http://localhost:8080/transferencia-eletronica-api/api/public/status	
		# Front-End - Acesse o navegador e adicione o endereço
		  http://localhost:8080/transferencia-eletronica-extranet-ui/#/transferencia	

    5.2 API no Weblogic:

        $ cd transferencia-eletronica-api
        $ mvn clean package wls:deploy
        Acesse http://localhost:7001/transferencia-eletronica-api/

    5.3 UI é constituído por HTML, JS e CSS, basta colocar como um diretório no Weblogic, Apache ou um Servlet Conatainer, como o Jetty.

        $ Acesse http://{servidor}:{porta}/transferencia-eletronica-ui/#

6. Logs
A aplicação além de enviar os logs para o console guarda no diretório 

        /u02/logs/transferencia-eletronica/transferencia-eletronica.log


## Observações

* Para alterações no módulo 'core', é necessário tem executar `mvn install` para que o módulo seja colocado no repositório local;
* DataSource de desenvolvimento é criado pelo Spring, na classe `ConfiguracaoJPADesenvolvimento.java`, sendo que as informações de conexão são lidas do arquivo `core.properties` do módulo core.
* Deve-se usar *weblogic/sefa123456* como usuário/senha do domínio local do seu Weblogic!