API Details : 


|**Method**|**Api**|**Header**|**Request**|**Response**|
| :-: | :-: | :-: | :-: | :-: |
|GET|/tasks|userId||List<Task>|
|POST|/tasks|userId|<p>{</p><p>`    `"title": "Backend",</p><p>`    `"description": "Complete Backend",</p><p>`    `"dueDate": "2023-01-25",</p><p>`    `"status": "Pending",</p><p>`    `"project":{</p><p>`        `"name": "ojas"</p><p>`    `},</p><p>`    `"labels":[</p><p>`        `{</p><p>`            `"title": "backend",</p><p>`            `"color": "red"</p><p>`        `},</p><p>`        `{</p><p>`            `"title": "JAVA",</p><p>`            `"color": "blue"</p><p>`        `}</p><p>`    `]</p><p>}</p><p></p>|Task|
|GET|/tasks/{id}|userId||Task|
|PUT|/tasks/{id}|userId|<p>{</p><p>`    `"title": "Backend",</p><p>`    `"description": "Complete Backend",</p><p>`    `"dueDate": "2023-01-25",</p><p>`    `"status": "Pending",</p><p>`    `"project":{</p><p>`        `"name": "ojas"</p><p>`    `},</p><p>`    `"labels":[</p><p>`        `{</p><p>`            `"title": "backend",</p><p>`            `"color": "red"</p><p>`        `},</p><p>`        `{</p><p>`            `"title": "JAVA",</p><p>`            `"color": "blue"</p><p>`        `}</p><p>`    `]</p><p>}</p><p></p>|Task|
|DELETE|<p>/tasks/{id}</p><p></p><p></p>|userId|||
|GET|/tasks/project/{id}|userId||List<Task>|
|POST|/user||<p>{</p><p>`    `"username" : "ojas\_dubey",</p><p>`    `"email" : "ojas.dubey81@gmail.com"</p><p>}</p>|User|
|GET|/user|||List<User>|
|GET|/user/{id}|||User|
|PUT|/user/{id}||<p>{</p><p>`    `"username" : "ojas\_dubey",</p><p>`    `"email" : "ojas.dubey81@gmail.com"</p><p>}</p>|User|
|DELETE|/user/{id}||||
|POST|/projects|userId|<p>{</p><p>`    `"name": "new1"</p><p>}</p>|Project|
|GET|/projects|userId||List<Project>|
|GET|/projects/{id}|userId||Project|
|PUT|/projects/{id}|userId|<p>{</p><p>`    `"name": "new1"</p><p>}</p>|Project|
|DELETE|/projects/{id}|userId|||


SQL Details: 

create table tasks

(

`    `Id          varchar(100) not null

`        `primary key,

`    `title       text         not null,

`    `description text         null,

`    `due\_date    date         null,

`    `status      text         null,

`    `user\_id     varchar(100) not null,

`    `project\_id  varchar(100) null

);


create table projects

(

`    `id      varchar(100) default '1' not null

`        `primary key,

`    `title   varchar(100)             not null,

`    `user\_id varchar(100)             not null,

`    `constraint projects\_title\_uindex

`        `unique (title),

`    `constraint projects\_title\_user\_id\_uindex

`        `unique (title, user\_id)

);


create table tasks\_labels

(

`    `task\_id  varchar(100) null,

`    `label\_id varchar(100) null,

`    `constraint tasks\_labels\_task\_id\_label\_id\_uindex

`        `unique (task\_id, label\_id)

);

create table users

(

`    `id       varchar(100) not null

`        `primary key,

`    `username varchar(100) not null,

`    `email    varchar(100) not null,

`    `constraint users\_email\_uindex

`        `unique (email),

`    `constraint users\_username\_uindex

`        `unique (username)

);


create table labels

(

`    `id      varchar(100) not null

`        `primary key,

`    `title   varchar(100) null,

`    `colour  varchar(30)  null,

`    `user\_id varchar(100) not null,

`    `constraint labels\_title\_colour\_user\_id\_uindex

`        `unique (title, colour, user\_id)

);

