insert into Reader (username, password, fullname) values ('craig', 'password', 'Craig Walls');

INSERT INTO USER
(USERNAME,		PASSWORD,		ACCOUNTNONEXPIRED,		ACCOUNTNONLOCKED,		CREDENTIALSNONEXPIRED,		ENABLED) VALUES
('ksh',       	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	TRUE,	TRUE),
('aaa', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	FALSE,	TRUE,	TRUE,	TRUE),
('bbb', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	FALSE,	TRUE,	TRUE),
('ccc', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	FALSE,	TRUE),
('ddd', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	TRUE,	FALSE),
('visualkhh',	'1234',	TRUE,	TRUE,	TRUE,	TRUE),
('hhk',			'1234',	TRUE,	TRUE,	TRUE,	TRUE);

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

INSERT INTO BOARD (BOARD_ID, TITLE, CONTENT, VIEW_COUNT, SECRET, TYPE, CREATED_BY, CREATED_AT ) VALUES 
(1, 'TITLE', 'CONTENTS', 0, 'N', 'SAMPLE', 'ksh', '2017-05-10 09:29:29.437'),
(2, 'TITLE2', 'CONTENTS2', 0, 'N', 'SAMPLE', 'ksh', '2017-05-10 09:29:29.437');

INSERT INTO COMMON_CODE_M
(GROUP_CD,		GROUP_NM,	CODE,			NAME,	SORT,	USE_YN) VALUES
('USER_STATUS',	'계정상태',	'NORMAL',		'활성',	'1',	'Y'),
('USER_STATUS',	'계정상태',	'ACCOUNT_LOCK',	'잠김',	'2',	'Y'),
('USER_STATUS',	'계정상태',	'1',		'1',	'3',	'Y'),
('USER_STATUS',	'계정상태',	'2',		'2',	'4',	'Y'),
('USER_STATUS',	'계정상태',	'3',		'3',	'5',	'Y'),
('USER_STATUS',	'계정상태',	'4',		'4',	'6',	'Y'),
('USER_STATUS',	'계정상태',	'5',		'5',	'7',	'Y'),
('USER_STATUS',	'계정상태',	'6',		'6',	'8',	'Y'),
('USER_STATUS',	'계정상태',	'7',		'7',	'9',	'Y'),
('USER_STATUS',	'계정상태',	'8',		'8',	'10',	'Y'),
('USER_STATUS',	'계정상태',	'9',		'9',	'11',	'Y'),
('USER_STATUS',	'계정상태',	'10',		'10',	'12',	'Y'),
('USER_STATUS',	'계정상태',	'11',		'11',	'13',	'Y'),
('USER_STATUS',	'계정상태',	'12',		'12',	'14',	'Y'),
('USER_STATUS',	'계정상태',	'13',		'13',	'15',	'Y'),

('TEST_CODE_1',	'테스트 코드 1',	'1',		'1',	'3',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'2',		'2',	'4',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'3',		'3',	'5',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'4',		'4',	'6',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'5',		'5',	'7',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'6',		'6',	'8',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'7',		'7',	'9',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'8',		'8',	'10',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'9',		'9',	'11',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'10',		'10',	'12',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'11',		'11',	'13',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'12',		'12',	'14',	'Y'),
('TEST_CODE_1',	'테스트 코드 1',	'13',		'13',	'15',	'Y'),

('TEST_CODE_2',	'테스트 코드 2',	'1',		'1',	'3',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'2',		'2',	'4',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'3',		'3',	'5',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'4',		'4',	'6',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'5',		'5',	'7',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'6',		'6',	'8',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'7',		'7',	'9',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'8',		'8',	'10',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'9',		'9',	'11',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'10',		'10',	'12',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'11',		'11',	'13',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'12',		'12',	'14',	'Y'),
('TEST_CODE_2',	'테스트 코드 2',	'13',		'13',	'15',	'Y'),

('TEST_CODE_3',	'테스트 코드 3',	'1',		'1',	'3',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'2',		'2',	'4',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'3',		'3',	'5',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'4',		'4',	'6',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'5',		'5',	'7',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'6',		'6',	'8',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'7',		'7',	'9',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'8',		'8',	'10',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'9',		'9',	'11',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'10',		'10',	'12',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'11',		'11',	'13',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'12',		'12',	'14',	'Y'),
('TEST_CODE_3',	'테스트 코드 3',	'13',		'13',	'15',	'Y');