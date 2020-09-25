/*Classroom*/

INSERT INTO public.classroom
("name")
VALUES('1.A');

INSERT INTO public.classroom
("name")
VALUES('1.B');

INSERT INTO public.classroom
("name")
VALUES('2.A');

INSERT INTO public.classroom
("name")
VALUES('3.A');

INSERT INTO public.classroom
("name")
VALUES('4.A');

INSERT INTO public.classroom
("name")
VALUES('4.B');


/*Student*/

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Ivana', 'Coufalová', 'ivana.coufalova@example.com', 0, '', '', '', 1);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Dana', 'Kadlecová', 'dana.kadlecova@example.com', 0, '', '', '', 1);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Michala', 'Jandová', 'michala.jandova@example.com', 0, '', '', '', 1);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Josef', 'Mašek', 'josef.masek@example.com', 0, '', '', '', 1);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Richard', 'Burda', 'richard.burda@example.com', 0, '', '', '', 1);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Jakub', 'Novotný', 'jakub.novotny@example.com', 0, '', '', '', 1);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Filip', 'Nejedlý', 'filip.nejedly@example.com', 0, '', '', '', 2);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('Petr', 'Dvořák', 'petr.dvorak@example.com', 0, '', '', '', 2);

INSERT INTO public.student
(firstname, surname, email, age, street, zip, chat_alert, classroom)
VALUES('David', 'Souček', 'david.soucek@example.com', 0, '', '', '', 3);


/*User*/

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'ivana.coufalova', 'ivana.coufalova', 1);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'dana.kadlecova', 'dana.kadlecova', 2);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'michala.jandova', 'michala.jandova', 3);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'josef.masek', 'josef.masek', 4);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'richard.burda', 'richard.burda', 5);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'jakub.novotny', 'jakub.novotny', 6);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'filip.nejedly', 'filip.nejedly', 7);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'petr.dvorak', 'petr.dvorak', 8);

INSERT INTO public.user_account
("role", username, "password", student)
VALUES('student', 'david.soucek', 'david.soucek', 9);


/*Teacher*/

INSERT INTO public.teacher
(email, firstname, surname, age, street, zip, phone, classroom)
VALUES('jana.novakova@example.com', 'Jana', 'Nováková', 40, 'Dolnohradská 50', ' 255 55', '111 222 333', 1);

INSERT INTO public.teacher
(email, firstname, surname, age, street, zip, phone, classroom)
VALUES('jana.kopecka@example.com', 'Jana', 'Kopecká', 42, 'Vysoká 41', ' 255 55', '111 222 444', 2);

INSERT INTO public.teacher
(email, firstname, surname, age, street, zip, phone, classroom)
VALUES('lucie.petraskova@example.com', 'Lucie', 'Petrásková', 60, 'Šedivá 10', ' 255 05', '555 555 555', 3);

INSERT INTO public.teacher
(email, firstname, surname, age, street, zip, phone, classroom)
VALUES('marie.vavrova@example.com', 'Marie', 'Vávrová', 28, 'Dolnomoravská 50', ' 255 55', '111 222 555', 4);

INSERT INTO public.teacher
(email, firstname, surname, age, street, zip, phone, classroom)
VALUES('patricie.petrova@example.com', 'Patrície Petrová', 'Nováková', 46, 'Mordá 50', ' 255 55', '111 222 666', 5);

INSERT INTO public.teacher
(email, firstname, surname, age, street, zip, phone, classroom)
VALUES('magdalena.otrubova@example.com', 'Magdaléna', 'Otrubová', 48, 'Žlutá 50', ' 255 55', '111 222 777', 6);


/*User*/

INSERT INTO public.user_account
("role", username, "password", teacher)
VALUES('teacher', 'jana.novakova', 'jana.novakova', 1);

INSERT INTO public.user_account
("role", username, "password", teacher)
VALUES('teacher', 'jana.kopecka', 'jana.kopecka', 2);

INSERT INTO public.user_account
("role", username, "password", teacher)
VALUES('teacher', 'lucie.petraskova', 'lucie.petraskova', 3);

INSERT INTO public.user_account
("role", username, "password", teacher)
VALUES('teacher', 'marie.vavrova', 'marie.vavrova', 4);

INSERT INTO public.user_account
("role", username, "password", teacher)
VALUES('teacher', 'patricie.petrova', 'patricie.petrova', 5);

INSERT INTO public.user_account
("role", username, "password", teacher)
VALUES('teacher', 'magdalena.otrubova', 'magdalena.otrubova', 6);


/*Subject*/

INSERT INTO public.subject
("name")
VALUES('Český jazyk');

INSERT INTO public.subject
("name")
VALUES('Matematika');

INSERT INTO public.subject
("name")
VALUES('Anglický jazyk');

INSERT INTO public.subject
("name")
VALUES('Informatika');

INSERT INTO public.subject
("name")
VALUES('Vlastivěda');

