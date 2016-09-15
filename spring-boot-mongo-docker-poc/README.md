# spring-boot-mongo-docker-poc
Spring Boot Mongo Docker — Ubuntu container (Java, Maven, Git) with Mongodb instance inside

### Environment

    OS: Mac OS X
    JVM: Oracle Corporation 1.8.0_51
    Spring Boot: 1.4.0.RELEASE
    Mongodb: 3.2.9

### Docker
1) Download VirtualBox
2) Download Docker
3) Create Docker Machine
    docker-machine create --driver virtualbox default
4) Docker IP:
    docker ip
5) Open VirtualBox -> Open docker -> Settings -> Network (Advanced) 
    -> Port Forwarding -> Add 8787:8787 (Host Port, Guest Port)
6) Update "git clone" in Dockerfile with credentials and project (username/password/project)
6) Build image: 
    docker build -t spring-boot-mongo-docker-poc .
6a) Build image after commit (no cache):
    docker build --no-cache -t spring-boot-mongo-docker-poc .
7) Run container: 
    docker run -it -p 8787:8787/tcp spring-boot-mongo-docker-poc:latest
7a) Connect to running container: 
    docker run -it -p 8787:8787/tcp spring-boot-mongo-docker-poc:latest /bin/sh
    
### Mongo
1) Download Mongodb
2) Create folder /data/db
3) Run script 
    sudo chown -R `id -u` /data/db
4) Run ./mongod


### Run
* Run Mongodb
* Run Application

### Queries

* Index:

*curl -X GET http://localhost:8787/api/index | json_pp*

* Find All:

*curl -X GET http://localhost:8787/api/findAll | json_pp*

NOTE: http://localhost/ in docker replace with http://dockerIP/ or http://0.0.0.0/
