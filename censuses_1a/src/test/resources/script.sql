CREATE TABLE TUSERS(
ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,
NIF VARCHAR(255),
CODIGOMESA INTEGER NOT NULL,
CONTRASEÑA VARCHAR(255),
EMAIL VARCHAR(255),
NAME VARCHAR(255));