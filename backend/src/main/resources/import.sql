
--$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..
INSERT INTO user (user_name, password, created_at, mail,status) VALUES ('admin', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', '2020-03-08', 'admin@admin.hu', 'ADMIN');
INSERT INTO user (user_name, password, created_at, full_name, mail, phone_number, status) VALUES ('andrea112', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', '2020-03-31', 'Andrea Bakocs', 'andi@gmail.com', '+36309358837', 'TEACHER');
INSERT INTO user (user_name, password, created_at, full_name, mail, phone_number, status) VALUES ('jancsika', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', '2020-04-01', 'Jancsi Tolnai', 'jancsi22@gmail.com', '+36708529814', 'STUDENT');

INSERT INTO difficulty (level) VALUES ('beginner');
INSERT INTO difficulty (level) VALUES ('intermediate');
INSERT INTO difficulty (level) VALUES ('expert');

INSERT INTO subject (level_id, name) VALUES (1, 'programming');
INSERT INTO subject (level_id, name) VALUES (1, 'mathematics');
INSERT INTO subject (level_id, name) VALUES (2, 'english');

INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2020-04-08', 1, 'Programming for kids', 2000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2020-04-08', 3, 'English course', 1000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2020-04-08', 3, 'German course', 2000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2020-04-08', 3, 'Tech course', 4000.0, 'Budapest Koer utca 1/a');
INSERT INTO task (created_by_id, created_at, subject_type_id, title, price, place) VALUES (2, '2020-04-08', 3, 'Math course', 5000.0, 'Budapest Koer utca 1/a');

INSERT INTO message (created_by_id, created_at, text, addressed_to_id, task_id,) VALUES (1,  '2020-04-07', 'Start message', 2, 1);
INSERT INTO message (created_by_id, created_at, text, addressed_to_id, task_id,) VALUES (2,  '2020-04-07', 'Hello', 3, 2);
