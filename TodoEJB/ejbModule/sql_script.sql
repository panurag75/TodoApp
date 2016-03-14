-- create database todoDB;

-- table definitions
create table status (
	statusId bigint(20) not null primary key auto_increment,
	name varchar(100) not null
) ENGINE=INNODB;

create table todoItem (
	id bigint(20) not null primary key auto_increment,
	summary varchar(100) not null,
	description varchar(500),
	statusId bigint(20),
	createdDate date not null,
	updatedDate date,
	dueDateTime dateTime,
	constraint foreign key (statusId) references status (statusId)
	on delete restrict on update cascade
) ENGINE=INNODB;

-- default data
insert into status (statusId, name) values 
(1, 'New'),
(2, 'Completed'),
(3, 'In progress');

-- tbd: create index for todoItem.status.
