/*************************************************************
*                  DATABASE INSERTION FLYWAY
**************************************************************/


/* Table departments */
INSERT INTO departments
(
    code,
    designation,
    created_at,
    updated_at)
VALUES (
   'TY859663',
   'Departement informatique',
   '2023-04-05','2023-04-05');

INSERT INTO departments
(
    code,
    designation,
    created_at,
    updated_at)
VALUES (
   'FF78200',
   'Departement RH',
   '2023-04-05','2023-04-05');



/* Table persons */

INSERT INTO persons
(
 firstname,
 lastname,
 age,
 id_department,
 created_at,
 updated_at)
VALUES (
    'Melissa',
    'Kouadio',
     56,
     1,
    '2023-04-05','2023-04-05');
INSERT INTO persons
(
 firstname,
 lastname,
 age,
 id_department,
 created_at,
 updated_at)
VALUES (
   'Meldev',
   'Koua',
   16, 2, '2023-04-05','2023-04-05');

INSERT INTO persons
(
 firstname,
 lastname,
 age,
 id_department,
 created_at,
 updated_at)
VALUES (
    'Fabrice',
    'Konate',
    56, 2, '2023-04-05','2023-04-05');
INSERT INTO persons
(
 firstname,
 lastname,
 age,
 id_department,
 created_at,
 updated_at)
VALUES (
   'Melissa',
   'Kouadio',
   36, 1, '2023-04-05','2023-04-05');
INSERT INTO persons
(
 firstname,
 lastname,
 age,
 id_department,
 created_at,
 updated_at)
VALUES (
   'Melissa',
   'Dion',
   26, 2, '2023-04-05','2023-04-05');


