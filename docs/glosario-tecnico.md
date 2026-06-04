# Glosario Técnico de BD y JDBC

| Término | Abreviatura | Definición |
|---|---|---|
| Commit | — | Confirma de forma permanente los cambios realizados en una transacción. Después de un commit, los datos quedan guardados en la base de datos. |
| Connection | — | Representa la conexión activa a la base de datos en JDBC. Se utiliza para enviar consultas y manejar transacciones. |
| CRUD | — | Conjunto de operaciones básicas: Create, Read, Update y Delete. Son las acciones fundamentales que se pueden realizar sobre registros de una base de datos. |
| DAO | Data Access Object | Patrón de diseño que organiza el acceso a la base de datos mediante objetos. Permite separar la lógica de negocio de la persistencia. |
| DatabaseMetaData | — | Interfaz de JDBC que permite obtener información sobre la base de datos, como tablas, columnas y tipos de datos. Ayuda a comprender la estructura del sistema. |
| DCL | Data Control Language | Instrucciones SQL para controlar los permisos de los usuarios sobre la base de datos, como GRANT y REVOKE. |
| DDL | Data Definition Language | Conjunto de instrucciones SQL para definir o modificar la estructura de la base de datos, como CREATE, ALTER y DROP. |
| DML | Data Manipulation Language | Instrucciones SQL para manipular datos dentro de las tablas, como INSERT, UPDATE, DELETE y SELECT. |
| Foreign Key | — | Restricción que asegura que un valor en una tabla coincide con un valor existente en otra. Mantiene la integridad de los datos relacionados. |
| Index | — | Estructura que acelera la búsqueda de datos dentro de una tabla. Funciona como un índice de libro para encontrar registros rápidamente. |
| JDBC | Java Database Connectivity | API de Java que permite conectarse y ejecutar operaciones en bases de datos relacionales mediante drivers específicos. |
| Motor de base de datos | — | Software que gestiona el almacenamiento, recuperación y manipulación de los datos. Controla la integridad y consistencia de la información. |
| ORM | Object-Relational Mapping | Patrón que mapea objetos del lenguaje de programación a tablas de la base de datos. Automatiza operaciones CRUD y reduce código repetitivo en comparación con DAO. |
| PreparedStatement | — | Consulta SQL precompilada en JDBC que permite usar parámetros. Previene inyección de SQL y mejora eficiencia de ejecución. |
| ResultSet | — | Contiene los resultados de una consulta ejecutada en JDBC. Permite recorrer filas y columnas para obtener los datos. |
| ResultSetMetaData | — | Proporciona información sobre las columnas de un ResultSet, como nombre, tipo de dato y tamaño. |
| Rollback | — | Revierte los cambios de la transacción actual. Útil para deshacer operaciones cuando ocurre un error. |
| Statement | — | Objeto JDBC para ejecutar consultas SQL simples sin parámetros. |
| Transacción | — | Conjunto de operaciones SQL que se ejecutan como una unidad. Todas deben completarse o ninguna se aplica. |