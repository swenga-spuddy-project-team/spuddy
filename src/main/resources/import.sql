INSERT INTO district(district_id, district_name) VALUES (1, 'Graz')
INSERT INTO district(district_id, district_name) VALUES (2, 'Graz-Umgebung')
INSERT INTO district(district_id, district_name) VALUES (3, 'Weiz')
INSERT INTO user(username, password,role, last_Name, first_Name, date_Of_Birth, district_district_id, is_Team) VALUES ('admin','$2a$10$oHAPozJIV6hJ6EZxUDusl.L1ExZxEtZcRQeyxo2LVj75CJFKMApgi','ROLE_ADMIN', 'Admi', 'istrator', '2020-01-01', 1, false);
INSERT INTO user(username, password,role, last_Name, first_Name, date_Of_Birth, district_district_id, is_Team) VALUES ('user','$2a$10$oHAPozJIV6hJ6EZxUDusl.L1ExZxEtZcRQeyxo2LVj75CJFKMApgi','ROLE_USER', 'Mustermann', 'Max', '2020-01-01', 1, false);