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
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'ivana.coufalova', 'WdwEI/s8ro1QsrPKDyu4xNjoxEgW86IUAx0xtbTKe98=', 'Ivana', 'Coufalová', 'ivana.coufalova@example.com', 'Polní 10', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'dana.kadlecova', 'U4bm2X13JkFWRD3s8tBliWJT0p0nVnlMgyOOX8GH1Ho=', 'Dana', 'Kadlecová', 'dana.kadlecova@example.com', 'Polní 20', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'michala.jandova', '9eh9mFVG/ngJK2SjPVkuF1V6n25a3L+9H6rS1/PGzX0=', 'Michala', 'Jandová', 'michala.jandova@example.com', 'Polní 30', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'josef.masek', '1L+8TUBn4GV9PcbmlZ0lLmYFwAOokfcXXS+6N+COnzY=', 'Josef', 'Mašek', 'josef.masek@example.com', 'Polní 40', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'richard.burda', '35AV/BUoIf4JkJR46i3pLS9mNrT2NumJV+UniW63GDw=', 'Richard', 'Burda', 'richard.burda@example.com', 'Polní 50', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'jakub.novotny', 'fM1o/2NfJgdUsBO0XedRRf2h4NGat5NXWzEOqbuDjOY=', 'Jakub', 'Novotný', 'jakub.novotny@example.com', 'Polní 60', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'filip.nejedly', '5GAWF3bI4g5sxfPs6DQRw22iVw/1cgMruYV3UrBHM7k=', 'Filip', 'Nejedlý', 'filip.nejedly@example.com', 'Polní 70', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'petr.dvorak', 'SFsh/+eARGwdHFhDqI8mvbsZvArl5EvB3/01yvCdq28=', 'Petr', 'Dvořák', 'petr.dvorak@example.com', 'Polní 80', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", firstname, surname, email, street, zip, city, phone, birthday, registered)
VALUES('student', 'david.soucek', 'H+AUUUFYEG5+pkZqbZPjD3jKKvWkdhfjt5MPsz9QOtA=', 'David', 'Souček', 'david.soucek@example.com', 'Polní 90', '111 22', 'Vidlákov', '123456789', '2010-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

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
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('teacher', 'jana.novakova', 'VTpuTbgPEgkiV9hBBEU3vsuW+kqo3xW8XVtnfYANqUA=', 'jana.novakova@example.com', 'Jana', 'Nováková', 'Dolnohradská 50', ' 255 55', 'Praha', '111 222 333', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('teacher', 'jana.kopecka', 'cdormPrA/sPldpxcPHU6i4tMVfJ2jNnS7/xvVdIDDiI=', 'jana.kopecka@example.com', 'Jana', 'Kopecká', 'Vysoká 41', ' 255 55', 'Praha', '111 222 444', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('teacher', 'lucie.petraskova', 'TU49Q4frSxOhrQYt0UIfeaYm5+JPK7rdmUt4j80Rs4s=', 'lucie.petraskova@example.com', 'Lucie', 'Petrásková', 'Šedivá 10', ' 255 05', 'Praha', '555 555 555', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('teacher', 'marie.vavrova', 'DPDmSdUkjj3HHSrbL+t536IbkUhyifJCTwCsYOc0Tfc=', 'marie.vavrova@example.com', 'Marie', 'Vávrová', 'Dolnomoravská 50', ' 255 55', 'Praha', '111 222 555', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('teacher', 'patricie.petrova', 'F0aox1N0xBUL8d84FfWC7kRW7Gtb6ZVySpEVArYsWG4=', 'patricie.petrova@example.com', 'Patrície', 'Petrová', 'Mordá 50', ' 255 55', 'Praha', '111 222 666', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('teacher', 'magdalena.otrubova', 'chKDLgxAovr/IHHHBg1zzlU3pT281r5FPWgQ4bNFJYo=', 'magdalena.otrubova@example.com', 'Magdaléna', 'Otrubová', 'Žlutá 50', ' 255 55', 'Praha', '111 222 777', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');


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
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(1, '<p>Zadání prvního úkolu</p>', 'První úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(1, '<p>Zadání druhého úkolu</p>', 'Druhý úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(1, '<p>Zadání třetího úkolu</p>', 'Třetí úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, date, type, points)
VALUES(1, '<p>Zadání dobrovolného úkolu...</p>', 'Úkol navíc', '2021-03-02 19:30:53.484', 'extra', 10);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(2, '<p>Zadání prvního úkolu</p>', 'První úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(3, '<p>Zadání prvního úkolu</p>', 'První úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(4, '<p>Zadání prvního úkolu</p>', 'První úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(4, '<p>Zadání druhého úkolu</p>', 'Druhý úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(5, '<p>Zadání prvního úkolu</p>', 'První úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);

INSERT INTO public.task
(teaching, "text", name, time_from, time_to, date, type, points)
VALUES(6, '<p>Zadání prvního úkolu</p>', 'První úkol', '2021-03-26 19:30:00', '2021-03-27 19:30:00', '2021-03-02 19:30:53.484', 'normal', 0);


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
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'lubomir.coufal', '/Kvmc4DCbw7B4B81Vk2OTixXNnZ0xiITHntDBMa5kGs=', 'lubomir.coufal@example.com', 'Lubomír', 'Coufal', 'Polní 10', '111 22', 'Vidlákov', '123 456 789', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'radka.coufalova', '7qhhYmPM7dwlOcUhYCYmYQfe8e90M03nsyyF1hQ1Qtw=', 'radka.coufalova@example.com', 'Radka', 'Coufalová', 'Polní 10', '111 22', 'Vidlákov', '123 456 789', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'radka.kadlecova', 'V/cMocjrl1I8S4SpcLIerK18wUKCh9swFPf4EGgb/as=', 'radka.kadlecova@example.com', 'Radka', 'Kadlecová', 'Polní 20', '111 22', 'Vidlákov', '111 111 111', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'bohumila.jandova', 'a/qU4DRp26ucaqeG/fjYmMsE3X9oT20e5ArYiyA8Iks=', 'bohumila.jandova@example.com', 'Bohumila', 'Jandová', 'Polní 30', '111 22', 'Vidlákov', '123 456 789', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'lukas.masek', 'dLRiyuKBru3yrunHn2yNrsZ6w5P8oDp8uFDuQF4/gRc=', 'lukas.masek@example.com', 'Lukáš', 'Mašek', 'Polní 40', '111 22', 'Vidlákov', '222 222 222', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'evzen.burda', 'IFloIiKz85mZUnvyycA6c6vN+mg0B9YyBDrXiMteqb0=', 'evzen.burda@example.com', 'Evžen', 'Burda', 'Polní 50', '111 22', 'Vidlákov', '333 333 333', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'lucie.novotna', '6WsqFrurkLvde8XzUrWD/CbGplD9nKD3VKZisHp4i3w=', 'lucie.novotna@example.com', 'Lucie', 'Novotná', 'Polní 60', '111 22', 'Vidlákov', '111 111 111', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'alexandr.nejedly', 'KBwisAQ6aXzaDNw6DSEuOBava+UwbBGuxBolz1bbg6c=', 'alexandr.nejedly@example.com', 'Alexandr', 'Nejedlý', 'Polní 70', '111 22', 'Vidlákov', '222 222 222', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'martina.dvorakova', 'sDHrIJE2CVdyPRIxsYhMoDh7WdKl8k09awLyB4LP4Sc=', 'martina.dvorakova@example.com', 'Martina', 'Dvořáková', 'Polní 80', '111 22', 'Vidlákov', '333 333 333', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('parent', 'roman.soucek', 'X4U9bj9IHWqfzygTAdGfYNK/VLBqNzqVveyhZyq9KLg=', 'roman.soucek@example.com', 'Roman', 'Souček', 'Polní 90', '111 22', 'Vidlákov', '444 444 444', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');


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


/* Admin */

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered)
VALUES('admin', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin@example.com', 'Daniel', 'Koten', 'ulice', 'psč', 'město', '123456789', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469');
