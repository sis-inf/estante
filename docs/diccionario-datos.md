# Diccionario de Datos - Proyecto Estante

Este documento describe la estructura de la base de datos relacional utilizada para la gestión de la biblioteca.

## 1. Tabla: `libros`
Almacena la información bibliográfica de las obras disponibles.

| Columna | Tipo de Dato | Descripción | Restricciones |
|---|---|---|---|
| `id_libro` | INTEGER | Identificador único del libro. | PK, AUTOINCREMENT |
| `titulo` | TEXT | Título de la obra. | NOT NULL |
| `autor` | TEXT | Nombre del autor. | NOT NULL |
| `isbn` | TEXT | Código ISBN único de 13 dígitos. | UNIQUE, NOT NULL |
| `ejemplares` | INTEGER | Cantidad total de copias físicas. | DEFAULT 1 |

## 2. Tabla: `socios`
Contiene los datos de los usuarios registrados en el sistema.

| Columna | Tipo de Dato | Descripción | Restricciones |
|---|---|---|---|
| `id_socio` | INTEGER | Identificador único del socio. | PK, AUTOINCREMENT |
| `nombre` | TEXT | Nombre completo del usuario. | NOT NULL |
| `dni` | TEXT | Documento nacional de identidad. | UNIQUE, NOT NULL |
| `email` | TEXT | Correo electrónico de contacto. | NOT NULL |

## 3. Tabla: `prestamos`
Registra la relación entre libros y socios, gestionando las fechas de entrega.

| Columna | Tipo de Dato | Descripción | Restricciones |
|---|---|---|---|
| `id_prestamo` | INTEGER | Identificador de la transacción. | PK, AUTOINCREMENT |
| `id_libro` | INTEGER | Referencia al libro prestado. | FK (libros.id_libro) |
| `id_socio` | INTEGER | Referencia al socio solicitante. | FK (socios.id_socio) |
| `fecha_salida` | DATE | Fecha en que se entregó el libro. | NOT NULL |
| `fecha_devolucion` | DATE | Fecha máxima de retorno. | NOT NULL |
| `estado` | TEXT | Estado del préstamo (Activo/Devuelto). | DEFAULT 'Activo' |