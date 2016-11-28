# spring-boot-newrelic-poc

Spring Boot New Relic PoC - Spring Boot instances monitored by New Relic

### Environment
	OS: Mac OS X
    JVM: Oracle Corporation 1.8.0_102
	Spring Boot: 1.4.2.RELEASE
	Mongodb: 3.2.9

### NewRelic
* Create account
* Get started with New Relic (Java)
* Download java-agent newrelic-java-${VERSION}.zip	
* Update extension.xsd, extension-example.xml, newrelic.yml, nrcerts in each application src/main/resource/newrelic folder

### Run
* Run Mondodb
* Run instance1 (from spring-boot-newrelic-instance1 folder)
`mvn clean install spring-boot:run -Drun.jvmArguments="-javaagent:./target/instance1.jar -Dnewrelic.config.file=./target/classes/newrelic/newrelic.yml -Dnewrelic.config.app_name=instance1"`
* Run instance2 (from spring-boot-newrelic-instance2 folder)
`mvn clean install spring-boot:run -Drun.jvmArguments="-javaagent:./target/instance2.jar -Dnewrelic.config.file=./target/classes/newrelic/newrelic.yml -Dnewrelic.config.app_name=instance2"`
* Run instance3 (from spring-boot-newrelic-client-emulator folder)
`mvn clean install spring-boot:run -Drun.jvmArguments="-javaagent:./target/instance3.jar -Dnewrelic.config.file=./target/classes/newrelic/newrelic.yml -Dnewrelic.config.app_name=instance3"`

### Queries
* Instance1: Save user
`curl -X POST http://localhost:8081/api/user | json_pp`
* Instance1: Save {bulkSize} users
`curl -X POST http://localhost:8081/api/user/bulk/4 | json_pp`
* Instance2: Find users
`curl -X GET http://localhost:8082/api/user | json_pp`

### New Relic
Applications statistics
<p align="center">
	<img src="https://github.com/JediVision/jedi-temple/blob/master/img/jedi-temple.jpg?raw=true" alt=""/>
</p>
Service Maps
<p align="center">
	<img src="https://github.com/JediVision/jedi-temple/blob/master/img/jedi-temple.jpg?raw=true" alt=""/>
</p>
<p align="center">
	<img src="https://github.com/JediVision/jedi-temple/blob/master/img/jedi-temple.jpg?raw=true" alt=""/>
</p>
