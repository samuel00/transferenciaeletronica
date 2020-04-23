# Template

## Tecnologias Utilizadas (informações das versões no arquivo `pom.xml`)

* Java 8.X
* [Maven](https://maven.apache.org/) 3.X
* [Spring](https://spring.io/)
* [JPA 2.0 | Hibernate](http://hibernate.org/)
* [Jetty](http://www.eclipse.org/jetty/) (desenvolvimento)
* [Weblogic 12c](http://www.oracle.com/technetwork/middleware/weblogic/)
* [AngularJS](https://angularjs.org/)
* [Liquibase](http://www.liquibase.org/)
* [RESTful](https://pt.wikipedia.org/wiki/REST)
* [Docker](https://www.docker.com/)
* [Fabric8 io](https://fabric8.io/)
* [SpotBugs](https://spotbugs.github.io/)
* [SonarQube](https://www.sonarqube.org/)
* [Terraform](https://www.terraform.io/)


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
├── transferencia-eletronica-doc
|   ├── documentacao-transferencia-eletronica.pdf
|   └── transferencia-eletronica.postman_collection.json|   
├── transferencia-eletronica-ui
|   ├── src
|   └── pom.xml
├── transferencia-eletronica-scripts
|   ├── docker-script
|   |	 ├── mysql
|   |	 |	 ├── sql-script
|   |	 |	 |	 └── CreateTable.sql
|   |	 |	 └── Dockerfile
|   |	 ├── sonarqube
|   |	 |	 └── Dockerfile
|   |	 ├── command-docker-swarm.txt
|   ├── terraform-script
|   |	 ├── aws_ami.tf
|   |	 ├── main.tf
|   |	 ├── script.sh
|   |	 └── variable.tf
├── README.md
├── Dockerfile
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
    
    
## SonarQube

1. Navegue até o diretório transferencia-eletronica-script/docker-script/sonarqube

		# Execute o comando
		$ docker build .
		# Em seguida
		$ docker run -p 9000:9000 sonarqube
		# Volte a raiz do projeto
		$ cd ../../../
		# Execute o comando
		$ mvn clean install
		# Execute o comando
		$ mvn sonar:sonar
		# Acesse a URL localhost:9000
		

## Subindo a aplicação

1. Baixando as dependências, executanto os testes e subindo no Jetty (caso queira desabilitar o start do Jetty após o install, basta alterar o plugin no arquivo `transferencia-eletronica-api/pom.xml`):

        $ mvn clean install
        Acesse http://localhost:9091/transferencia-eletronica-api/
        Acesse http://localhost:9091/transferencia-eletronica-ui/#

2. Configurando para o Eclipse, execute dentro do diretório do projeto

        Basta importar o projeto como **Maven Project**.

3. Informações para criação dos DataSource se encontram no arquivo `core.properties`.

4. Excutando o projeto

	4.1 Com Fabric8 io:

		# Construindo Imagem
		$ mvn clean install
		$ mvn docker:build --non-recursive
		# Executando a imagem
		$ mvn docker:run --non-recursive
		# Para testar se está funcionando
		$ curl http://localhost:8080/transferencia-eletronica-api/api/public/status	
		# Front-End - Acesse o navegador e adicione o endereço
		  http://localhost:8080/transferencia-eletronica-extranet-ui/#/transferencia

		  
	4.2 Com Terraform io (Necessário uma conta na AWS):

		# Navegando até o diretório
		$ cd transferencia-eletronica-script/terraform-script/
		# Iniciando Terraform
		$ terraform init
		# Planejando a infraestrutura
		$ terraform plan	
		# Criando a infra-estrutura
		$ terraform apply	--auto-approve
		
		Para finalizar acesse o arquivo gerado chamado public_ips.txt que terá o link
		da aplicação deployada na AWS 
		ex: ip.publico.na.aws:8080/transferencia-eletronica-extranet-ui/


    4.3 API no Weblogic:

        $ cd transferencia-eletronica-api
        $ mvn clean package wls:deploy
        Acesse http://localhost:7001/transferencia-eletronica-api/

    4.4 UI é constituído por HTML, JS e CSS, basta colocar como um diretório no Weblogic, Apache ou um Servlet Conatainer, como o Jetty.

        $ Acesse http://{servidor}:{porta}/transferencia-eletronica-ui/#

5. Logs
A aplicação além de enviar os logs para o console guarda no diretório 

        /u02/logs/transferencia-eletronica/transferencia-eletronica.log


## Observações

* Para alterações no módulo 'core', é necessário tem executar `mvn install` para que o módulo seja colocado no repositório local;
* DataSource de desenvolvimento é criado pelo Spring, na classe `ConfiguracaoJPADesenvolvimento.java`, sendo que as informações de conexão são lidas do arquivo `core.properties` do módulo core.
