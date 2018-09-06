#!/usr/bin/env groovy

@Library('biblioteca-compartilhada') _
pipeline {
    environment {
        PROFILE= "${(env.BRANCH_NAME == 'master') ? 'producao' : env.BRANCH_NAME}"
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '1'))
        timeout(time: 20, unit: 'MINUTES')
        timestamps()
    }

    parameters {
        string(name: 'managed_server', defaultValue: "", description: 'Identifica o nome do managed server')
        string(name: 'jdk', defaultValue: "jdk8", description: 'Versao do Java')
        string(name: 'canal_slack', defaultValue: "#dev-java", description: 'Canal do slack para notificações')
        booleanParam(name: 'processamento', defaultValue: false, description: 'identifica se e uma app de processamento')
        booleanParam(name: 'wls_api', defaultValue: false, description: 'identifica se e uma app da infraestrutura do API (https://api.sefa.pa.gov.br)')
    }

    tools {
        maven 'Maven 3.3.9'
        jdk "jdk7"
    }

    agent any

    stages {
        stage("checkout-git") {
            steps {
                notificarSlack "STARTED", "${params.canal_slack}"
                script {
                    realizarCheckout()
                }
            }
        }

        stage("testes-java7") {
            when {
                expression { return  params.jdk != 'jdk8'}
            }
            steps {
                sh "mvn -B verify -P${PROFILE} -DskipTests"
            }
        }

        stage("testes-java8") {
            when {
                expression { return  params.jdk == 'jdk8'}
            }
            tools {
                jdk "jdk8"
            }
            steps {
                sh "mvn -B verify -P${PROFILE} -DskipTests"
            }
        }

        stage("analise-sonar") {
            steps {
                script {
                    def propriedades = carregarInformacoes()
                    executarSonar(propriedades)
                }
            }
        }

        stage('versionamento-java7') {
             when {
                expression { return params.jdk != 'jdk8'}
            }
            steps {
                script {
                    def propriedades = carregarInformacoes()
                    incrementarVersao(propriedades)
                } // script
            }
        } // stage - versionamento

         stage('versionamento-java8') {
             when {
                expression { return params.jdk == 'jdk8'}
            }
            tools {
                jdk "jdk8"
            }
            steps {
                script {
                    def propriedades = carregarInformacoes()
                    incrementarVersao(propriedades)
                } // script
            }
        } // stage - versionamento

        stage('arquivamento') {
            steps {
                script {
                    def propriedades = carregarInformacoes()
                    arquivarModulos(propriedades)
                }
            }
        } // stage arquivamento

        stage('undeploy-deploy') {
            steps {
                retry(3) {
                    script {
                        def propriedades = carregarInformacoes()
                        undeployDeploy(propriedades)
                    } // script
                }
            } // steps
        } // stage - undeploy-deploy
    } // final stages

    post {
        success {
             notificarSlack "SUCCESS", "${params.canal_slack}"
        }
        failure {
            notificarSlack "FAILURE", "${params.canal_slack}"
             script {
                if (env.BRANCH_NAME == "master") {
                    mail to: "${env.EMAIL_NOTIFICACAO_FALHA}",
                    subject: "${currentBuild.fullDisplayName}",
                    body: "${env.MENSAGEM_PADRAO} falhou!"
                }
            }
        }
        unstable {
            notificarSlack "UNSTABLE", "${params.canal_slack}"
        }
        // always {
        // }
    } // post
} // pipeline