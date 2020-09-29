create table classroom
(
    id   serial      not null
        constraint classroom_pk
            primary key,
    name varchar(64) not null
);

alter table classroom
    owner to onlineedu;

create table teacher
(
    id         serial       not null
        constraint teacher_pk
            primary key,
    email      varchar(255) not null,
    firstname  varchar(255) not null,
    surname    varchar(255) not null,
    age        integer,
    registered timestamp,
    street     varchar,
    zip        varchar,
    phone      varchar(64)  not null,
    classroom  integer
        constraint teacher_fk
            references classroom
            on update cascade on delete set null
);

alter table teacher
    owner to onlineedu;

create table student
(
    id         serial       not null
        constraint student_pk
            primary key,
    email      varchar(255) not null,
    firstname  varchar(255) not null,
    surname    varchar(255) not null,
    age        integer,
    registered timestamp,
    street     varchar,
    zip        varchar,
    chat_alert varchar,
    classroom  integer      not null
        constraint student_fk
            references classroom
            on update cascade on delete set null
);

alter table student
    owner to onlineedu;

create table parent
(
    id         serial       not null
        constraint parent_pk
            primary key,
    email      varchar(255) not null,
    firstname  varchar(255) not null,
    surname    varchar(255) not null,
    age        integer,
    registered timestamp,
    street     varchar,
    zip        varchar,
    phone      varchar(64)  not null
);

alter table parent
    owner to onlineedu;

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

alter table family
    owner to onlineedu;

create table user_account
(
    id       serial       not null
        constraint user_account_pk
            primary key,
    role     varchar(64)  not null,
    username varchar(255) not null
        constraint user_account_un
            unique,
    password varchar      not null,
    student  integer
        constraint user_account_fk
            references student
            on update cascade on delete cascade,
    parent   integer
        constraint user_account_fk_1
            references parent
            on update cascade on delete cascade,
    teacher  integer
        constraint user_account_fk_2
            references teacher
            on update cascade on delete cascade
);

alter table user_account
    owner to onlineedu;

create table subject
(
    id   serial not null
        constraint subject_pk
            primary key,
    name varchar(255)
);

alter table subject
    owner to onlineedu;

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

alter table teaching
    owner to onlineedu;

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

alter table chat
    owner to onlineedu;

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
    role         varchar(64)   not null,
    text         varchar(1028) not null,
    time         timestamp     not null
);

alter table message
    owner to onlineedu;

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

alter table material
    owner to onlineedu;

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

alter table summary
    owner to onlineedu;

create table scheduled_teaching
(
    id       serial    not null
        constraint scheduled_teaching_pk
            primary key,
    teaching integer   not null
        constraint scheduled_teaching_fk
            references teaching
            on update cascade on delete cascade,
    "from"   timestamp not null,
    "to"     timestamp not null
);

alter table scheduled_teaching
    owner to onlineedu;

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
    "from"   timestamp,
    "to"     timestamp,
    date     timestamp,
    type     varchar(64)
);

alter table task
    owner to onlineedu;

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
    feedback varchar(1024)
);

alter table attempt
    owner to onlineedu;

