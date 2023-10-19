create table if not exists animal(
id bigserial primary key,
animalType varchar(20),
name varchar(20),
age integer,
breed varchar(20),
pathToPhoto varchar(150)
    );

create table if not exists animalQwner(
id bigserial primary key,
name varchar(20),
phoneNumber varchar(20),
eMail varchar(100),
trialPeriod boolean
);

create table if not exists botUser(
id bigserial primary key,
name varchar(100),
local_date_time timestamp
);

create table if not exists petReport(
id bigserial primary key,
diet varchar(150),
feelings varchar(150),
check boolean,
local_date_time timestamp
);

create table if not exists photo(
id bigserial primary key,
filePath varchar(20),
fileSize bigserial,
mediaType varchar(20),
data  image
);

create table if not exists shelter(
id bigserial primary key,
shelterType varchar(150),
shelterName varchar(150),
address varchar(150),
information varchar(350),
);

create table if not exists users(
id bigserial primary key,
name varchar(100),
animalId bigserial,
local_date_time timestamp
telephone varchar(100),
);

create table if not exists volunteer(
id bigserial primary key,
name varchar(100),
phoneNumber varchar(100),
);