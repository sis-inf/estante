# Diccionario de Datos - DBMS Estante

## Tabla: LIBROS
| Campo | Tipo | Descripción | Restricciones |
|---|---|---|---|
| `id_libro` | INT | Identificador único. | PK, AUTOINCREMENT |
| `isbn` | VARCHAR(13) | Código único. | UNIQUE, NOT NULL |
| `titulo` | VARCHAR(200) | Título del libro. | NOT NULL |

## Tabla: PRESTAMOS
| Campo | Tipo | Descripción | Restricciones |
|---|---|---|---|
| `id_prestamo` | INT | ID Transacción. | PK |
| `id_libro` | INT | Puntero a LIBROS. | FK |