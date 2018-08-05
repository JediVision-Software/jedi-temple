# spring-boot-h2-gradle-poc

Spring Boot H2 Gradle — Embedded H2 database; jacoco, cobertura, gradle

### Environment
	OS: Linux Ubuntu 16.04 LTS
	Spring Boot: 1.4.0.RELEASE
	H2: 1.4.192
	Gradle: 2.14
	
### Run
* Run Application

### Coverage Reports
* Jacoco — build/reports/jacoco/index.html
* Cobertura — build/reports/cobertura/index.html

### Queries

* Find jedi by id:

*curl -X GET http://localhost:8080/jedi/1 | json_pp*

* Find jedi:

*curl -X GET http://localhost:8080/jedi | json_pp*

* Find jedi by rank:

*curl -X GET http://localhost:8080/jedi/search?rank=master | json_pp*