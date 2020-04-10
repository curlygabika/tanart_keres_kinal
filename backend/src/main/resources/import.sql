
--$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..
INSERT INTO user (user_name, password, created_at, mail,status) VALUES ('admin', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', '2019-10-08', 'admin@admin.hu', 'ADMIN');
INSERT INTO user (user_name, password, created_at, full_name, mail, phone_number, status) VALUES ('andrea112', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', '2019-10-08', 'Andrea Bakocs', 'andi@gmail.com', '+36309358837', 'TEACHER');

INSERT INTO difficulty (level) VALUES ('beginner');
INSERT INTO difficulty (level) VALUES ('intermediate');
INSERT INTO difficulty (level) VALUES ('expert');

INSERT INTO subject (level_id, name) VALUES (1, 'programming');
INSERT INTO subject (level_id, name) VALUES (1, 'mathematics');
INSERT INTO subject (level_id, name) VALUES (2, 'english');

INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2019-10-08', 1, 'Programming for kids', 2000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2019-10-08', 3, 'English course', 1000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2019-10-08', 3, 'German course', 2000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2019-10-08', 3, 'Tech course', 4000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2019-10-08', 3, 'Math course', 5000.0, 'Budapest Koer utca 1/a');

--INSERT INTO message (task_id, created_by_id, created_at, text) VALUES (1, 2, '2019-10-08', 'Start message');
--INSERT INTO message (addressed_to_id, created_by_id, created_at, text) VALUES (1, 2, '2019-10-08', 'Hello');
