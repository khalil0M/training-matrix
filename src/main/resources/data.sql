INSERT INTO course_type (type_title) VALUES
  ('Spring'),
  ('React'),
  ('Angular');

INSERT INTO trainer (name,address,phone,email) VALUES
  ('SOGETI','Paris','+33512345678','sogeti@email.com'),
  ('DUPONT','Lille','+33512345679','dupont@email.com');

INSERT INTO course (id_type, id_trainer, title, description,start_date,end_date) VALUES
  (1,2,'Initiation à Spring boot','Une formation qui donne les notions de base de Spring boot.','2020-01-17','2020-01-20'),
  (2,1,'Formation React','Une formation React pour les experts.','2020-03-26','2020-03-31'),
  (3,1,'Formation Angular','Une formation Angular pour les débutants.','2020-04-23','2020-04-26');

    INSERT INTO intern (email_person) VALUES
  ('said@email.com'),
  ('youcef@email.com'),
  ('mohamed@email.com'),
  ('adangote@sqli.com');

INSERT INTO review (intern_id,course_id,created_on,score) VALUES
  (1,2,'2020-01-02',3),
  (1,3,'2020-01-02',5),
  (2,1,'2020-01-02',4),
  (3,1,'2020-01-02',5),
  (4,3,'2020-01-02',2);
