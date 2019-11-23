create table post
(
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	author varchar(50),
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	id int AUTO_INCREMENT not null primary key,
	tag varchar(256),
);