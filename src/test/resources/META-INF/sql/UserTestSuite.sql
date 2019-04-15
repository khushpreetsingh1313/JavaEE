-- any sql that is to be pre-loaded before testsuite runs goes in here
-- statements must be a single line without returns


insert into PLATFORM_USER(id, username, password) values(1, 'admin', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
insert into PLATFORM_USER(id, username, password) values(2, 'user1', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

insert into PLATFORM_ROLE(id, rolename) values(1, 'ADMINS');
insert into PLATFORM_ROLE(id, rolename) values(2, 'USERS');

insert into PLATFORM_USER_ROLE(id, username, rolename) values(1, 'admin', 'ADMINS'); 
insert into PLATFORM_USER_ROLE(id, username, rolename) values(2, 'user1', 'USERS'); 