# spring-boot-casssandra-cluster-poc

Spring Boot Cassandra Cluster PoC - Cassandra Cluster + Nginx (RoundRobin three Spring Boot apps)

## Environment
	OS: Mac OS X
    JVM: Oracle Corporation 1.8.0_102
	Spring Boot: 1.4.2.RELEASE
	Gradle: 3.0

### Install cassandra with multiple nodes
* Download cassandra:
`http://cassandra.apache.org/download/`
* Follow video to setup cassandra or read Cassandra.md:
`https://www.youtube.com/watch?v=oHMJrhMtv3c`

### Run cassandra nodes:
`sudo cassandra` - port 7199
`sudo cassandra2` - port 7188
`sudo cassandra3` - port 7177

### Install Nginx load balancer:
* Install Nginx with brew:
`brew install nginx`
* Find Nginx location path:
`brew info nginx`
`(usually it is /usr/local/etc/nginx)`
* Configure Nginx:
 1) copy nginx.conf from project root to /usr/local/etc/nginx/
* Run Nginx
`sudo nginx`

### Create cassandra keyspace
`cqlsh -f database_creation.cql`

### Run projects
	cd spring-boot-cassandra
	- run first node project `./gradlew node1 bootRun`
	- run second node project `./gradlew node2 bootRun`
	- run third node project `./gradlew node3 bootRun`

### Populate some data
* Index:
`curl -X GET http://localhost:8080/jedi/index | json_pp`

### Get all data
* FindAll
`curl -X GET http://localhost:8080/jedi/findAll | json_pp`