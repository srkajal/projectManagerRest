Table creation:
database: project_manager

Table creation:

create table parent_task (parent_id int primary key not null auto_increment, parent_task_name varchar(60) not null);

create table task (task_id int primary key not null auto_increment, task_name varchar(60) not null, priority int not null, start_date date not null, end_date date not null, status varchar(10) not null, parent_id int null, project_id int not null, FOREIGN KEY (parent_id) REFERENCES parent_task(parent_id), FOREIGN KEY (project_id) REFERENCES project(project_id) );

create table project (project_id int primary key not null auto_increment, project_name varchar(60) not null, start_date date not null, end_date date not null, priority int not null, status varchar(10) not null);

create table user (user_id int primary key not null auto_increment, first_name varchar(60) not null, last_name varchar(60) not null, employee_id int not null, project_id int null, task_id int null, FOREIGN KEY (project_id) REFERENCES project(project_id), FOREIGN KEY (task_id) REFERENCES task(task_id));


Data:

INSERT INTO parent_task (parent_task_name) VALUES('IPB');

INSERT INTO task (task_name, priority, start_date, end_date, status, parent_id, project_id) VALUES('Task 1', 1, '2019-01-09', '2019-06-09', 'OPEN', 1, 1);

INSERT INTO project (project_name, start_date, end_date, priority) VALUES('Project 1', '2019-01-09', '2019-06-09', 5);

INSERT INTO user (first_name, last_name, employee_id, project_id, task_id) VALUES('Dhiman', 'Roy', '123', 1, 1);



INSERT INTO parent_task (parent_task_name) VALUES('IPB');

INSERT INTO parent_task (parent_task_name) VALUES('GWM');

INSERT INTO parent_task (parent_task_name) VALUES('CCB');