create database demo_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
show databases;
use demo_db;
create table User(
	id bigint primary key auto_increment,
    nameUser varchar(100),
    age int,
    gender TINYINT,
    address varchar(250),
	birthdate DATE

);
INSERT INTO User (nameUser, age, gender, address, birthdate) VALUES 
('Nguyễn Văn A', 22, 1, 'Hà Nội', '2002-05-15'),
('Trần Thị B', 24, 0, 'TP. Hồ Chí Minh', '2000-08-21'),
('Lê Văn C', 30, 1, 'Đà Nẵng', '1994-12-05'),
('Phạm Thị D', 28, 0, 'Cần Thơ', '1996-03-17'),
('Hoàng Văn E', 19, 1, 'Hải Phòng', '2005-11-02');
select * from User
