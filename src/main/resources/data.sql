insert into employee
values(DEFAULT,'samkishore@email.com', 'sam','saravanan');

insert into employee
values(DEFAULT,'rk@gmail.com', 'ravi','kiran');

insert into roles (role_id,name) values (default,'ADMIN');

insert into users (user_id,password,username) values (1,'$2a$12$Q9Ze7EpdbYlBgAWrziuEGuYh8kISR6bYIDEcz0mM1UyFZDkI5YMJ.','sruthi');

insert into users_roles (user_id,role_id) values (1,1);