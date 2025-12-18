-- Active: 1751925373600@@127.0.0.1@3306
CREATE TABLE ventas(
    id INT PRIMARY KEY,
    cliente_id INT,
    articulo_id INT,
    cantidad INTEGER,
    fecha TEXT,
    total REAL,
FOREIGN KEY (cliente_id) REFERENCES clientes(id),
FOREIGN KEY (articulo_id) REFERENCES articulos(id)
);


CREATE TABLE proveedores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(25) NOT NULL CHECK (LENGTH(nombre) > 3),
    apellido VARCHAR(25) NOT NULL CHECK (LENGTH(apellido) > 3),
    empresa VARCHAR(25) NOT NULL,
    direccion VARCHAR(255)
);



CREATE TABLE clientes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(25) NOT NULL CHECK (LENGTH(nombre) > 3),
    apellido VARCHAR(25) NOT NULL CHECK (LENGTH(apellido) > 3),
    direccion VARCHAR(255)
);


CREATE TABLE articulos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo VARCHAR(25) NOT NULL CHECK (LENGTH(tipo) > 3),
    marca VARCHAR(25),
    peso DECIMAL(10, 2),
    calidad VARCHAR(25),
    precio DECIMAL(10, 2),
    proveedor_id INT,
    stock BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id)
);




