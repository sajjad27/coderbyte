--liquibase formatted sql

--changeset sajad:5 failOnError:true runInTransaction:true

--comment:  create a comment table

create table comment (
    id bigint not null auto_increment,
	postId bigint not null,
	name varchar(255) not null,
	email varchar(50) not null,
	body varchar(500) not null,
	primary key (id)
);
	
   
alter table comment 
   add constraint FK_COMMENT_POST
   foreign key (postId) 
   references post (id);