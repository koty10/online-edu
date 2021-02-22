/* Admin */

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('admin', 'admin', 'admin', 'admin@example.com', 'Daniel', 'Koten', 'ulice', 'psč', '123456789');

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

/*User*/

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'ivana.coufalova', 'ivana.coufalova', 'Ivana', 'Coufalová', 'ivana.coufalova@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'dana.kadlecova', 'dana.kadlecova', 'Dana', 'Kadlecová', 'dana.kadlecova@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'michala.jandova', 'michala.jandova', 'Michala', 'Jandová', 'michala.jandova@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'josef.masek', 'josef.masek', 'Josef', 'Mašek', 'josef.masek@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'richard.burda', 'richard.burda', 'Richard', 'Burda', 'richard.burda@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'jakub.novotny', 'jakub.novotny', 'Jakub', 'Novotný', 'jakub.novotny@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'filip.nejedly', 'filip.nejedly', 'Filip', 'Nejedlý', 'filip.nejedly@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'petr.dvorak', 'petr.dvorak', 'Petr', 'Dvořák', 'petr.dvorak@example.com', '', '');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip)
VALUES('student', 'david.soucek', 'david.soucek', 'David', 'Souček', 'david.soucek@example.com', '', '');

/*Student*/

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 1, 1);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 1, 2);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 1, 3);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 1, 4);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 1, 5);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 1, 6);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 2, 7);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 2, 8);

INSERT INTO public.student
(chat_alert, classroom, user_account)
VALUES('', 3, 9);



/*User*/

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('teacher', 'jana.novakova', 'jana.novakova', 'jana.novakova@example.com', 'Jana', 'Nováková', 'Dolnohradská 50', ' 255 55', '111 222 333');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('teacher', 'jana.kopecka', 'jana.kopecka', 'jana.kopecka@example.com', 'Jana', 'Kopecká', 'Vysoká 41', ' 255 55', '111 222 444');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('teacher', 'lucie.petraskova', 'lucie.petraskova', 'lucie.petraskova@example.com', 'Lucie', 'Petrásková', 'Šedivá 10', ' 255 05', '555 555 555');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('teacher', 'marie.vavrova', 'marie.vavrova', 'marie.vavrova@example.com', 'Marie', 'Vávrová', 'Dolnomoravská 50', ' 255 55', '111 222 555');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('teacher', 'patricie.petrova', 'patricie.petrova', 'patricie.petrova@example.com', 'Patrície Petrová', 'Nováková', 'Mordá 50', ' 255 55', '111 222 666');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('teacher', 'magdalena.otrubova', 'magdalena.otrubova', 'magdalena.otrubova@example.com', 'Magdaléna', 'Otrubová', 'Žlutá 50', ' 255 55', '111 222 777');


/*Teacher*/

INSERT INTO public.teacher
(classroom, user_account)
VALUES(1, 10);

INSERT INTO public.teacher
(classroom, user_account)
VALUES(2, 11);

INSERT INTO public.teacher
(classroom, user_account)
VALUES(3, 12);

INSERT INTO public.teacher
(classroom, user_account)
VALUES(4, 13);

INSERT INTO public.teacher
(classroom, user_account)
VALUES(5, 14);

INSERT INTO public.teacher
(classroom, user_account)
VALUES(6, 15);


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


/*User*/

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'lubomir.coufal', 'lubomir.coufal', 'lubomir.coufal@example.com', 'Lubomír', 'Coufal', 'Spálená 20', '111 02', '123456789');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'radka.coufalova', 'radka.coufalova', 'radka.coufalova@example.com', 'Radka', 'Coufalová', '', '', '123456789');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'radka.kadlecova', 'radka.kadlecova', 'radka.kadlecova@example.com', 'Radka', 'Kadlecová', '', '', '111 111 111');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'bohumila.jandova', 'bohumila.jandova', 'bohumila.jandova@example.com', 'Bohumila', 'Jandová', '', '', '');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'lukas.masek', 'lukas.masek', 'lukas.masek@example.com', 'Lukáš', 'Mašek', '', '', '222 222 222');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'evzen.burda', 'evzen.burda', 'evzen.burda@example.com', 'Evžen', 'Burda', 'Úzká 12', '158 03', '333 333 333');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'lucie.novotna', 'lucie.novotna', 'lucie.novotna@example.com', 'Lucie', 'Novotná', '', '', '');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'alexandr.nejedly', 'alexandr.nejedly', 'alexandr.nejedly@example.com', 'Alexandr', 'Nejedlý', '', '', '');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'martina.dvorakova', 'martina.dvorakova', 'martina.dvorakova@example.com', 'Martina', 'Dvořáková', '', '', '');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, phone)
VALUES('parent', 'roman.soucek', 'roman.soucek', 'roman.soucek@example.com', 'Roman', 'Souček', '', '', '444 444 444');



/*Parent*/

INSERT INTO public.parent
(user_account)
VALUES(16);
INSERT INTO public.parent
(user_account)
VALUES(17);
INSERT INTO public.parent
(user_account)
VALUES(18);
INSERT INTO public.parent
(user_account)
VALUES(19);
INSERT INTO public.parent
(user_account)
VALUES(20);
INSERT INTO public.parent
(user_account)
VALUES(21);
INSERT INTO public.parent
(user_account)
VALUES(22);
INSERT INTO public.parent
(user_account)
VALUES(23);
INSERT INTO public.parent
(user_account)
VALUES(24);
INSERT INTO public.parent
(user_account)
VALUES(25);



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


