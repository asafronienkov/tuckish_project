
-- DROP SCHEMA TO RE-CREATE
DROP TABLE IF EXISTS PROJECT;

-- CREATE SCHEMA
CREATE TABLE PROJECT (
	ID INT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(1024) NOT NULL,
	TYPE VARCHAR(512) NOT NULL,
	PRIMARY KEY (ID)
);