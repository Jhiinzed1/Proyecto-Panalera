-- Active: 1751925373600@@127.0.0.1@3306

-- 1. Listar todos los Articulos en venta.
SELECT * FROM articulos;

-- 2. mostrar todos los articulos en stock.
SELECT * FROM articulos WHERE stock = true;
--3. Mostrar todos los artiuclos disponible junto a sus proveedores
SELECT a.*, p.nombre AS proveedor_nombre FROM articulos a
JOIN proveedores p ON a.proveedor_id = p.id
WHERE a.stock = true;

--4. Listar todos los articulos junto a su respectivo proveedor
SELECT a.*, p.nombre AS proveedor_nombre
FROM articulos a
JOIN proveedores p ON a.proveedor_id = p.id;

-- 5. listado de clientes

SELECT * FROM clientes;

-- 6. Listar cada articulo junto con su calidad precio y id de proveedor

SELECT id, tipo, calidad, precio, proveedor_id FROM articulos;

-- 7. Listado de proveedores
SELECT * FROM proveedores;

-- 8. Total de ventas por cliente
SELECT c.nombre, c.apellido, SUM(v.total) AS total_ventas FROM ventas v
JOIN clientes c ON v.cliente_id = c.id
GROUP BY c.id;

-- 9. articulos con su cantidad de stock
SELECT tipo, stock FROM articulos;

-- 10. proveedores con el numero de articulos que tienen
SELECT p.nombre, COUNT(a.id) AS cantidad_articulos
FROM proveedores p
LEFT JOIN articulos a ON p.id = a.proveedor_id
GROUP BY p.id;

-- 11. articulos vendidos en un rango de fecha
SELECT a.tipo, SUM(v.cantidad) AS total_vendidos
FROM ventas v
JOIN articulos a ON v.articulo_id = a.id
WHERE v.fecha BETWEEN 'YYYY-MM-DD' AND 'YYYY-MM-DD'
GROUP BY a.id;