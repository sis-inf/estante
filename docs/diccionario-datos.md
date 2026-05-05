# Diccionario de Datos - Sistema Estante

Este documento detalla la estructura de la base de datos para el proyecto Estante, definiendo tipos de datos, longitudes y restricciones de integridad.

---

## Tabla: CATEGORIAS

Clasificación lógica de los libros disponibles en el sistema.

| Nombre Campo   | Tipo de Dato  | Descripción                               | Restricciones        |
| :------------- | :------------ | :---------------------------------------- | :------------------- |
| `id_categoria` | INT           | Identificador único de la categoría.      | PK, AUTO_INCREMENT   |
| `nombre`       | VARCHAR(100)  | Nombre de la categoría (Ingeniería, etc). | NOT NULL             |
| `descripcion`  | VARCHAR(255)  | Descripción breve de la categoría.        | NULL                 |

---

## Tabla: LIBROS

Almacena la información bibliográfica de los textos disponibles en el sistema.

| Nombre Campo   | Tipo de Dato  | Descripción                                | Restricciones        |
| :------------- | :------------ | :----------------------------------------- | :------------------- |
| `id_libro`     | INT           | Identificador único del libro.             | PK, AUTO_INCREMENT   |
| `isbn`         | VARCHAR(13)   | Código internacional normalizado.          | UNIQUE, NOT NULL     |
| `titulo`       | VARCHAR(255)  | Título completo de la obra.                | NOT NULL             |
| `autor`        | VARCHAR(150)  | Nombre del autor principal.                | NOT NULL             |
| `editorial`    | VARCHAR(150)  | Editorial que publicó el libro.            | NULL                 |
| `anio_pub`     | YEAR          | Año de publicación de la obra.             | NULL                 |
| `id_categoria` | INT           | Referencia a la categoría del libro.       | FK → CATEGORIAS      |
| `stock`        | INT           | Cantidad de ejemplares disponibles.        | NOT NULL, DEFAULT 1  |

---

## Tabla: USUARIOS

Registra a las personas que pueden realizar préstamos en el sistema.

| Nombre Campo   | Tipo de Dato  | Descripción                                | Restricciones        |
| :------------- | :------------ | :----------------------------------------- | :------------------- |
| `id_usuario`   | INT           | Identificador único del usuario.           | PK, AUTO_INCREMENT   |
| `nombre`       | VARCHAR(150)  | Nombre completo del usuario.               | NOT NULL             |
| `correo`       | VARCHAR(100)  | Correo electrónico del usuario.            | UNIQUE, NOT NULL     |
| `telefono`     | VARCHAR(20)   | Número de contacto del usuario.            | NULL                 |
| `tipo`         | VARCHAR(20)   | Tipo de usuario (Alumno, Docente, etc).    | NOT NULL             |

---

## Tabla: PRESTAMOS

Registra las transacciones de libros entre la biblioteca y los usuarios.

| Nombre Campo       | Tipo de Dato  | Descripción                              | Restricciones           |
| :----------------- | :------------ | :--------------------------------------- | :---------------------- |
| `id_prestamo`      | INT           | Identificador único de la transacción.   | PK, AUTO_INCREMENT      |
| `id_usuario`       | INT           | Usuario que realiza el préstamo.         | FK → USUARIOS, NOT NULL |
| `id_libro`         | INT           | Libro objeto del préstamo.               | FK → LIBROS, NOT NULL   |
| `fecha_salida`     | DATE          | Fecha en que se entrega el libro.        | NOT NULL                |
| `fecha_devolucion` | DATE          | Fecha pactada de retorno.                | NOT NULL                |
| `estado`           | VARCHAR(20)   | Estado del préstamo (Activo, Devuelto).  | DEFAULT 'Activo'        |

---

## Tabla: MULTAS

Registra las sanciones económicas generadas por devoluciones tardías.

| Nombre Campo   | Tipo de Dato   | Descripción                                 | Restricciones            |
| :------------- | :------------- | :------------------------------------------ | :----------------------- |
| `id_multa`     | INT            | Identificador único de la multa.            | PK, AUTO_INCREMENT       |
| `id_prestamo`  | INT            | Préstamo que originó la multa.              | FK → PRESTAMOS, NOT NULL |
| `monto`        | DECIMAL(8,2)   | Monto económico de la multa.                | NOT NULL                 |
| `pagado`       | TINYINT(1)     | Indica si la multa fue pagada (0/1).        | DEFAULT 0                |
| `fecha_multa`  | DATE           | Fecha en que se generó la multa.            | NOT NULL                 |

---

## Relaciones entre tablas

CATEGORIAS ──< LIBROS
USUARIOS   ──< PRESTAMOS
LIBROS     ──< PRESTAMOS
PRESTAMOS  ──< MULTAS