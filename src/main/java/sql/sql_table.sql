create database mybatis;

use mybatis;

create table user(
  id int primary key auto_increment,
  username varchar(50),
  birthday date,
  sex varchar(20),
  address varchar(50)
);