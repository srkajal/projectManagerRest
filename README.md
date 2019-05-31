Table creation:
database: task_manager

create table parent_task (parent_id int primary key not null auto_increment, parent_task_name varchar(60) not null);

create table task (task_id int primary key not null auto_increment, task_name varchar(60) not null, priority int not null, start_date date not null, end_date date not null, status varchar(10) not null, parent_id int null, FOREIGN KEY (parent_id) REFERENCES parent_task(parent_id));

INSERT INTO parent_task (parent_task_name) VALUES('IPB');

INSERT INTO task (task_name, priority, start_date, end_date, status, parent_id) VALUES('Task 1', 1, '2019-01-09', '2019-06-09', 'OPEN', 1);

INSERT INTO parent_task (parent_task_name) VALUES('IPB');

INSERT INTO parent_task (parent_task_name) VALUES('GWM');

INSERT INTO parent_task (parent_task_name) VALUES('CCB');