
#Create NetWork
docker network create --driver overlay samuel-santana-network

docker service create --name mysqldb -p 3306:3306 --network samuel-santana-network samuelsantana/mysql


docker service create --name transferencia-eletronica-docker -p 8080:8080 --network samuel-santana-network samuelsantana/transferencia-eletronica-docker

#Show Docker Swarm Token
$ docker swarm join-token manager
$ docker swarm join-token worker



#Init Docker Service
sudo service docker start
