DROP TABLE IF EXISTS jedi;

CREATE TABLE jedi (
  id int(11) NOT NULL AUTO_INCREMENT,
  full_name varchar(45) NOT NULL,
  rank varchar(45) NOT NULL,
  age int(11) NOT NULL,
  force int(11) NOT NULL,
  PRIMARY KEY (id));