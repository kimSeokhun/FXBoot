INSERT INTO USER
(USERNAME,		PASSWORD,		ACCOUNTNONEXPIRED,		ACCOUNTNONLOCKED,		CREDENTIALSNONEXPIRED,		ENABLED) VALUES
('system',      '$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	TRUE,	TRUE),
('admin',      '$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	TRUE,	TRUE),
('ksh',       	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	TRUE,	TRUE),
('user', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.',	TRUE,	TRUE,	TRUE,	TRUE);

--INSERT INTO USER
--(USERNAME,		PASSWORD) VALUES
--('system',      '$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('admin',      '$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('ksh',       	'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('aaa', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('bbb', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('ccc', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('ddd', 		'$2a$10$9Uraz7S4LZTFttdqJgE5v.rrIW54wZgFh3nQ7e0hCs9CiPhckfoG.'),
--('visualkhh',	'1234'),
--('hhk',			'1234');

insert  into RESOURCES (RESOURCES_ID, URL) values (1,'/user');
insert  into RESOURCES (RESOURCES_ID, URL) values (2,'/admin');
insert  into RESOURCES (RESOURCES_ID, URL) values (3,'/sample');
insert  into RESOURCES (RESOURCES_ID, URL) values (4,'/system');
insert  into RESOURCES (RESOURCES_ID, URL) values (5,'/board/SAMPLE');
insert  into RESOURCES (RESOURCES_ID, URL) values (6,'/actuator/beans');
insert  into RESOURCES (RESOURCES_ID, URL) values (7,'/actuator/mappings');

insert  into ROLE(ROLE_ID, ROLE_NAME) values (1,'ROLE_SYSTEM');
insert  into ROLE(ROLE_ID, ROLE_NAME) values (2,'ROLE_ADMIN');
insert  into ROLE(ROLE_ID, ROLE_NAME) values (3,'ROLE_USER');

insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (1,1,2);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (2,1,3);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (3,1,4);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (4,2,2);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (5,2,3);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (6,2,5);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (7,2,6);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (8,1,7);
insert  into ROLE_RESOURCE(ROLE_RESOURCE_ID, ROLE_ID, RESOURCES_ID) values (9,3,5);

insert  into AUTHORITIES(AUTHORITIES_ID, USERNAME, ROLE_ID) values (1,'ksh',2);
insert  into AUTHORITIES(AUTHORITIES_ID, USERNAME, ROLE_ID) values (2,'system',1);
insert  into AUTHORITIES(AUTHORITIES_ID, USERNAME, ROLE_ID) values (3,'admin',2);
insert  into AUTHORITIES(AUTHORITIES_ID, USERNAME, ROLE_ID) values (4,'user',3);




--INSERT INTO AUTHORITY
--(USERNAME,		AUTHORITY) VALUES
--('ksh',       	'ROLE_SYSTEM'),
--('ksh',       	'ROLE_ADMIN'),
--('ksh',       	'ROLE_USER'),
--('ksh',       	'ROLE_USER2'),
--('ksh',       	'ROLE_USER3'),
--('visualkhh', 	'ROLE_USER'),
--('hhk',       	'ROLE_USER'),
--('aaa',       	'ROLE_USER'),
--('bbb',       	'ROLE_USER'),
--('ccc',       	'ROLE_USER'),
--('ddd',       	'ROLE_USER');

INSERT INTO BOARD_TYPE(TYPE, NAME, DESC) VALUES ('SAMPLE', 'SAMPLE BOARD', '샘플용 게시판입니다.');

INSERT INTO BOARD (BOARD_ID, TITLE, CONTENT, VIEW_COUNT, SECRET, DEL_YN, TYPE, CREATED_BY, CREATED_AT ) VALUES 
(1, 'TITLE', 'CONTENTS', 0, 'N', 'N', 'SAMPLE', 'ksh', '2017-05-10 09:29:29.437'),
(2, 'TITLE2', 'CONTENTS2', 0, 'N', 'N', 'SAMPLE', 'ksh', '2017-05-10 09:29:29.437'),
(3, 'TITLE333', 'CONTENTS', 0, 'N', 'N', 'SAMPLE', 'aaa', '2017-05-10 09:29:29.437');

INSERT INTO COMMON_CODE
(GROUP_CD,		GROUP_NM,	CODE,			NAME,	SORT,	USE_YN) VALUES
('USER_STATUS',	'계정상태',	'NORMAL',		'활성',	'1',	'Y'),
('USER_STATUS',	'계정상태',	'ACCOUNT_LOCK',	'잠김',	'2',	'Y'),

('MENU_GROUP',	'메뉴 그룹',	'SYSTEM',		'시스템 그룹',	'1',	'Y'),
('MENU_GROUP',	'메뉴 그룹',	'ADMIN',		'관리자 그룹',	'2',	'Y'),
('MENU_GROUP',	'메뉴 그룹',	'USER',			'사용자 그룹',	'3',	'Y'),

--('AUTH_GROUP',	'권한 그룹',	'SYSTEM',		'시스템 그룹',	'1',	'Y'),
--('AUTH_GROUP',	'권한 그룹',	'ADMIN',		'관리자 그룹',	'2',	'Y'),
--('AUTH_GROUP',	'권한 그룹',	'USER',			'사용자 그룹',	'3',	'Y'),

('USER_ROLE',	'사용자 롤',	'SYSTEM',		'시스템 접근 권한',	'1',	'Y'),
('USER_ROLE',	'사용자 롤',	'ADMIN',		'관리자 접근 권한',	'2',	'Y'),
('USER_ROLE',	'사용자 롤',	'USER',			'사용자 접근 권한',	'3',	'Y'),

('ZTEST_CODE_1',	'테스트 코드 1',	'1',		'1',	'3',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'2',		'2',	'4',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'3',		'3',	'5',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'4',		'4',	'6',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'5',		'5',	'7',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'6',		'6',	'8',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'7',		'7',	'9',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'8',		'8',	'10',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'9',		'9',	'11',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'10',		'10',	'12',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'11',		'11',	'13',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'12',		'12',	'14',	'Y'),
('ZTEST_CODE_1',	'테스트 코드 1',	'13',		'13',	'15',	'Y'),

('ZTEST_CODE_2',	'테스트 코드 2',	'1',		'1',	'3',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'2',		'2',	'4',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'3',		'3',	'5',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'4',		'4',	'6',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'5',		'5',	'7',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'6',		'6',	'8',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'7',		'7',	'9',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'8',		'8',	'10',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'9',		'9',	'11',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'10',		'10',	'12',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'11',		'11',	'13',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'12',		'12',	'14',	'Y'),
('ZTEST_CODE_2',	'테스트 코드 2',	'13',		'13',	'15',	'Y'),

('ZTEST_CODE_3',	'테스트 코드 3',	'1',		'1',	'3',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'2',		'2',	'4',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'3',		'3',	'5',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'4',		'4',	'6',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'5',		'5',	'7',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'6',		'6',	'8',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'7',		'7',	'9',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'8',		'8',	'10',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'9',		'9',	'11',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'10',		'10',	'12',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'11',		'11',	'13',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'12',		'12',	'14',	'Y'),
('ZTEST_CODE_3',	'테스트 코드 3',	'13',		'13',	'15',	'Y'),

('ZTEST_CODE_4',	'테스트 코드 4',	'1',		'1',	'3',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'2',		'2',	'4',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'3',		'3',	'5',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'4',		'4',	'6',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'5',		'5',	'7',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'6',		'6',	'8',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'7',		'7',	'9',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'8',		'8',	'10',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'9',		'9',	'11',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'10',		'10',	'12',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'11',		'11',	'13',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'12',		'12',	'14',	'Y'),
('ZTEST_CODE_4',	'테스트 코드 4',	'13',		'13',	'15',	'Y');

INSERT INTO MENU(MENU_ID, MENU_GRP_CD, PARENT_ID, MENU_NM, LEVEL, SORT, PROG_URL, USE_YN) VALUES (1, 'SYSTEM', null, 'test', 0, 0, '/test', 'Y');
INSERT INTO MENU(MENU_ID, MENU_GRP_CD, PARENT_ID, MENU_NM, LEVEL, SORT, PROG_URL, USE_YN) VALUES (2, 'SYSTEM', 1, 'test2', 0, 0, '/test2', 'Y');
