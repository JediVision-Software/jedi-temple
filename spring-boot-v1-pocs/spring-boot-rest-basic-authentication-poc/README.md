# spring-boot-rest-basic-authentication-poc

Spring Boot REST Basic Authentication PoC — HTTP basic in-memory authentication

### Environment
	OS: Mac OS X
	JVM: Oracle Corporation 1.8.0_102
	Spring Boot: 1.4.1.RELEASE

### Run
* Run Application

### Queries

* Get Application Version: 

*curl -X GET http://localhost:8080/api/version | json_pp*

* Find events by ADMIN role [200 "OK"] option 1:

*curl -X GET admin:secret@localhost:8080/api/event | json_pp*

* Find events by ADMIN role [200 "OK"] option 2:

*curl -H "Authorization: Basic YWRtaW46c2VjcmV0" localhost:8080/api/event | json_pp*

* Find events by USER role [200 "OK"] option 1:

*curl -X GET user:password@localhost:8080/api/event | json_pp*

* Find events by USER role [200 "OK"] option 2:

*curl -H "Authorization: Basic dXNlcjpwYXNzd29yZA==" localhost:8080/api/event | json_pp*

* Find one event anonymous [401 "Unauthorized"]:

*curl -X GET http://localhost:8080/api/event/1 | json_pp*

* Find one event by ADMIN role [200 "OK"]:

*curl -X GET admin:secret@localhost:8080/api/event/1 | json_pp*

* Find one event by USER role [403 "Forbidden"]:

*curl -X GET user:password@localhost:8080/api/event/1 | json_pp*

