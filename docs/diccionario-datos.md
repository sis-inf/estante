# Diccionario de Datos Técnico - DBMS Estante

Este documento detalla el esquema lógico y la estructura de almacenamiento del sistema **Estante**, garantizando la integridad de los datos y la optimización de las consultas.

## 1. Tabla: `libros` (Catálogo de Entidades)
Almacena los registros maestros de los recursos bibliográficos.

| Campo | Tipo de Dato | Tamaño | Descripción | Restricciones |
|---|---|---|---|---|
| `id_libro` | INTEGER | 4 bytes | Identificador único de registro. | PK, AUTOINCREMENT |
| `titulo` | VARCHAR | 255 | Cadena de caracteres para el nombre del recurso. | NOT NULL |
| `isbn` | VARCHAR | 13 | Índice único para búsqueda rápida por código. | UNIQUE, NOT NULL |
| `ejemplares` | INT | - | Contador de instancias físicas disponibles. | DEFAULT 1 |

## 2. Tabla: `socios` (Usuarios del Sistema)
Define la estructura para el almacenamiento de metadatos de los usuarios finales.

| Campo | Tipo de Dato | Tamaño | Descripción | Restricciones |
|---|---|---|---|---|
| `id_socio` | INTEGER | 4 bytes | Clave primaria de la entidad usuario. | PK, AUTOINCREMENT |
| `nombre` | VARCHAR | 100 | Nombre completo registrado en el sistema. | NOT NULL |
| `dni` | VARCHAR | 20 | Identificador único de identidad (Índice). | UNIQUE, NOT NULL |
| `email` | VARCHAR | 150 | Punto de contacto para notificaciones del sistema. | NOT NULL |

## 3. Tabla: `prestamos` (Relación de Transacciones)
Tabla de unión que gestiona las transacciones y la integridad referencial entre libros y socios.

| Campo | Tipo de Dato | Descripción | Restricciones |
|---|---|---|---|
| `id_prestamo` | INTEGER | Clave primaria de la transacción. | PK, AUTOINCREMENT |
| `id_libro` | INTEGER | Puntero externo a la tabla `libros`. | FK (libros.id_libro) |
| `id_socio` | INTEGER | Puntero externo a la tabla `socios`. | FK (socios.id_socio) |
| `fecha_salida` | DATE | Marca de tiempo de inicio de la transacción. | NOT NULL |
| `fecha_devolucion` | DATE | Límite temporal de la persistencia del préstamo. | NOT NULL |
| `estado` | CHAR(1) | Estado lógico del registro (A: Activo, C: Cerrado). | DEFAULT 'A' |