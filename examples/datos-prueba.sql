DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;

CREATE TABLE productos (
    id INTEGER PRIMARY KEY,
    nombre TEXT,
    precio REAL,
    stock INTEGER
);

CREATE TABLE clientes (
    id INTEGER PRIMARY KEY,
    nombre TEXT,
    email TEXT,
    fecha_registro TEXT
);

INSERT INTO productos (id, nombre, precio, stock) VALUES
(1, 'Teclado Mecanico', 45.50, 12),
(2, 'Mouse Inalambrico', 18.99, 25),
(3, 'Monitor 24 Pulgadas', 149.90, 7),
(4, 'Memoria USB 64GB', 9.75, 40),
(5, 'Laptop Ultraligera', 899.99, 3);

INSERT INTO clientes (id, nombre, email, fecha_registro) VALUES
(1, 'Carlos Mendoza', 'carlos@example.com', '2026-01-15'),
(2, 'Lucia Herrera', 'lucia@example.com', '2026-02-03'),
(3, 'Miguel Rojas', 'miguel@example.com', '2026-02-20'),
(4, 'Andrea Flores', 'andrea@example.com', '2026-03-11'),
(5, 'Sofia Vargas', 'sofia@example.com', '2026-04-05');