INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Maria", "Camus", "maria.camus@usach.cl", "19.787.283-3");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Nacho", "Ortiz", "nacho.ortiz@usach.cl", "19.878.234-2");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Tamara", "Zuñiga", "tamara.zuniga@usach.cl", "19.543.523-4");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Andrea", "Martinez", "andrea.martinez@usach.cl", "18.424.212-3");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Juan", "Jara", "juan.jara@usach.cl", "19.680.541-k");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Rodrigo", "Scheel", "rodrigo.scheel@usach.cl", "19.321.123-0");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Ruth", "Marin", "ruth.marin@usach.cl", "18.784.983-1");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Jose", "Bahamondes", "jose.bahamondes@usach.cl", "19.563.112-1");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Enrique", "Aviles", "enrique.aviles@usach.cl", "19.223.511-8");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Veronica", "Calas", "veronica.calas@usach.cl", "19.134.474-7");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Alcides", "Quispe", "alcides.quispe@usach.cl", "16.112.332-4");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Natalia", "Guzman", "natalia.guzman.s@usach.cl", "19.680.464-1");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Mario", "Alvarez", "mario.alvarez.m@usach.cl", "19.522.760-8");
INSERT INTO users (first_name, last_name, mail, rut) VALUES ("Joaquin", "Jara", "joaquin.jara@usach.cl", "19.739.353-k");


INSERT INTO roles (name) VALUES ("Student");
INSERT INTO roles (name) VALUES ("Teacher");

INSERT INTO roles_users (user_id, role_id) VALUES (1,1);
INSERT INTO roles_users (user_id, role_id) VALUES (2,1);
INSERT INTO roles_users (user_id, role_id) VALUES (3,1);
INSERT INTO roles_users (user_id, role_id) VALUES (4,1);
INSERT INTO roles_users (user_id, role_id) VALUES (5,1);
INSERT INTO roles_users (user_id, role_id) VALUES (6,1);
INSERT INTO roles_users (user_id, role_id) VALUES (7,1);
INSERT INTO roles_users (user_id, role_id) VALUES (8,1);
INSERT INTO roles_users (user_id, role_id) VALUES (9,1);
INSERT INTO roles_users (user_id, role_id) VALUES (10,1);
INSERT INTO roles_users (user_id, role_id) VALUES (11,2);
INSERT INTO roles_users (user_id, role_id) VALUES (12,1);
INSERT INTO roles_users (user_id, role_id) VALUES (13,1);
INSERT INTO roles_users (user_id, role_id) VALUES (14,1);

INSERT INTO sections (semester) VALUES ("2-A1");
INSERT INTO sections (semester) VALUES ("2-A2");

INSERT INTO sections_students (student_id, section_id) VALUES (1,1);
INSERT INTO sections_students (student_id, section_id) VALUES (2,1);
INSERT INTO sections_students (student_id, section_id) VALUES (3,1);
INSERT INTO sections_students (student_id, section_id) VALUES (4,1);
INSERT INTO sections_students (student_id, section_id) VALUES (5,1);
INSERT INTO sections_students (student_id, section_id) VALUES (13,1);


INSERT INTO sections_students (student_id, section_id) VALUES (6,2);
INSERT INTO sections_students (student_id, section_id) VALUES (7,2);
INSERT INTO sections_students (student_id, section_id) VALUES (8,2);
INSERT INTO sections_students (student_id, section_id) VALUES (9,2);
INSERT INTO sections_students (student_id, section_id) VALUES (10,2);
INSERT INTO sections_students (student_id, section_id) VALUES (12,2);
INSERT INTO sections_students (student_id, section_id) VALUES (14,2);

INSERT INTO sections_teachers (teacher_id, section_id) VALUES (11,1);
INSERT INTO sections_teachers (teacher_id, section_id) VALUES (11,2);

INSERT INTO problems (deadline , published_at, statement, title, updated_at, teacher_id) VALUES ("2017-12-12 09:30:00","2017-12-12 08:00:00","Genere un programa que entregue el factorial de un numero","Factorial","2017-12-12 08:00:00" , 11);
INSERT INTO problems (deadline , published_at, statement, title, updated_at, teacher_id) VALUES ("2017-12-30 23:59:59","2017-12-16 11:30:11","Genere un programa que entregue la sumatoria de numeros naturales hasta un numero dado por el usuario","Sumatoria","2017-12-16 11:30:11", 11);

INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-12 08:00:01", "2017-12-12 09:30:00", null, 1, 1);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-12 08:00:02", "2017-12-12 09:30:00", null, 1, 2);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-12 08:00:03", "2017-12-12 09:30:00", null, 1, 3);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-12 08:00:04", "2017-12-12 09:30:00", null, 1, 4);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-12 08:00:05", "2017-12-12 09:30:00", null, 1, 5);

INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:12", "2017-12-30 23:59:59", null, 2, 6);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:13", "2017-12-30 23:59:59", null, 2, 7);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:14", "2017-12-30 23:59:59", null, 2, 8);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:15", "2017-12-30 23:59:59", null, 2, 9);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:16", "2017-12-12 09:30:00", null, 1, 12);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:16", "2017-12-30 23:59:59", null, 2, 12);

INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:16", "2017-12-12 09:30:00", null, 1, 14);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:16", "2017-12-30 23:59:59", null, 2, 14);

INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:16", "2017-12-12 09:30:00", null, 1, 13);
INSERT INTO assignments (assigned_at, deadline, grade, problem_id, student_id) VALUES ("2017-12-16 11:30:16", "2017-12-30 23:59:59", null, 2, 13);

INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Factorial de 3","3","6",1);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Factorial de 4","4","24",1);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Factorial de 5","5","120",1);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Factorial de 6","6","720",1);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Factorial de 7","7","5040",1);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Factorial de 8","8","40320",1);

INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Sumatoria de 1","1","1",2);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Sumatoria de 2","2","3",2);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Sumatoria de 3","3","6",2);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Sumatoria de 4","4","10",2);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Sumatoria de 5","5","15",2);
INSERT INTO test_cases (description, input, output, problem_id) VALUES ("Sumatoria de 6","6","21",2);




