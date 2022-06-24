-- schema
show tables;
desc user;
desc blog;
desc category;
desc post;



select * from user;
delete from user where id="dooly";
select id, name, password from user where id = "dooly" and password = "1234";

select * from blog;
delete from user where id="dooly123";
select * from category;
select max(no) from category where blog_id = "coco";
select * from post;

update blog set logo="/assets/images/spring-logo.jpg";

select a.no, a.name, a.description, 
	(select count(*) from post b where a.no = b.category_no) as count
	from category a
    where a.blog_id = 'coco'
order by no desc; 



delete from category where no = 3;

select b.no, b.title, b.contents, b.category_no
	from category a, post b
	where a.no = b.category_no
		and a.no = 1 order by b.no desc;
	