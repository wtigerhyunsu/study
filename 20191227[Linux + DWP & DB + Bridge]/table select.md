create table users(
userid varchar2(20) primary key,
userpwd varchar2(20),
username varchar2(20),
useraddr varchar2(20),
userphone varchar2(20),
ranks varchar2(20),
birth varchar2(50),
gender varchar2(20),
email varchar2(50)
);

----

create table product(
prodname varchar2(50) primary key,
brand varchar2(20),
price number,
speccpu varchar2(20),
specram varchar2(20),
specstorage varchar2(20),
specsize varchar2(20),
specimg varchar2(50),
stock number,
proddate date
);

---



create sequence seq_cart

start with 1

increment by 1

;

---

create table cart(
cartno varchar2(20) primary key,
userid varchar2(20),
prodname varchar2(50),
quantity number

);