delete from region;
insert into region(id, nom) values (1, 'Hauts-de-France'), (2, 'Grand Est'), (3, 'Normandie'), (4, 'Ile-de-France');

delete from club;
insert into club(id, nom, region_id) values (1, 'Lille',     1), (2, 'Cambrai',   1), (3, 'Abbeville', 1), (4, 'Colmar',    2), (5, 'Le Havre',  3), (6, 'Bailleau',  4);

delete from pilote;
insert into pilote(id, prenom, nom, dateNaissance, club_id) values (1,  'Paul',      'Leroy',    '1965-02-13', 1), (2,  'Maxime',    'Martin',   '1982-01-23', 2), (3,  'Thomas',    'Durand',   '1995-10-13', 1), (4,  'Sebastien', 'Lefebvre', '1958-02-13', 2), (5,  'Daniel',    'Bernard',  '1974-02-13', 5), (6,  'Jerome',    'Dubois',   '1968-02-13', 4), (7,  'Vincent',   'Thomas',   '1978-02-13', 3), (8,  'Florence',  'Richard',  '1981-02-13', 2), (9,  'Marie',     'Petit',    '1984-02-13', 6), (10, 'Manu',      'Leroy',    '1975-02-13', 6);

delete from repas;
insert into repas(id, date, heure) values (1, '2017-07-01', '12:00:00'), (2, '2017-07-01', '18:30:00'), (3, '2017-07-02', '11:00:00'), (4, '2017-07-02', '12:30:00'), (5, '2017-07-02', '19:00:00'), (6, '2017-07-03', '20:30:00');

delete from reservation;
insert into reservation(repas_id, pilote_id, nbPersonnes) values (1, 1, 2), (1, 3, 5), (1, 6, 3), (1, 8, 5), (1, 9, 5), (2, 1, 6), (2, 2, 6), (2, 6, 5), (3, 1, 2), (3, 2, 2), (3, 3, 2), (3, 4, 2), (4, 6, 2), (4, 7, 2), (4, 8, 2), (6, 1, 21);
