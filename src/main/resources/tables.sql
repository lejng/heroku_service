
CREATE TABLE service.users (
	 id        SERIAL PRIMARY KEY ,
     phone     varchar(40) NOT NULL UNIQUE,
     password  varchar(100) NOT NULL,
     name      varchar(40) NOT NULL,
     surname   varchar(40) NOT NULL,
     balance   float4 NOT NULL DEFAULT 0,
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE service.advertising (
	 id        SERIAL PRIMARY KEY ,
     link      varchar(500) NOT NULL,
     cost      float4 NOT NULL,
     timer     integer DEFAULT 0
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE service.users_advertising (
     id              SERIAL PRIMARY KEY,
     id_advertising  integer NOT NULL REFERENCES service.advertising(id) ON DELETE CASCADE ON UPDATE CASCADE,
     id_user         integer NOT NULL REFERENCES service.users(id) ON DELETE CASCADE ON UPDATE CASCADE,
     viewed          bool NOT NULL DEFAULT false
)WITH (
	OIDS=FALSE
) ;

CREATE TABLE service.phone_confirm (
	 id            SERIAL PRIMARY KEY ,
     phone         varchar(40) NOT NULL UNIQUE,
     confirm_code  varchar(40) NOT NULL
)
WITH (
	OIDS=FALSE
) ;
