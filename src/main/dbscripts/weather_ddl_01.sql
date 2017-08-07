create user weatherinfo identified by 123456;
grant dba to weatherinfo;

create table weatherInfo (
  city VARCHAR2(64),
  curr_min float,
  curr_max float,
  curr_temp float,
  curr_time timestamp,
  UNIQUE (city, curr_time)
);



