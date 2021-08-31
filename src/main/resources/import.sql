
INSERT INTO genders (image,name) VALUES ('img_accion','Accion'); #1
INSERT INTO genders (image,name) VALUES ('img_aventura','Aventura'); #2
INSERT INTO genders (image,name) VALUES ('img_infantil','infantil'); #3

INSERT INTO characters (age,history,image,name,weight) VALUES (25,'Le pica una araña y le da poderes','img_spdierman','peter parker',55); #1
INSERT INTO characters (age,history,image,name,weight) VALUES (40,'Es un cientifico inteligente y millonario','img_ironman','Ironman',60); #2
INSERT INTO characters (age,history,image,name,weight) VALUES (50,'Hijo del dios del trueno','img_thor','Thor',78); #3
INSERT INTO characters (age,history,image,name,weight) VALUES (15,'Es un raton divertido','img_micky','Mickey Mouse',25); #4

INSERT INTO movies (create_at,image,qualification,title,gender_id) VALUES ('2021-05-15','img_avenger',5,'Avengers 1',1); #1
INSERT INTO movies (create_at,image,qualification,title,gender_id) VALUES ('2021-06-20','img_spiderman',4,'Spiderman1',2); #2
INSERT INTO movies (create_at,image,qualification,title,gender_id) VALUES ('2019-03-13','img_mickey',3,'Mickey Mouse',3); #3
INSERT INTO movies (create_at,image,qualification,title,gender_id) VALUES ('2005-05-11','img_ragnarok',5,'Ragnarok 1',1); #4

INSERT INTO character_movie (character_id,movie_id) VALUES (2,1); # iron
INSERT INTO character_movie (character_id,movie_id) VALUES (3,1); # thor
INSERT INTO character_movie (character_id,movie_id) VALUES(1,2); #  peter
INSERT INTO character_movie (character_id,movie_id) VALUES (4,3); # mickey
INSERT INTO character_movie (character_id,movie_id) VALUES(3,4); # thor

INSERT INTO users (username,password,email) VALUES ('lucas','$2a$10$ApJgIPobvKMnzXJOJbxCyO78rQCSFDCDltFDjbUc7mdskJqcaFmpC','lukee_sf@hotmail.com');
INSERT INTO users (username,password,email) VALUES ('admin','$2a$10$ApJgIPobvKMnzXJOJbxCyO78rQCSFDCDltFDjbUc7mdskJqcaFmpC','lucaschalela@hotmail.com');

