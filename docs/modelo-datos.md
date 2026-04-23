# Modelo de Datos - Estante

Este documento describe la estructura lógica y el modelo entidad-relación del gestor de base de datos.

## 1. Entidades Principales

### Entidad: Tabla
* **Atributos:**
    * `id_tabla` (PK): Identificador único de la tabla.
    * `nombre`: Nombre de la tabla.
    * `fecha_creacion`: Fecha en la que se creó la tabla.
    * `ubicacion_archivo`: Ruta física del archivo de datos.

### Entidad: Registro (Fila)
* **Atributos:**
    * `id_registro` (PK): Identificador único del registro.
    * `id_tabla` (FK): Referencia a la tabla a la que pertenece.
    * `contenido`: Datos almacenados.

## 2. Relaciones
* **Tabla a Registro (1:N):** Una Tabla puede contener muchos Registros, pero un registro pertenece exclusivamente a una tabla.

## 3. Claves
* **Claves Primarias (PK):** id_tabla e id_registro.
* **Claves Foráneas (FK):** id_tabla en la entidad Registro.
