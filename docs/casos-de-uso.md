# Casos de Uso del Administrador de BD - Proyecto Estante

Este documento describe las interacciones entre los actores y el motor de base de datos para la gestión técnica de la información.

## 1. Actores del Sistema
- **DBA (Database Administrator):** Actor con privilegios de nivel superior encargado de la definición de esquemas, mantenimiento de la integridad y gestión masiva de registros.
- **Gestor de Consultas (Query Engine):** Proceso o usuario que realiza operaciones de recuperación y manipulación de datos (DML) para el consumo de información.

## 2. Casos de Uso Principales

### CU-01: Inserción de Registros (Create)
- **Actor:** DBA
- **Descripción:** Persistir una nueva instancia de datos en la entidad (tabla) seleccionada.
- **Flujo Principal:**
  1. El DBA proporciona los atributos del registro.
  2. El sistema valida las restricciones de integridad (Unique/Not Null).
  3. El motor de BD escribe el registro en el archivo físico de almacenamiento.

### CU-02: Recuperación Selectiva de Información (Read)
- **Actor:** Gestor de Consultas
- **Descripción:** Consultar registros específicos basándose en criterios de filtrado o índices.
- **Flujo Principal:**
  1. Se ingresa el parámetro de búsqueda (ej: Clave Primaria).
  2. El motor realiza un escaneo de la tabla o índice.
  3. Se retorna el conjunto de resultados que cumplen la condición.

### CU-03: Actualización Atómica de Datos (Update)
- **Actor:** DBA
- **Descripción:** Modificar valores de campos en registros existentes sin comprometer la consistencia.
- **Flujo Principal:**
  1. Se identifica el registro mediante su ID.
  2. Se ingresan los nuevos valores para los atributos seleccionados.
  3. El sistema actualiza el registro y confirma la persistencia (Commit).

### CU-04: Eliminación con Integridad Referencial (Delete)
- **Actor:** DBA
- **Descripción:** Remover físicamente un registro de la base de datos.
- **Flujo Principal:**
  1. Se selecciona el registro para su remoción.
  2. El motor verifica que no existan dependencias (Foreign Keys) activas.
  3. Se elimina el registro y se actualizan los índices de la tabla.

### CU-05: Gestión de Transacciones de Préstamo
- **Actor:** DBA
- **Descripción:** Registrar el vínculo transaccional entre una entidad de recurso y una entidad de usuario.
- **Flujo Principal:**
  1. Se vinculan los identificadores de ambas entidades.
  2. El sistema genera un registro de auditoría con marcas de tiempo.
  3. Se actualiza el estado de disponibilidad en la tabla maestra.

### CU-06: Auditoría de Integridad del Sistema
- **Actor:** DBA / Gestor de Consultas
- **Descripción:** Revisar el estado de los registros para asegurar que los datos no estén corruptos.
- **Flujo Principal:**
  1. El sistema realiza un listado general de las tablas.
  2. Se comparan los contadores de registros con los índices.
  3. Se reporta el estado de salud de la base de datos.