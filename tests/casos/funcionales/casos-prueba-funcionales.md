# Casos de Prueba Funcionales

| ID | Requerimiento Asociado | Descripción del Caso | Precondiciones | Pasos | Resultado esperado |
|---|---|---|---|---|---|
| CP-001 | RF-001 |Validar conexión a una base de datos PostgreSQL mediante JDBC. | Base de datos PostgreSQL en ejecución y credenciales válidas. | 1. Configurar cadena de conexión JDBC.<br>2. Ingresar usuario y contraseña válidos.<br>3. Intentar conectar. | El sistema establece conexión exitosa y muestra mensaje de confirmación. |
| CP-002 | RF-002 | Validar visualización de tablas disponibles en la base de datos. | Conexión establecida a PostgreSQL. | 1. Acceder al módulo de exploración.<br>2. Solicitar listado de tablas.<br>3. Revisar salida. | El sistema muestra todas las tablas disponibles en formato tabular. |
| CP-003 | RF-003 | Validar ejecución de consultas SQL ingresadas por el usuario. | Conexión activa y al menos una tabla con datos. | 1. Ingresar consulta SQL válida (ej. SELECT * FROM tabla).<br>2. Ejecutar consulta.<br>3. Revisar resultados. | El sistema ejecuta la consulta y devuelve resultados correctos. |
| CP-004 | RF-004 | Validar visualización de resultados en formato tabular. | Consulta SQL ejecutada con éxito. | 1. Ejecutar consulta SELECT.<br>2. Solicitar visualización en formato tabular.<br>3. Revisar salida. | El sistema muestra los resultados en tabla con columnas y filas legibles. |
| CP-005 | RF-005 | Validar inserción de nuevos registros en una tabla. | Conexión activa y tabla existente con permisos de escritura. | 1. Ingresar comando SQL INSERT con datos válidos.<br>2. Ejecutar consulta.<br>3. Consultar la tabla. | El sistema inserta el registro y aparece en la tabla al consultarla. |
| CP-006 | RF-006 |||||
| CP-007 | RF-007 |||||
| CP-008 | RF-008 |||||
| CP-009 | RF-009 |||||
| CP-010 | RF-010 |||||