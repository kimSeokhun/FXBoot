insert into Reader (username, password, fullname) values ('craig', 'password', 'Craig Walls');

INSERT INTO USER
(USERNAME, 		PASSWORD, 	ACCOUNTNONEXPIRED, 	ACCOUNTNONLOCKED,		CREDENTIALSNONEXPIRED, 		ENABLED) VALUES
('ksh',       	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',		TRUE,              TRUE,                   TRUE,                       TRUE),
('visualkhh', 	'1234',		TRUE,              TRUE,                   TRUE,                       TRUE),
('hhk',       	'1234',		TRUE,              TRUE,                   TRUE,                       TRUE);

INSERT INTO AUTHORITY
(USERNAME,		AUTHORITY) VALUES
('ksh',       	'ROLE_ADMIN'),
('ksh',       	'ROLE_USER'),
('ksh',       	'AUTH_USER_C'),
('ksh',       	'AUTH_USER_R'),
('ksh',       	'AUTH_USER_U'),
('ksh',       	'AUTH_USER_D'),
('ksh',       	'AUTH_ADMIN_C'),
('ksh',       	'AUTH_ADMIN_D'),
('visualkhh', 	'ROLE_USER'),
('hhk',       	'ROLE_USER');
