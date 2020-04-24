create table user_credentials(email_id varchar(50),password varchar(200),ts timestamp,primary key(email_id));
drop table IF EXISTS exexpense_details;
create table expense_details(id int IDENTITY PRIMARY KEY,user_id varchar(50),title varchar(2000),category varchar(200),contact int,description varchar(400),amount decimal(8, 2) NOT NULL,expense_date date,constraint userId_fk foreign key(user_id) references user_credentials(email_id));
