# dropwizard-one-to-many-poc

DropWizard - One-To-Many relation with unit tests for DAO, service, resource layers

### Create Database

```sql
DROP DATABASE IF EXISTS `starwars`;
CREATE DATABASE `starwars`;
USE `starwars`;
```

### Create Tables

```sql

--
-- Table structure for table `jedi`
--

DROP TABLE IF EXISTS `jedi`;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jedi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `rank` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  `jedi_id` int(11),
  PRIMARY KEY (`id`),
  KEY `jedi_fk_idx` (`jedi_id`),
  CONSTRAINT `jedi_fk` FOREIGN KEY (`jedi_id`) REFERENCES `jedi` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### Postman collection
* postman folder in root project directory

### Run
* mvn clean install
* java -jar target/dropwizard-one-to-many-poc-1.0.jar server configuration.yml

### Queries
* Index :

*curl -X GET http://localhost:8080/index | json_pp*

* Find All:

*curl -X GET http://localhost:8080/jedi | json_pp*
