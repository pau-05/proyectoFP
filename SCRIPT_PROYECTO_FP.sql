CREATE TABLE USUARIO(
    id_ususario NUMBER(8) PRIMARY KEY,
    apellido1 VARCHAR2(30) NOT NULL,
    apellido2 VARCHAR2(30) NOT NULL,
    email VARCHAR2(20) UNIQUE,
    nombre VARCHAR2(30) NOT NULL,
    contrasenia VARCHAR2(15) NOT NULL
);

CREATE TABLE CATEGORÍA(
    nombre VARCHAR(30) PRIMARY KEY,
    descripcion VARCHAR(80) NOT NULL
);

CREATE TABLE ORGANIZADOR(
    NIF VARCHAR2(9) PRIMARY KEY,
    nombre VARCHAR2(20) NOT NULL,
    dirección VARCHAR2(40)
);

CREATE TABLE ORGANIZADOR_TELF(
    NIF VARCHAR2(9),
    email VARCHAR2(20),
    PRIMARY KEY (email, NIF),
    FOREIGN KEY (NIF) REFERENCES ORGANIZADOR(NIF)
);

CREATE TABLE ORGANIZADOR_EMAIL (
    NIF VARCHAR2(9),
    tlfno NUMBER(9),
    PRIMARY KEY (tlfno, NIF),
    FOREIGN KEY (NIF) REFERENCES ORGANIZADOR(NIF)
);

CREATE TABLE EVENTO(
    id_evento NUMBER(6) PRIMARY KEY,
    direccion VARCHAR2(40),
    duracion INTERVAL DAY TO SECOND NOT NULL,
    fecha_h TIMESTAMP NOT NULL,
    nombre VARCHAR2(30) NOT NULL,
    descripción VARCHAR2(150) NOT NULL,
    precio NUMBER(2)
);

CREATE TABLE USUARIO_EVENTO_INSCRIPCION(
    id_usuario NUMBER(8),
    id_evento NUMBER(6),
    fecha_h TIMESTAMP NOT NULL,
    PRIMARY KEY (id_usuario, id_evento),
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_ususario),
    FOREIGN KEY (id_evento) REFERENCES EVENTO(id_evento)
);

CREATE TABLE USUARIO_EVENTO_ASISTENCIA(
    id_usuario NUMBER(8),
    id_evento NUMBER(6),
    asistencia NUMBER(1), --0 para true y 1 para false
    PRIMARY KEY (id_usuario, id_evento),
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_ususario),
    FOREIGN KEY (id_evento) REFERENCES EVENTO(id_evento),
    CONSTRAINT ck_booleano CHECK (asistencia IN (0, 1))
    --la constraint revisará que solo se ponga 0 o 1 en asistencia
);