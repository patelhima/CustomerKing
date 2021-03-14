CREATE TABLE IF NOT EXISTS Customers(
	id bigint NOT NULL,
	address varchar(255) NULL,
	dob varchar(255) NULL,
	email varchar(255) NULL,
	gender varchar(255) NULL,
	isCustomerActive bit NULL,
	name varchar(255) NULL,
	profession varchar(255) NULL,
PRIMARY KEY (id));

CREATE SEQUENCE id_sequence START WITH 1 INCREMENT BY 1;
  
 
create table IF NOT EXISTS languages (
	id integer auto_increment, 
	locale varchar(2), 
	messagekey varchar(255),
	messagecontent varchar(255),
	primary key (id));