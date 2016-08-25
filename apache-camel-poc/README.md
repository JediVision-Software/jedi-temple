# apache-camel-poc

Apache Camel — ActiveMQ/RabbitMQ, TCP/HTTP, IMAP/POP3/SMTP, Timer, ElasticSearch, Spring integration

## Environment
	OS: Mac OS X
	JVM: Oracle Corporation 1.8.0_51

### apache-camel-elasticsearch
* Configure ElasticSearch clusterName ($ES_HOME/config/elasticsearch.yml)
* Configure application ElasticSearch attributes (ip, port, clusterName, indexName, indexType)
* Run Elasticsearch
* Run Application main method
* Verify. Command:
curl -X POST 'localhost:9200/jedis/_search?pretty' -d '{
  "fields": ["fullName", "age"]
}'

### apache-camel-http
* Play: curl -X POST -H 'Content-Type: text/plain' -d 'rawBody' http://localhost:8888/jedi
* Exit: curl -X POST -H 'Content-Type: text/plain' -d 'exit' http://localhost:8888/jedi

### apache-camel-imap
* Configure email/password
* Change POP/IMAP settings to allow IMAP for account at email account

### apache-camel-pop3
* Configure email/password
* Change POP/IMAP settings to allow POP3 for account at email account

### apache-camel-rabbitmq
* Run RabbitMQ
* Run Application main method

### apache-camel-rabbitmq-and-springbean
* Run RabbitMQ
* Run Application main method

### apache-camel-smtp
* Configure email/password
* Run Application main method

### apache-camel-tcp
* Run "telnet localhost 7000"
* Play: type any line
* Exit: type "exit"

#### Read more: http://camel.apache.org/components.html