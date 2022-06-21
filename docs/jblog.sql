-- schema
show tables;
desc user;
desc blog;
desc category;
desc post;

select * from user;
delete from user where id="coco";
select id, name, password from user where id = "dooly" and password = "1234";

select * from blog;
select * from category;

update blog set logo="/assets/images/spring-logo.jpg";
