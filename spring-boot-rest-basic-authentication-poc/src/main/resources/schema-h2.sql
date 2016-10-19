DROP TABLE IF EXISTS event;

CREATE TABLE event (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  description varchar(100) NOT NULL,
  place varchar(100) NOT NULL,
  price int(11) NOT NULL,
  PRIMARY KEY (id));
