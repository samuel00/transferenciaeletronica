FROM mysql

# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup

ENV MYSQL_DATABASE transferenciaeletronica
ENV MYSQL_ROOT_PASSWORD root

ADD sql-script/ /docker-entrypoint-initdb.d/

EXPOSE 3306
