 /*************************************************************
 *                  DATABASE CREATION FLYWAY
 **************************************************************/

/** Table departments **/
 CREATE TABLE departments
 (
     id          SERIAL PRIMARY KEY NOT NULL,
     code   VARCHAR(255) NOT NULL,
     designation    VARCHAR(255) NOT NULL,
     created_at  TIMESTAMP NOT NULL,
     updated_at TIMESTAMP
 );

/** Table persons **/
CREATE TABLE persons
(
    id          SERIAL  NOT NULL,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    age         INT4 NOT NULL,
    id_department    INT REFERENCES departments (id) ,
    created_at  TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