INSERT INTO public.subject
("name")
VALUES('Hudební výchova');

INSERT INTO public.subject
("name")
VALUES('Prvouka');

INSERT INTO public.subject
("name")
VALUES('Přírodověda');


/*Teaching*/

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(1, 1, 1);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(1, 1, 2);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(1, 1, 3);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(2, 1, 4);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(3, 1, 5);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(2, 1, 6);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(1, 1, 7);

INSERT INTO public.teaching
(teacher, classroom, subject)
VALUES(1, 1, 8);


/*Task*/

INSERT INTO public.task
(teaching, "text")
VALUES(1, 'Zadání prvního úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(1, 'Zadání druhého úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(1, 'Zadání třetího úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(2, 'Zadání prvního úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(3, 'Zadání prvního úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(4, 'Zadání prvního úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(4, 'Zadání druhého úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(5, 'Zadání prvního úkolu');

INSERT INTO public.task
(teaching, "text")
VALUES(6, 'Zadání prvního úkolu');


/*Summary*/

INSERT INTO public.summary
(teaching, student, final_grade, feedback)
VALUES(1, 1, '1', 'Paráda');

INSERT INTO public.summary
(teaching, student, final_grade, feedback)
VALUES(1, 2, '1', 'Dobrá práce');

INSERT INTO public.summary
(teaching, student, final_grade, feedback)
VALUES(1, 3, '2', 'Šlo to');

INSERT INTO public.summary
(teaching, student, final_grade, feedback)
VALUES(1, 4, '3', 'Chtělo by to zlepšit');

INSERT INTO public.summary
(teaching, student, final_grade, feedback)
VALUES(1, 5, '2', 'Docela dobré');


/*Parent*/

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('lubomir.coufal@example.com', 'Lubomír', 'Coufal', 50, 'Spálená 20', '111 02', '123456789');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('radka.coufalova@example.com', 'Radka', 'Coufalová', 50, '', '', '123456789');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('radka.kadlecova@example.com', 'Radka', 'Kadlecová', 40, '', '', '111 111 111');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('bohumila.jandova@example.com', 'Bohumila', 'Jandová', 0, '', '', '');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('lukas.masek@example.com', 'Lukáš', 'Mašek', 0, '', '', '222 222 222');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('evzen.burda@example.com', 'Evžen', 'Burda', 0, 'Úzká 12', '158 03', '333 333 333');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('lucie.novotna@example.com', 'Lucie', 'Novotná', 0, '', '', '');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('alexandr.nejedly@example.com', 'Alexandr', 'Nejedlý', 0, '', '', '');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('martina.dvorakova@example.com', 'Martina', 'Dvořáková', 0, '', '', '');

INSERT INTO public.parent
(email, firstname, surname, age, street, zip, phone)
VALUES('roman.soucek@example.com', 'Roman', 'Souček', 0, '', '', '444 444 444');


/*User*/

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'lubomir.coufal', 'lubomir.coufal', 1);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'radka.coufalova', 'radka.coufalova', 2);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'radka.kadlecova', 'radka.kadlecova', 3);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'bohumila.jandova', 'bohumila.jandova', 4);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'lukas.masek', 'lukas.masek', 5);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'evzen.burda', 'evzen.burda', 6);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'lucie.novotna', 'lucie.novotna', 7);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'alexandr.nejedly', 'alexandr.nejedly', 8);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'martina.dvorakova', 'martina.dvorakova', 9);

INSERT INTO public.user_account
("role", username, "password", parent)
VALUES('parent', 'roman.soucek', 'roman.soucek', 10);



/*Material*/

INSERT INTO public.material
(teaching, "name", url, "type")
VALUES(1, 'první materiál', 'www.google.com', '');

INSERT INTO public.material
(teaching, "name", url, "type")
VALUES(1, 'druhý materiál', 'www.seznam.cz', '');

INSERT INTO public.material
(teaching, "name", url, "type")
VALUES(2, 'první materiál', 'www.google.com', '');

INSERT INTO public.material
(teaching, "name", url, "type")
VALUES(2, 'druhý materiál', 'www.google.com', '');


/*Family*/

INSERT INTO public."family"
(student, parent)
VALUES(1, 1);

INSERT INTO public."family"
(student, parent)
VALUES(1, 2);

INSERT INTO public."family"
(student, parent)
VALUES(2, 3);

INSERT INTO public."family"
(student, parent)
VALUES(3, 4);

INSERT INTO public."family"
(student, parent)
VALUES(4, 5);

INSERT INTO public."family"
(student, parent)
VALUES(5, 6);

INSERT INTO public."family"
(student, parent)
VALUES(6, 7);

INSERT INTO public."family"
(student, parent)
VALUES(7, 8);

INSERT INTO public."family"
(student, parent)
VALUES(8, 9);

INSERT INTO public."family"
(student, parent)
VALUES(9, 10);


