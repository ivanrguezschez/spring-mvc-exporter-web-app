insert into rol(id_rol, rol) values (1, 'ADMINISTRADOR');
insert into rol(id_rol, rol) values (2, 'USUARIO');

insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (1, '00000001R', 'nombre-1','apellido1-1', 'apellido2-1', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (2, '00000009D', 'nombre-2','apellido1-2', 'apellido2-2', null, 2, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (3, '00000010X', 'nombre-3','apellido1-3', 'apellido2-3', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (4, '00000002W', 'nombre-4','apellido1-4', 'apellido2-4', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (5, '00000003A', 'nombre-5','apellido1-5', 'apellido2-5', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (6, '00000004G', 'nombre-6','apellido1-6', 'apellido2-6', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (7, '00000005M', 'nombre-7','apellido1-7', 'apellido2-7', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (8, '00000006Y', 'nombre-8','apellido1-8', 'apellido2-8', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (9, '00000007F', 'nombre-9','apellido1-9', 'apellido2-9', null, 1, CURRENT_DATE);
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (10, '00000008P', 'nombre-10','apellido1-10', 'apellido2-10', null, 1, CURRENT_DATE);

commit;