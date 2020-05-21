FROM ubuntu:16.04

# https://hub.docker.com/u/samuelsantana
LABEL maintainer="samuel.santana"

ENV KAFKA_HOME /usr/local/kafka
ENV KAFKA_SSL /var/private/ssl
ADD ./script-kafka/start-kafka.sh /scripts/
ENV CONFIG_SERVER /config_server
 
# install java + others
RUN apt-get update && apt-get install -y \
  wget \
  openjdk-8-jdk

RUN chmod +x /scripts/start-kafka.sh
RUN mkdir /config_server

# config file server properties
ADD ./script-kafka/server.properties ${CONFIG_SERVER}/
ADD ./script-kafka/server.truststore.jks ${KAFKA_SSL}/
ADD ./script-kafka/server.keystore.jks ${KAFKA_SSL}/
 
# install kafka
RUN wget https://downloads.apache.org/kafka/2.5.0/kafka_2.12-2.5.0.tgz && \
  tar -xzf kafka_2.12-2.5.0.tgz && \
  mv kafka_2.12-2.5.0 $KAFKA_HOME
 
CMD ["/scripts/start-kafka.sh"]

EXPOSE 9092 2181