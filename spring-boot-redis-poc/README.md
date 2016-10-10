# spring-boot-redis-poc

Spring Boot Redis — Redis key, value storage(java objects)

### Environment
	OS: Mac OS X
	JVM: Oracle Corporation 1.8.0_51
	Spring Boot: 1.4.0.RELEASE
	Redis: 3.2.3

### Run
* Run Redis
* Run Application

### Queries

* Put key, value pair to storage :

*curl -X GET http://localhost:8080/jedi/put/yoda | json_pp*

* Get value by key:

*curl -X GET http://localhost:8080/jedi/get/yoda | json_pp*

