drop table user;
drop table address;
drop table organisation;
drop table service;
drop table event;



create table user
(
    name          varchar(255) NOT NULL,
    first_name    varchar(255) NOT NULL,
    date_of_birth DATE,
    role          varchar(255) NOT NULL, /* organiser, participants, user */
    password      varchar(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (name)
);

# insert into user (name, first_name, date_of_birth, password) VALUE ('Praneeth', 'Praneth Madusanka', '2008-7-04', 'cg@cg');

create table event
(
    eventId integer      NOT NULL,
    name    varchar(255) NOT NULL,
    status  varchar(255) NOT NULL,
    CONSTRAINT pk_event PRIMARY KEY (eventId)
);

# insert into event (eventId, name, status) VALUE (1, 'Praneeth1', CURDATE());

create table service
(
    title       varchar(255) NOT NULL,
    eventId     integer      NOT NULL,
    description varchar(255) NOT NULL,
    percentage  integer      NOT NULL,
    CONSTRAINT pk_service PRIMARY KEY (title),
    CONSTRAINT fk_service FOREIGN KEY (eventId) REFERENCES event (eventId)
);

create table organisation
(
    eventId   integer      NOT NULL,
    date      DATE         NOT NULL,
    capacity  integer      NOT NULL,
    outdoors  varchar(255),
    age_limit integer,
    property  varchar(255) NOT NULL,
    CONSTRAINT pk_org PRIMARY KEY (eventId),
    CONSTRAINT fk_org FOREIGN KEY (eventId) REFERENCES event (eventId)
);

create table address
(
    eventId     integer      NOT NULL,
    number      integer      NOT NULL,
    street      varchar(255) NOT NULL,
    postal_code varchar(255) NOT NULL,
    city        varchar(255) NOT NULL,
    country     varchar(255) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (eventId),
    CONSTRAINT fk_address FOREIGN KEY (eventId) REFERENCES event (eventId)
);
