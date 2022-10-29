--liquibase formatted sql

--changeset sajad:1 failOnError:true runInTransaction:true

--comment:  create a user table

create table user (
    id bigint not null auto_increment,
	username varchar(50) not null,
	password varchar(255) not null,
	name varchar(255) not null,
	email varchar(255),
	gender varchar(30),
	is_activated integer not null,
	primary key (id)
);
	
alter table user 
   add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);