-- we don't know how to generate root <with-no-name> (class Root) :(
create table USERS
(
	ID VARCHAR(255) not null
		constraint USERS_PK
			primary key,
	NAME VARCHAR(200) not null,
	EMAIL VARCHAR(150) not null,
	PASSWORD VARCHAR(300) not null,
	CREATED DATE default CURRENT_DATE not null,
	MODIFIED DATE default CURRENT_DATE not null,
	LAST_LOGIN TIMESTAMP,
	TOKEN VARCHAR(400),
	IS_ACTIVE BOOLEAN default TRUE
);

create table PHONES
(
	NUMBER VARCHAR(20) not null,
	CITYCODE VARCHAR(10) not null,
	COUNTRYCODE VARCHAR(10) not null,
	ID_USER VARCHAR(255)
		constraint PHONES_USERS_ID_FK
			references USERS,
	constraint PHONES_PK
		primary key (NUMBER, CITYCODE, COUNTRYCODE)
);

create unique index USERS_ID_UINDEX
	on USERS (ID);

