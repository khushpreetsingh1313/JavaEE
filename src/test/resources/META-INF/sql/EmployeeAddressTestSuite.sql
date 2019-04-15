-- any sql that is to be pre-loaded before testsuite runs goes in here
-- statements must be a single line without returns

INSERT INTO ADDRESS (ID, CITY, COUNTRY, POSTAL, STATE, STREET, VERSION) VALUES (2, 'Nepean', 'CA', 'K2X 5A1', 'ON', '20 Pinetrail Cress', 1)
INSERT INTO EMPLOYEE (ID, FIRSTNAME, LASTNAME,SALARY,ADDR_ID, VERSION) VALUES (2, 'Sharon', 'Matthews',3500.00, 2, 1)
INSERT INTO EMPLOYEE (ID, FIRSTNAME, LASTNAME,SALARY,ADDR_ID, VERSION) VALUES (3, 'Celine', 'Matthews',3500.00,NULL , 1)