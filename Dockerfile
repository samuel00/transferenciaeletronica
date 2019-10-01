FROM tomcat

LABEL maintainer="samuel.santana"

ADD transferencia-eletronica-api/target/transferencia-eletronica-api.war /usr/local/tomcat/webapps/
ADD transferencia-eletronica-extranet-ui/target/transferencia-eletronica-extranet-ui.war /usr/local/tomcat/webapps/


EXPOSE 8080

CMD ["catalina.sh", "run"]