#!/bin/bash


sleep 5;
echo "[UPDATING THE SYSTEM]";
sudo apt update && upgrade -y;
echo "[INSTALLING DOCKER]";
curl -fsSL https://get.docker.com/ | sh;
echo "[STARTING DOCKER]";
sudo service docker start;
echo "[DOWNLOADING MySQL]";
sudo docker pull samuelsantana/mysql;
echo "[DOWNLOADING PROJECT]";
sudo docker pull samuelsantana/transferencia-eletronica-docker;
echo "[CREATING CONTAINER MySQL]";
sudo docker run -p 3306:3306 --name mysqldb -d samuelsantana/mysql;
echo "[CREATING CONTAINER PROJECT]";
sudo docker run -p 8080:8080 --link mysqldb:mysqldb --name tranferenciaeletronica -d samuelsantana/transferencia-eletronica-docker;
echo "[STARTING CONTAINER MySQL]";
sudo docker start mysqldb;
echo "[STARTING CONTAINER PROJECT]";
sudo docker start tranferenciaeletronica;