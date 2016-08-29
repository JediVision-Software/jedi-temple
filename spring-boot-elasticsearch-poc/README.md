# spring-boot-elasticsearch-poc

Spring Boot ElasticSearch — "One-To-Many" document relation; ElasticSearch index, search API

### Environment
	OS: Mac OS X
	JVM: Oracle Corporation 1.8.0_51
	Spring Boot: 1.4.0.RELEASE
	ElasticSearch: 2.3.5

### ElasticSearch
1. Open $ES_HOME/config/elasticsearch.yml
2. Configure "cluster.name" parameter to "starwars"

### Run
* Run ElasticSearch
* Run Application

### Queries

* Index:

*curl -X GET http://localhost:8080/elastic/jedi/index | json_pp*

* Find All (via SpringData):

*curl -X GET http://localhost:8080/elastic/jedi/findAll | json_pp*

* Find All sorted by "force", "age" DESC (via SpringData):

*curl -X GET http://localhost:8080/elastic/jedi/findAll/sorted | json_pp*

* Find by force between (range) (via SpringData):

*curl -X GET http://localhost:8080/elastic/jedi/findByForceBetween/500/900 | json_pp*

* Find by rank (YOUNGLING, PADAWAN, MASTER) and order by age (via SpringData):

*curl -X GET http://localhost:8080/elastic/jedi/findByRankOrderByAge/MASTER | json_pp*

* Find by force greater than force and rank is (via ElasticSearch Template):

*curl -X GET http://localhost:8080/elastic/jedi/findByForceGreaterThanAndRankIs/800/YOUNGLING | json_pp*

* Count by force less than force (via ElasticSearch Template):

*curl -X GET http://localhost:8080/elastic/jedi/countByForceLessThan/1100 | json_pp*
