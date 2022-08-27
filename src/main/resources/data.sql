INSERT INTO tb_user (name,email,password) VALUES ('Bob Brown', 'bob@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name,email,password) VALUES ('Ana', 'ana@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');
INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);

INSERT INTO tb_genre (name) values ('Ação');
INSERT INTO tb_genre (name) values ('Drama');

INSERT INTO tb_movie (img_url,title,sub_title,synopsis,year,genre_id) values ('https://exame.com/casual/cinema-exibe-os-cinco-filmes-baseados-em-tolkien-durante-madrugada/','Senhor dos Aneis','LOTR','LOREM IPSULUM','2018',1);
INSERT INTO tb_movie (img_url,title,sub_title,synopsis,year,genre_id) values ('https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRP4mwVZKRzVTKD_9PivbgnYertoZDauicSh3Ufuu3dZT5unrDw','Luta pela fé','Lorem ipsulum','LOREM IPSULUM','2020',2);

INSERT INTO tb_review(text,movie_id,user_id) values ('Filme muito bom', 1, 1);
INSERT INTO tb_review(text,movie_id,user_id) values ('Filme otimo', 2, 2);

