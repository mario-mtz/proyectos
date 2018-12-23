insert into manager (nombre) values('MIGUEL LÓPEZ');
insert into manager (nombre) values('ANTONIO MARTÍNEZ');
insert into manager (nombre) values('CÉSAR PAZ');
insert into manager (nombre) values('ERICK SOSA');
insert into manager (nombre) values('VALERIA AGUILAR');

insert into desarrollador (nombre) values('ALMA JÉSUS');
insert into desarrollador (nombre) values('EDUARDO AGUILAR');
insert into desarrollador (nombre) values('MARIO MARTÍNEZ');
insert into desarrollador (nombre) values('NEIL TORRES');
insert into desarrollador (nombre) values('HÉCTOR GARCÍA');
insert into desarrollador (nombre) values('RICARDO HERNÁNDEZ');
insert into desarrollador (nombre) values('MAURICIO PÉREZ');
insert into desarrollador (nombre) values('ESPERANZA ESPEJEL');
insert into desarrollador (nombre) values('ERICK ARIAS');
insert into desarrollador (nombre) values('CARLOS LÓPEZ');

insert into proyecto (clave, descripcion, manager_id) values('p1','Proyecto 1', 3);
insert into proyecto (clave, descripcion, manager_id) values('p2','Proyecto 2', 4);

insert into proyecto_desarrollador values(1,2);
insert into proyecto_desarrollador values(1,3);

insert into proyecto_desarrollador values(2,6);
insert into proyecto_desarrollador values(2,7);
insert into proyecto_desarrollador values(2,8);