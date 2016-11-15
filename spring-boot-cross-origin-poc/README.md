# spring-boot-cross-origin-poc

Spring Boot Cross Origin PoC — Cross Origin via annotation 

### Environment
	OS: Mac OS X
	JVM: Oracle Corporation 1.8.0_101
	Spring Boot: 1.4.1.RELEASE
	
### Run
* Run Application `./gradlew bootRun`
* Go to src/main/resources/under-web-server 
* Run web-server `python -m SimpleHTTPServer 8000`
* Open `http://localhost:8000/cross-origin.html`

