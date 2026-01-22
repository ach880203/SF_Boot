"# spring_boot" 


## MySQL Database 설정

```sql
-- 📌 1) Database & User 설정
CREATE DATABASE springdb;

create table member(
	member_id int auto_increment primary key,
    name varchar(50) not null,
    age int,
    address varchar(100),
    phone varchar(20)
);

insert into member(name, age, address, phone)
values('까미', 10, '수원시', '010-1111-2222');

insert into member(name, age, address, phone)
values('로이', 13, '안산시', '010-2222-3333');

insert into member(name, age, address, phone)
values('뽀양', 8, '강동구', '010-3333-4444');

