DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS course_type;
DROP TABLE IF EXISTS intern;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS trainer;


CREATE TABLE course_type (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  type_title VARCHAR(250) NOT NULL
);

INSERT INTO course_type (type_title) VALUES
  ('Spring'),
  ('React'),
  ('Angular');

CREATE TABLE trainer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  phone VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);

INSERT INTO trainer (name,address,phone,email) VALUES
  ('SOGETI','Paris','+33512345678','sogeti@email.com'),
  ('DUPONT','Lille','+33512345679','dupont@email.com');

 CREATE TABLE course (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  id_type VARCHAR(250) NOT NULL,
  id_trainer VARCHAR(250) NOT NULL,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(250) DEFAULT NULL,
  start_date DATE DEFAULT NULL,
  end_date DATE DEFAULT NULL
);

INSERT INTO course (id_type, id_trainer, title, description,start_date,end_date) VALUES
  (1,2,'Initiation à Spring boot','Une formation qui donne les notions de base de Spring boot.','2020-01-17','2020-01-20'),
  (2,1,'Formation React','Une formation React pour les experts.','2020-03-26','2020-03-31'),
  (3,1,'Formation Angular','Une formation Angular pour les débutants.','2020-04-23','2020-04-26');

CREATE TABLE intern (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  email_person VARCHAR(250) NOT NULL
);

INSERT INTO intern (email_person) VALUES
  ('said@email.com'),
  ('youcef@email.com'),
  ('mamado@email.com'),
  ('paul@email.com');

CREATE TABLE review (
  course_id VARCHAR(250) NOT NULL,
  intern_id VARCHAR(250) NOT NULL,
  created_on DATE NOT NULL,
  score INT NOT NULL,
  PRIMARY KEY (course_id, intern_id)
);

INSERT INTO review (intern_id,course_id,created_on,score) VALUES
  (1,2,'2020-01-02',3),
  (1,3,'2020-01-02',5),
  (2,1,'2020-01-02',4),
  (3,3,'2020-01-02',5);
