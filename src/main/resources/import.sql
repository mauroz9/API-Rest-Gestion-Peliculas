-- DIRECTORES (Existentes y nuevos)
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'Christopher Nolan', 1970);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'Greta Gerwig', 1983);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'Steven Spielberg', 1946);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'Quentin Tarantino', 1963);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'Martin Scorsese', 1942);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'Sofia Coppola', 1971);
INSERT INTO director (id, nombre, anio_nacimiento) VALUES (nextval('director_seq'), 'George Lucas', 1944);

-- ACTORES (Existentes y nuevos)
INSERT INTO actor (id, nombre) VALUES (nextval('actor_seq'), 'Leonardo DiCaprio');
INSERT INTO actor (id, nombre) VALUES (nextval('actor_seq'), 'Margot Robbie');
INSERT INTO actor (id, nombre) VALUES (nextval('actor_seq'), 'Tom Hanks');
INSERT INTO actor (id, nombre) VALUES (nextval('actor_seq'), 'Brad Pitt');
INSERT INTO actor (id, nombre) VALUES (nextval('actor_seq'), 'Robert De Niro');
INSERT INTO actor (id, nombre) VALUES (nextval('actor_seq'), 'Scarlett Johansson');

-- PELÍCULAS (Existentes y nuevas - Buscando ID de director en una línea)
INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (nextval('pelicula_seq'), 'Inception', 'Sci-Fi', '2010-07-16', (SELECT id FROM director WHERE nombre = 'Christopher Nolan'));
INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (nextval('pelicula_seq'), 'Barbie', 'Comedia', '2023-07-21', (SELECT id FROM director WHERE nombre = 'Greta Gerwig'));
INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (nextval('pelicula_seq'), 'Saving Private Ryan', 'Bélico', '1998-07-24', (SELECT id FROM director WHERE nombre = 'Steven Spielberg'));
INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (nextval('pelicula_seq'), 'Once Upon a Time in Hollywood', 'Comedia/Drama', '2019-07-26', (SELECT id FROM director WHERE nombre = 'Quentin Tarantino'));
INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (nextval('pelicula_seq'), 'Taxi Driver', 'Drama/Crimen', '1976-02-08', (SELECT id FROM director WHERE nombre = 'Martin Scorsese'));
INSERT INTO pelicula (id, titulo, genero, fecha_estreno, director_id) VALUES (nextval('pelicula_seq'), 'Lost in Translation', 'Drama/Romance', '2003-10-03', (SELECT id FROM director WHERE nombre = 'Sofia Coppola'));

-- RELACIONES ACTORES_PELICULA (Busca IDs de película y actor en una línea)
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Inception'), (SELECT id FROM actor WHERE nombre = 'Leonardo DiCaprio'));
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Barbie'), (SELECT id FROM actor WHERE nombre = 'Margot Robbie'));
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Saving Private Ryan'), (SELECT id FROM actor WHERE nombre = 'Tom Hanks'));
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Once Upon a Time in Hollywood'), (SELECT id FROM actor WHERE nombre = 'Brad Pitt'));
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Once Upon a Time in Hollywood'), (SELECT id FROM actor WHERE nombre = 'Margot Robbie'));
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Taxi Driver'), (SELECT id FROM actor WHERE nombre = 'Robert De Niro'));
INSERT INTO actores_pelicula (pelicula_id, actor_id) VALUES ((SELECT id FROM pelicula WHERE titulo = 'Lost in Translation'), (SELECT id FROM actor WHERE nombre = 'Scarlett Johansson'));