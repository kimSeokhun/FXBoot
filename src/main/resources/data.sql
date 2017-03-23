insert into Reader (username, password, fullname) values ('craig', 'password', 'Craig Walls');

INSERT INTO USER
(USERNAME, 		PASSWORD, 	ACCOUNTNONEXPIRED, 	ACCOUNTNONLOCKED,		CREDENTIALSNONEXPIRED, 		ENABLED) VALUES
('ksh',       	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.', TRUE, TRUE, TRUE, TRUE),
('aaa', 	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.', FALSE, TRUE, TRUE, TRUE),
('bbb', 	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.', TRUE, FALSE, TRUE, TRUE),
('ccc', 	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.', TRUE, TRUE, FALSE, TRUE),
('ddd', 	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.', TRUE, TRUE, TRUE, FALSE),
('visualkhh', 	'1234', TRUE, TRUE, TRUE, TRUE),
('hhk',       	'1234', TRUE, TRUE, TRUE, TRUE);

INSERT INTO AUTHORITY
(USERNAME,		AUTHORITY) VALUES
('ksh',       	'ROLE_ADMIN'),
('ksh',       	'ROLE_USER'),
('ksh',       	'ROLE_USER2'),
('ksh',       	'ROLE_USER3'),
('visualkhh', 	'ROLE_USER'),
('hhk',       	'ROLE_USER'),
('aaa',       	'ROLE_USER'),
('bbb',       	'ROLE_USER'),
('ccc',       	'ROLE_USER'),
('ddd',       	'ROLE_USER');
