# spring-cloud-microservices-poc

Spring Cloud Microservices PoC — Microservice architecture: Eureka, Ribbon, Hystix, Zuul + Docker-Compose etc.

### Environment
	OS: Mac OS X
	JVM: Oracle Corporation 1.8.0_102
	Spring Cloud: 1.4.0.RELEASE
	Mongodb: 3.3

### Application description

##### configuration-service
HTTP, resource-based API for external configuration
##### discovery-service
Find services, allows communication between them
##### gateway-service
Server-side aggregation endpoint, uses Ribbon as load balancer
##### monitoring-service
Monitoring via Hystrix metrics in real time
##### task-service
Task business logic: saving, deleting, searching
##### user-service
Task client; User business logic: saving, deleting, searching

### Installation guide 

- Create Mongodb databases: mongo_docker_poc_tasks, mongo_docker_poc_users

### Run

- Run Mongodb
- Run MySQL
- Run "mvn clean spring-boot:run" in each service

### Sequence
- discovery-service
- configuration-service
- gateway-service
- task-service
- user-service
- monitoring-service

### Docker

- Run "mvn clean install" in project directory
- Run "export  DOCKER_IP=${DOCKER_IP:-0.0.0.0}"
- Run "cd docker"
- Run "docker-compose up"




