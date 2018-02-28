create table actor
(
	name varchar(50) not null collate utf8_bin
		primary key,
	movies text null collate utf8_bin,
	count int null,
	style varchar(200) null,
	director varchar(200) null collate utf8_bin,
	corporation varchar(20) null
)
engine=InnoDB
;

create table binding
(
	binding varchar(50) not null collate utf8_bin
		primary key,
	count int null
)
engine=InnoDB
;

create table date
(
	id int null,
	year int null,
	month int null,
	week int null,
	count int null,
	movies text null
)
engine=InnoDB
;

create table director
(
	name varchar(500) not null,
	count int null,
	movies longtext null,
	style varchar(200) null,
	actors varchar(200) null collate utf8_bin,
	corporation varchar(200) null collate utf8_bin
)
engine=InnoDB
;

create table genre
(
	genre varchar(50) not null collate utf8_bin
		primary key,
	count int null,
	movies longtext null
)
engine=InnoDB
;

create table language
(
	language varchar(50) not null collate utf8_bin
		primary key,
	count int null
)
engine=InnoDB
;

create table movie
(
	id varchar(50) not null
		primary key,
	title varchar(500) null,
	starrings varchar(1000) null,
	actors varchar(1000) null,
	directors varchar(1000) null,
	genres varchar(500) null,
	languages varchar(500) null,
	binding varchar(500) null,
	studios varchar(500) null,
	date date null,
	products text null,
	year int null,
	month int null,
	week int null
)
engine=InnoDB
;

create table product
(
	id varchar(50) not null
		primary key,
	sales_rank int null,
	price double null,
	languages varchar(500) null,
	binding varchar(500) null,
	studios varchar(500) null,
	running_time int null
)
engine=InnoDB
;

create table ranking
(
	id int auto_increment
		primary key,
	product varchar(30) null,
	ranking double null
)
engine=InnoDB
;

create table starring
(
	name varchar(30) not null collate utf8_bin
		primary key,
	count int null,
	movies text null
)
engine=InnoDB
;

create table studio
(
	studio varchar(250) not null collate utf8_bin
		primary key,
	count int null
)
engine=InnoDB
;

