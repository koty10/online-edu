create table classroom
(
    id   serial      not null
        constraint classroom_pk
            primary key,
    name varchar(64) not null
);

create table user_account
(
    id       serial       not null
        constraint user_account_pk
            primary key,
    role     varchar(64)  not null,
    username varchar(255) not null
        constraint user_account_un
            unique,
    password   varchar      not null,
    email      varchar(255) not null,
    firstname  varchar(255) not null,
    surname    varchar(255) not null,
    birthday   timestamp,
    registered timestamp,
    street     varchar(128),
    zip        varchar(32),
    phone      varchar(64),
    city       varchar(128)
);
   
create table teacher
(
    id         serial       not null
        constraint teacher_pk
            primary key,
    classroom  integer
        constraint teacher_fk
            references classroom
            on update cascade on delete set null,
    user_account  integer not null
        constraint user_account_fk
            references user_account
            on update cascade on delete cascade
);

create table student
(
    id         serial       not null
        constraint student_pk
            primary key,
    chat_alert varchar,
    classroom  integer      not null
        constraint student_fk
            references classroom
            on update cascade on delete set null,
    user_account  integer not null
        constraint user_account_fk
            references user_account
            on update cascade on delete cascade,
    points       integer default 0 not null
);

create table parent
(
    id         serial       not null
        constraint parent_pk
            primary key,
    user_account  integer not null
        constraint user_account_fk
            references user_account
            on update cascade on delete cascade
);

create table family
(
    student integer not null
        constraint family_fk_1
            references student
            on update cascade on delete cascade,
    parent  integer not null
        constraint family_fk
            references parent
            on update cascade on delete cascade,
    id      serial  not null
        constraint family_pk
            primary key
);

create table subject
(
    id   serial not null
        constraint subject_pk
            primary key,
    name varchar(255)
);

create table teaching
(
    id        serial  not null
        constraint teaching_pk
            primary key,
    teacher   integer not null
        constraint teaching_fk
            references teacher
            on update cascade on delete cascade,
    classroom integer not null
        constraint teaching_fk_2
            references classroom
            on update cascade on delete cascade,
    subject   integer not null
        constraint teaching_fk_3
            references subject
            on update cascade on delete restrict
);

create table chat
(
    id       serial not null
        constraint chat_pk
            primary key,
    student  integer
        constraint chat_fk
            references student
            on update cascade on delete cascade,
    teaching integer
        constraint chat_fk_2
            references teaching
            on update cascade on delete cascade
);

create table message
(
    id           serial        not null
        constraint message_pk
            primary key,
    chat         integer       not null
        constraint message_chat_fk
            references chat
            on update cascade on delete cascade,
    user_account integer       not null
        constraint message_fk
            references user_account
            on update cascade on delete cascade,
    text         varchar(1028) not null,
    time         timestamp     not null
);

create table material
(
    id       serial       not null
        constraint material_pk
            primary key,
    teaching integer      not null
        constraint material_fk
            references teaching
            on update cascade on delete cascade,
    name     varchar(128) not null,
    url      varchar(256),
    blob     bytea,
    type     varchar(64)  not null
);

create table summary
(
    id          serial  not null
        constraint summary_pk
            primary key,
    teaching    integer not null
        constraint summary_fk
            references teaching
            on update cascade on delete cascade,
    student     integer not null
        constraint summary_fk_1
            references student
            on update cascade on delete cascade,
    final_grade varchar(64),
    feedback    varchar(1024),
    constraint summary_un
        unique (teaching, student)
);

create table scheduled_teaching
(
    id       serial    not null
        constraint scheduled_teaching_pk
            primary key,
    teaching integer   not null
        constraint scheduled_teaching_fk
            references teaching
            on update cascade on delete cascade,
    "time_from"   timestamp not null,
    "time_to"     timestamp not null
);

create table task
(
    id       serial  not null
        constraint task_pk
            primary key,
    teaching integer not null
        constraint task_fk
            references teaching
            on update cascade on delete cascade,
    text     varchar,
    name     varchar(64),
    "time_from"   timestamp,
    "time_to"     timestamp,
    date     timestamp,
    type     varchar(64),
	points     integer
);

create table attempt
(
    id       serial  not null
        constraint attempt_pk
            primary key,
    student  integer not null
        constraint attempt_fk
            references student
            on update cascade on delete cascade,
    task     integer not null
        constraint attempt_fk_1
            references task
            on update cascade on delete cascade,
    grade    varchar(64),
    score    varchar(64),
    feedback varchar(1024),
    text     varchar(4096),
    time     timestamp not null default CURRENT_DATE,
    state    varchar(64) not null
);

