FROM sonarqube


EXPOSE 9000




#docker build -t ubuntu-test:latest -f Dockerfile.custom .

# create a directory on host
# mkdir /server_data/sonarqube/extensions
# mkdir /server_data/sonarqube/data # this will be useful in saving startup time

# Start the container
# docker run -d \
#    --name sonarqube \
#    -p 9000:9000 \
#    -e SONARQUBE_JDBC_USERNAME=sonar \
#    -e SONARQUBE_JDBC_PASSWORD=SOME_PASSWORD \
#    -e SONARQUBE_JDBC_URL="jdbc:mysql://HOST_IP_OF_DB_SERVER:PORT/sonar?#useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance" \
#    -v /server_data/sonarqube/data:/opt/sonarqube/data \
#    -v /server_data/sonarqube/extensions:/opt/sonarqube/extensions \
#    sonarqube
