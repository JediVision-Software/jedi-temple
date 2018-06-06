DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id long(11) NOT NULL AUTO_INCREMENT,
  username varchar(200) NOT NULL,
  password varchar(200) NOT NULL,
  salary long(11) NOT NULL,
  age int(11) NOT NULL,
  PRIMARY KEY (id));