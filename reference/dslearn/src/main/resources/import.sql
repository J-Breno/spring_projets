INSERT INTO tb_user (name, email, password) VALUES ('Alex Brown',  'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown',  'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Maria Green',  'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_STUDENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 3);

INSERT INTO tb_course(name, img_uri, img_gray_uri) VALUES ('Bootcampt HTML', 'data:imasnuthfiaf5EuadjfI9g9ZPdbC/E0/7eN1OxvljxOrmsVVHpVNK4V6dVHp1F4', 'https://blog.coursify.me/wp-content/uploads/2018/08/plan-your-online-course.jpg');

INSERT INTO tb_offer(edition, start_moment, end_moment, course_id) VALUES ('1.0', TIMESTAMP WITH TIME ZONE '2020-11-20T03:00:00Z',TIMESTAMP WITH TIME ZONE '2021-11-20T03:00:00Z', 1);
INSERT INTO tb_offer(edition, start_moment, end_moment, course_id) VALUES ('2.0', TIMESTAMP WITH TIME ZONE '2020-12-20T03:00:00Z',TIMESTAMP WITH TIME ZONE '2021-12-20T03:00:00Z', 1);

INSERT INTO tb_resource(title, description, position, img_uri, type, offer_id) VALUES ('Trilha HTML', 'Trilha principal do curso', 1, 'https:afdsakjfajfdhslsdjalf.png', 1, 1);
INSERT INTO tb_resource(title, description, position, img_uri, type, offer_id) VALUES ('Forum', 'Tire suas dúvidas', 2, 'https:afdsakjfajfdhslsdjalf.png', 2, 1);
INSERT INTO tb_resource(title, description, position, img_uri, type, offer_id) VALUES ('Lives', 'Lives exclusivas para turma', 3, 'https:afdsakjfajfdhslsdjalf.png', 0, 1);

INSERT INTO tb_section(title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Capítulo 1', 'Neste capitulo vamos começar', 1, 'https:afdsakjfajfdhslsdjalf', 1, null);
INSERT INTO tb_section(title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Capítulo 2', 'Neste capitulo vamos continuar', 2, 'https:afdsakjfajfdhslsdjalf', 1, 1);
INSERT INTO tb_section(title, description, position, img_uri, resource_id, prerequisite_id) VALUES ('Capítulo 3', 'Neste capitulo vamos finalizar', 3, 'https:afdsakjfajfdhslsdjalf', 1, 2);

INSERT INTO tb_enrollment(user_id, offer_id, enroll_moment, refund_moment, available, only_update) VALUES (1, 1, TIMESTAMP WITH TIME ZONE '2020-11-20T13:00:00Z', null, true, false);
INSERT INTO tb_enrollment(user_id, offer_id, enroll_moment, refund_moment, available, only_update) VALUES (2, 1, TIMESTAMP WITH TIME ZONE '2020-11-20T13:00:00Z', null, true, false);

INSERT INTO tb_lesson(title, position, section_id) VaLUES('Aula 1 do capitulo 1', 1,1);
INSERT INTO tb_content(text_content, video_uri, id) VALUES('Material de apoio: abc','https:www.googlw.comvrequesxg=dfauhdsauh',1 );

INSERT INTO tb_lesson(title, position, section_id) VaLUES('Aula 2 do capitulo 2', 2,1);
INSERT INTO tb_content(text_content, video_uri, id) VALUES('','https:www.googlw.comvrequesxg=dfauhdsauh',2);

INSERT INTO tb_lesson(title, position, section_id) VaLUES('Aula 3 do capitulo 1', 3,1);
INSERT INTO tb_content(text_content, video_uri, id) VALUES('Material de apoio: abc','https:www.googlw.comvrequesxg=dfauhdsauh',3);

INSERT INTO tb_lesson(title, position, section_id) VaLUES('Tarefa 3 co capitlo 1', 4,1);
INSERT INTO tb_tka(id, description, question_count, approval_count, weight, due_date) VALUES(4,'Fazer trabalho',5,4, 1.0,TIMESTAMP WITH TIME ZONE '2020-11-20T13:00:00Z');


INSERT INT tb_lessons_done(lesson_id, user_id, offer_id) VALUES (1, 1, 1);
INSERT INT tb_lessons_done(lesson_id, user_id, offer_id) VALUES (2, 1, 1);
