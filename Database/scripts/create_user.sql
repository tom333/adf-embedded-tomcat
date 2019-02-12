DROP USER summit_adf CASCADE;

CREATE USER summit_adf IDENTIFIED BY summit_adf DEFAULT TABLESPACE users 
                                  TEMPORARY TABLESPACE temp  
                                  QUOTA UNLIMITED ON users;

GRANT create session TO summit_adf ;
GRANT alter session TO summit_adf ;
GRANT create table TO summit_adf ;
GRANT create trigger TO summit_adf ;
GRANT create view TO summit_adf ;
GRANT create sequence TO summit_adf ;
GRANT create synonym TO summit_adf ;
GRANT create type TO summit_adf ;
GRANT create procedure TO summit_adf ;
