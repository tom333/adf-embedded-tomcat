

@@create_user

REM If this fails with ORA-01940 because summit_adf user is connected, issue the following as system:
REM select sid, serial#, username from v$session where username='SUMMIT_ADF';
REM alter system kill session '<SID>,<SERIAL#>' immediate;

REM Modify the next line to point to your database SID if not local (connect summit_adf/summit_adf@SID)

connect summit_adf/summit_adf
@@create_tables
@@create_sequences
@@create_functions
@@create_packages
@@/docker-entrypoint-initdb.d/data/S_REGION_Data
@@/docker-entrypoint-initdb.d/data/S_COUNTRIES_Data
@@/docker-entrypoint-initdb.d/data/S_DEPT_Data
@@/docker-entrypoint-initdb.d/data/S_TITLE_Data
@@/docker-entrypoint-initdb.d/data/S_EMP_Data
@@/docker-entrypoint-initdb.d/data/S_CREDIT_RATING_Data
@@/docker-entrypoint-initdb.d/data/S_CUSTOMER_Data
@@/docker-entrypoint-initdb.d/data/S_WAREHOUSE_Data
@@/docker-entrypoint-initdb.d/data/S_LONGTEXT_Data
@@/docker-entrypoint-initdb.d/data/S_IMAGE_Data
@@/docker-entrypoint-initdb.d/data/S_PRODUCT_CATEGORIES_Data
@@/docker-entrypoint-initdb.d/data/S_PRODUCT_Data
@@/docker-entrypoint-initdb.d/data/S_PAYMENT_TYPE_Data
@@/docker-entrypoint-initdb.d/data/S_PAYMENT_OPTIONS_Data
@@/docker-entrypoint-initdb.d/data/S_ORD_Data
@@/docker-entrypoint-initdb.d/data/S_ITEM_Data
@@/docker-entrypoint-initdb.d/data/S_INVENTORY_Data
@@create_triggers
@@create_type
commit;
