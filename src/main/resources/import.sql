-- Password for both users -> admin
INSERT INTO user(username,password, role) VALUES ('admin','$2a$10$oHAPozJIV6hJ6EZxUDusl.L1ExZxEtZcRQeyxo2LVj75CJFKMApgi', 'ROLE_ADMIN');
INSERT INTO user(username,password, role) VALUES ('user','$2a$10$oHAPozJIV6hJ6EZxUDusl.L1ExZxEtZcRQeyxo2LVj75CJFKMApgi', 'ROLE_USER');