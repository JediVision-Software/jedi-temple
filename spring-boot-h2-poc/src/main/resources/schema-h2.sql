DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS jedi;
DROP TABLE IF EXISTS rating;

CREATE TABLE equipment (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  type varchar(45) NOT NULL,
  value int(11) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE jedi (
  id int(11) NOT NULL AUTO_INCREMENT,
  full_name varchar(45) NOT NULL,
  rank varchar(45) NOT NULL,
  age int(11) NOT NULL,
  force int(11) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE rating (
    id int(11) NOT NULL AUTO_INCREMENT,
    rate int(11) NOT NULL,
    equipment_id int(11) NOT NULL,
    jedi_id int(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (jedi_id) REFERENCES jedi (id) ON DELETE NO ACTION ON UPDATE NO ACTION);