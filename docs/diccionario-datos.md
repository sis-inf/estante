# Diccionario de Datos - Sistema Estante

| Información | Valor |
|---|---|
| Proyecto | Estante |
| Versión | 1.0 |
| Motor de Base de Datos | MySQL |
| Tecnología | Java + JavaFX |
| Fecha | Mayo 2026 |

---

# Descripción General

Este documento describe la estructura de la base de datos interna del sistema **Estante**, un gestor visual de bases de datos MySQL desarrollado en Java y JavaFX.

El objetivo de este diccionario es definir de manera clara las tablas, atributos, relaciones, restricciones y reglas de negocio utilizadas por el sistema para la administración de conexiones, ejecución de consultas SQL y almacenamiento del historial de operaciones.

---

# Convenciones Utilizadas

| Convención | Descripción |
|---|---|
| PK | Primary Key |
| FK | Foreign Key |
| NOT NULL | Campo obligatorio |
| UNIQUE | Valor único |
| AUTOINCREMENT | Valor incremental automático |
| DEFAULT | Valor por defecto |
| INDEX | Índice para optimización de búsquedas |

---

# Tabla: CONEXIONES

Almacena los perfiles de conexión a bases de datos MySQL configurados por el usuario. Permite guardar conexiones frecuentes y reutilizarlas desde la interfaz gráfica del sistema.

| Nombre Campo | Tipo de Dato | Descripción | Restricciones |
|---|---|---|---|
| `id_conexion` | INTEGER | Identificador único del perfil de conexión | PK, AUTOINCREMENT |
| `nombre` | VARCHAR(100) | Nombre descriptivo de la conexión | NOT NULL |
| `host` | VARCHAR(150) | Dirección IP o nombre del servidor MySQL | NOT NULL |
| `puerto` | INTEGER | Puerto de conexión del servidor MySQL | NOT NULL, DEFAULT 3306 |
| `base_datos` | VARCHAR(100) | Nombre de la base de datos | NOT NULL |
| `usuario` | VARCHAR(100) | Usuario de autenticación MySQL | NOT NULL, INDEX |
| `contrasena` | VARCHAR(255) | Contraseña cifrada de acceso | NOT NULL |
| `fecha_registro` | DATETIME | Fecha y hora de registro de la conexión | NOT NULL |

---

# Tabla: HISTORIAL_CONSULTAS

Registra todas las consultas SQL ejecutadas dentro del sistema. Permite al usuario consultar operaciones anteriores y revisar errores de ejecución.

| Nombre Campo | Tipo de Dato | Descripción | Restricciones |
|---|---|---|---|
| `id_consulta` | INTEGER | Identificador único del historial | PK, AUTOINCREMENT |
| `id_conexion` | INTEGER | Referencia a la conexión utilizada | FK → CONEXIONES(id_conexion), NOT NULL |
| `sentencia_sql` | TEXT | Consulta SQL ejecutada por el usuario | NOT NULL |
| `estado` | VARCHAR(20) | Estado de ejecución de la consulta | NOT NULL, CHECK (estado IN ('EXITOSA', 'ERROR')) |
| `mensaje_error` | TEXT | Mensaje de error generado durante la ejecución | NULL |
| `fecha_ejecucion` | DATETIME | Fecha y hora exacta de ejecución | NOT NULL, INDEX |
| `tiempo_respuesta_ms` | INTEGER | Tiempo de respuesta en milisegundos | NULL |

---

# Relaciones entre Tablas

```text
CONEXIONES (1) ───────────< (N) HISTORIAL_CONSULTAS
```

## Descripción de Relaciones

- Una conexión puede registrar múltiples consultas ejecutadas.
- Cada registro del historial pertenece a una única conexión.
- La relación entre ambas tablas se establece mediante el campo `id_conexion`.

---

# Reglas de Negocio

- No se puede registrar una conexión sin especificar host, puerto, base de datos y usuario.
- El puerto por defecto para conexiones MySQL es el 3306.
- Las contraseñas almacenadas deben mantenerse cifradas.
- El sistema debe mostrar mensajes de error comprensibles para el usuario final.
- El historial de consultas debe ser accesible desde la interfaz principal.
- Las consultas SQL ejecutadas deben almacenarse junto con su tiempo de respuesta.
- El sistema debe permitir reutilizar conexiones previamente guardadas.
- El tiempo máximo recomendado de respuesta para consultas simples es de 2 segundos.

---

# Consideraciones Técnicas

- Se recomienda utilizar consultas parametrizadas para prevenir ataques de SQL Injection.
- Los campos `usuario` y `fecha_ejecucion` deben indexarse para optimizar búsquedas.
- El historial de consultas puede requerir políticas de limpieza periódica dependiendo del volumen de registros.
- Las contraseñas no deben almacenarse en texto plano.
- El sistema debe validar previamente la conectividad antes de guardar un perfil de conexión.

---

# Escalabilidad Futura

El modelo de datos podrá ampliarse en futuras versiones para incorporar:

- Gestión de usuarios autenticados.
- Consultas SQL favoritas.
- Exportación de resultados.
- Historial de conexiones recientes.
- Configuración avanzada de conexiones.
- Registro de actividad del usuario.
