--liquibase formatted sql

--changeset sajad:3 failOnError:true runInTransaction:true

--comment:  create a post table

create table post (
    id bigint not null auto_increment,
	userId bigint not null,
	title varchar(255) not null,
	body varchar(2500) not null,
	primary key (id)
);
	
   
alter table post 
   add constraint FK_POST_USER
   foreign key (userId) 
   references user (id);