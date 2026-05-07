# Diccionario de Datos - Sistema Estante

Este documento detalla la estructura de la base de datos interna para el proyecto Estante, definiendo tipos de datos, longitudes y restricciones de integridad. El sistema Estante es una aplicación de escritorio para administrar bases de datos PostgreSQL, permitiendo al usuario conectarse, visualizar tablas, ejecutar consultas SQL y gestionar registros.



## Tabla: CONEXIONES

Almacena los perfiles de conexión a bases de datos PostgreSQL configurados por el usuario. Permite al sistema recordar conexiones previas y que el usuario seleccione entre diferentes bases de datos (RF-001, RF-009).

| Nombre Campo        | Tipo de Dato | Descripción                                                  | Restricciones          |
| :------------------ | :----------- | :----------------------------------------------------------- | :--------------------- |
| `id_conexion`       | INTEGER      | Identificador único del perfil de conexión.                  | PK, AUTOINCREMENT      |
| `nombre`            | TEXT         | Nombre descriptivo asignado a la conexión.                   | NOT NULL               |
| `host`              | TEXT         | Dirección IP o nombre del servidor PostgreSQL.               | NOT NULL               |
| `puerto`            | INTEGER      | Puerto de red para la conexión (por defecto 5432).           | NOT NULL, DEFAULT 5432 |
| `base_datos`        | TEXT         | Nombre de la base de datos a la que se conecta.              | NOT NULL               |
| `usuario`           | TEXT         | Nombre de usuario para autenticarse en PostgreSQL.           | NOT NULL               |
| `contrasena`        | TEXT         | Contraseña cifrada del usuario de la base de datos.          | NOT NULL               |
| `fecha_registro`    | TEXT         | Fecha y hora en que se guardó el perfil de conexión.         | NOT NULL               |



## Tabla: HISTORIAL_CONSULTAS

Registra todas las consultas SQL ejecutadas por el usuario dentro de la aplicación. Permite el acceso rápido a consultas anteriores (RF-010, RNF-007).

| Nombre Campo          | Tipo de Dato | Descripción                                                             | Restricciones              |
| :-------------------- | :----------- | :---------------------------------------------------------------------- | :------------------------- |
| `id_consulta`         | INTEGER      | Identificador único del registro de consulta.                           | PK, AUTOINCREMENT          |
| `id_conexion`         | INTEGER      | Referencia a la conexión sobre la que se ejecutó la consulta.           | FK → CONEXIONES, NOT NULL  |
| `sentencia_sql`       | TEXT         | Texto completo de la consulta SQL ejecutada.                            | NOT NULL                   |
| `estado`              | TEXT         | Resultado de la ejecución (Exitosa, Error).                             | NOT NULL                   |
| `mensaje_error`       | TEXT         | Mensaje de error en lenguaje comprensible si el estado es Error (RF-008, RNF-004). | NULL              |
| `fecha_ejecucion`     | TEXT         | Fecha y hora exacta en que se ejecutó la consulta.                      | NOT NULL                   |
| `tiempo_respuesta_ms` | INTEGER      | Tiempo de respuesta de la consulta en milisegundos (RNF-001).           | NULL                       |



## Relaciones entre tablas


CONEXIONES ──< HISTORIAL_CONSULTAS


- Una **Conexión** puede tener N registros en **HISTORIAL_CONSULTAS**.
- Cada entrada del historial pertenece a una única conexión.



## Reglas de negocio

- No se puede guardar una conexión sin especificar host, puerto, base de datos y usuario.
- El sistema debe mostrar los resultados de las consultas en un tiempo máximo de 2 segundos (RNF-001).
- Los mensajes de error deben mostrarse en lenguaje comprensible para el usuario, no como errores técnicos crudos (RNF-004).
- El historial de consultas debe ser de fácil acceso desde la interfaz principal (RNF-007).
- El puerto por defecto para conexiones PostgreSQL es el 5432.