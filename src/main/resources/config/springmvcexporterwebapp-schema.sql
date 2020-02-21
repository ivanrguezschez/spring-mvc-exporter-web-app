DROP TABLE rol IF EXISTS CASCADE;
create table rol (
	id_rol integer primary key,
	rol varchar(50) not null
);
DROP TABLE usuario IF EXISTS CASCADE;
create table usuario (
	id_usuario integer generated by default as identity(start with 3) primary key,
	nif varchar(9) not null,
	nombre varchar(50) not null,
	apellido1 varchar(50) not null,
	apellido2 varchar(50),
	email varchar(100),
	id_rol integer not null,
	fecha_alta date not null,
	constraint fk_usuario_rol foreign key(id_rol) references rol(id_rol)
);
create unique index ix_usuario_nif on usuario (nif);