CREATE DATABASE crud_sixtema;

CREATE TABLE departamento (
    id INT PRIMARY KEY AUTO_INCREMENT,  
    nombre VARCHAR(100) NOT NULL        
);

CREATE TABLE empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,  
    nombre VARCHAR(100) NOT NULL,      
    departamento INT,                  
    sueldo DECIMAL(10, 2) NOT NULL,     
    FOREIGN KEY (departamento) REFERENCES departamento(id)  
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE
);

INSERT INTO usuarios (nickname, password, email, is_admin)
VALUES ('joseLedo', 'jose1234', 'jose_ledo@hotmail.es', TRUE),
       ('admin', 'admin', 'admin@example.com', TRUE);

INSERT INTO departamento (id, nombre)
VALUES
(1, 'Recursos Humanos'),
(2, 'Finanzas'),
(3, 'Marketing'),
(4, 'Ventas'),
(5, 'Logística'),
(6, 'Investigación y Desarrollo'),
(7, 'Compras'),
(8, 'Atención al Cliente'),
(9, 'Producción'),
(10, 'Calidad'),
(11, 'Legal'),
(12, 'Sistemas'),
(13, 'Administración'),
(14, 'Innovación'),
(15, 'Comunicación');

INSERT INTO empleado (id, nombre, departamento, sueldo)
VALUES
(1, 'Juan Pérez', 1, 45000),
(2, 'María Gómez', 2, 52000),
(3, 'Carlos Sánchez', 3, 48000),
(4, 'Ana López', 4, 53000),
(5, 'Miguel Torres', 5, 47000),
(6, 'Laura Fernández', 6, 60000),
(7, 'Jorge Ramírez', 7, 49000),
(8, 'Claudia Castro', 8, 46000),
(9, 'David Martínez', 9, 54000),
(10, 'Lucía Moreno', 10, 51000),
(11, 'Pedro Díaz', 11, 55000),
(12, 'Carmen Reyes', 12, 58000),
(13, 'Raúl Gutiérrez', 13, 47000),
(14, 'Marta Campos', 14, 62000),
(15, 'Sofía Jiménez', 15, 49000),
(16, 'Alberto Rivas', 1, 45000),
(17, 'Beatriz Ortiz', 2, 52000),
(18, 'Fernando Ruiz', 3, 48000),
(19, 'Patricia Silva', 4, 53000),
(20, 'Diego Vargas', 5, 47000),
(21, 'Rocío Vega', 6, 60000),
(22, 'Ignacio Romero', 7, 49000),
(23, 'Elena Guerrero', 8, 46000),
(24, 'Antonio Herrera', 9, 54000),
(25, 'Natalia Ponce', 10, 51000),
(26, 'Rubén Navarro', 11, 55000),
(27, 'Julia Serrano', 12, 58000),
(28, 'Sergio Morales', 13, 47000),
(29, 'Paula Peña', 14, 62000),
(30, 'Esteban Álvarez', 15, 49000);
