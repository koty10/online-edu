/* Subject */

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


/* Admin */

INSERT INTO public.user_account
("role", username, "password", email, firstname, surname, street, zip, city, phone, birthday, registered, gender)
VALUES('admin', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin@example.com', 'Daniel', 'Koten', 'ulice', 'psč', 'město', '123456789', '1990-03-01 16:58:47.469', '2021-03-01 16:58:47.469', 'male');

