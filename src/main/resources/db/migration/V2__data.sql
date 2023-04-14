/*************************************************************
*                  DATABASE INSERTION FLYWAY
**************************************************************/

/* Table departments */
INSERT INTO departments
(code,designation, created_at,updated_at)
VALUES
('TY859663', 'Departement informatique', '2023-04-05','2023-04-05'),
('FF78200','Departement RH','2023-04-05','2023-04-05');


/* Table persons */
INSERT INTO persons
(first_name,last_name, age, id_department, created_at, updated_at)
VALUES ('Melissa', 'Kouadio', 56, 1,'2023-04-05','2023-04-05'),
('Meldev','Koua',16, 2, '2023-04-05','2023-04-05'),
('Fabrice','Konate', 56, 2, '2023-04-05','2023-04-05'),
('Melissa','Kouadio',36, 1, '2023-04-05','2023-04-05'),
( 'Melissa','Dion',26, 2, '2023-04-05','2023-04-05');


