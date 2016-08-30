DROP TABLE IF EXISTS jedi;
DROP TABLE IF EXISTS equipment;

CREATE TABLE jedi (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  rank varchar(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE equipment (
    id int(11) NOT NULL AUTO_INCREMENT,
    type varchar(45) NOT NULL,
    value int(11) NOT NULL,
    jedi_id int(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (jedi_id) REFERENCES jedi (id) ON DELETE NO ACTION ON UPDATE NO ACTION);

INSERT INTO jedi VALUES
    (1,'Yoda','MASTER');

INSERT INTO equipment VALUES
    (1,'ARMOR',15,1);