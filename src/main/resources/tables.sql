
CREATE TABLE service.users (
	 id        SERIAL PRIMARY KEY ,
     phone     varchar(40) NOT NULL UNIQUE,
     password  varchar(100) NOT NULL,
     name      varchar(40) NOT NULL,
     surname   varchar(40) NOT NULL,
     balance   float4 NOT NULL DEFAULT 0,
     confirm   bool NOT NULL DEFAULT false
)
WITH (
	OIDS=FALSE
) ;