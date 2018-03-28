
CREATE TABLE service.users (
	 id        SERIAL PRIMARY KEY ,
     phone     varchar(40) NOT NULL UNIQUE,
     password  varchar(100) NOT NULL,
     name      varchar(40) NOT NULL,
     surname   varchar(40) NOT NULL,
     balance   float4 NOT NULL,
     confirm   bool NOT NULL
)
WITH (
	OIDS=FALSE
) ;