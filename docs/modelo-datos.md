# Modelo de Datos - Estante

## 1. Visión General

Estante es un gestor de bases de datos desarrollado en Java con JavaFX que permite conectarse, explorar y administrar bases de datos MySQL mediante una interfaz gráfica.

El sistema permite gestionar conexiones, ejecutar consultas SQL y explorar estructuras de bases de datos de forma visual e intuitiva.

El presente documento describe el modelo entidad-relación (ER) principal del sistema.

---

# 2. Entidades y Atributos

## 2.1. Usuario

Representa a los usuarios que utilizan el sistema.

| Atributo | Tipo | Descripción |
|---|---|---|
| id_usuario | INT | Identificador único |
| nombre | VARCHAR | Nombre del usuario |
| email | VARCHAR | Correo electrónico |
| password | VARCHAR | Contraseña cifrada |
| created_at | TIMESTAMP | Fecha de creación |

### Claves

- PK: `id_usuario`

---

## 2.2. Conexion

Representa una conexión configurada hacia un servidor MySQL.

| Atributo | Tipo | Descripción |
|---|---|---|
| id_conexion | INT | Identificador único |
| id_usuario | INT | Usuario propietario |
| nombre | VARCHAR | Nombre descriptivo |
| host | VARCHAR | Dirección del servidor |
| puerto | INT | Puerto de conexión |
| database_name | VARCHAR | Base de datos seleccionada |
| username | VARCHAR | Usuario MySQL |
| password | VARCHAR | Contraseña cifrada |
| created_at | TIMESTAMP | Fecha de creación |

### Claves

- PK: `id_conexion`
- FK: `id_usuario -> Usuario.id_usuario`

---

## 2.3. BaseDeDatos

Representa una base de datos accesible desde una conexión.

| Atributo | Tipo | Descripción |
|---|---|---|
| id_database | INT | Identificador único |
| id_conexion | INT | Conexión asociada |
| nombre | VARCHAR | Nombre de la base de datos |

### Claves

- PK: `id_database`
- FK: `id_conexion -> Conexion.id_conexion`

---

## 2.4. Tabla

Representa tablas existentes dentro de una base de datos.

| Atributo | Tipo | Descripción |
|---|---|---|
| id_tabla | INT | Identificador único |
| id_database | INT | Base de datos asociada |
| nombre | VARCHAR | Nombre de la tabla |
| total_registros | INT | Cantidad de registros |

### Claves

- PK: `id_tabla`
- FK: `id_database -> BaseDeDatos.id_database`

---

## 2.5. ConsultaSQL

Representa consultas SQL ejecutadas por el usuario.

| Atributo | Tipo | Descripción |
|---|---|---|
| id_consulta | INT | Identificador único |
| id_usuario | INT | Usuario que ejecuta |
| id_conexion | INT | Conexión utilizada |
| consulta | TEXT | Sentencia SQL |
| estado | VARCHAR | Resultado de ejecución |
| fecha_ejecucion | TIMESTAMP | Fecha de ejecución |

### Claves

- PK: `id_consulta`
- FK: `id_usuario -> Usuario.id_usuario`
- FK: `id_conexion -> Conexion.id_conexion`

---

## 2.6. Historial

Registra eventos y operaciones realizadas dentro del sistema.

| Atributo | Tipo | Descripción |
|---|---|---|
| id_historial | INT | Identificador único |
| id_usuario | INT | Usuario asociado |
| accion | VARCHAR | Acción realizada |
| fecha_evento | TIMESTAMP | Fecha del evento |

### Claves

- PK: `id_historial`
- FK: `id_usuario -> Usuario.id_usuario`

---

# 3. Relaciones entre Entidades

## Usuario → Conexion

- Un usuario puede registrar múltiples conexiones.
- Cada conexión pertenece a un único usuario.

**Cardinalidad:** `1:N`

---

## Conexion → BaseDeDatos

- Una conexión puede contener múltiples bases de datos.
- Cada base de datos pertenece a una única conexión.

**Cardinalidad:** `1:N`

---

## BaseDeDatos → Tabla

- Una base de datos puede contener múltiples tablas.
- Cada tabla pertenece a una única base de datos.

**Cardinalidad:** `1:N`

---

## Usuario → ConsultaSQL

- Un usuario puede ejecutar múltiples consultas SQL.
- Cada consulta pertenece a un único usuario.

**Cardinalidad:** `1:N`

---

## Conexion → ConsultaSQL

- Una conexión puede utilizarse en múltiples consultas.
- Cada consulta utiliza una única conexión.

**Cardinalidad:** `1:N`

---

## Usuario → Historial

- Un usuario puede generar múltiples eventos.
- Cada evento pertenece a un único usuario.

**Cardinalidad:** `1:N`

---

# 4. Diagrama ER (Mermaid)

```mermaid
erDiagram

    USUARIO {
        INT id_usuario PK
        VARCHAR nombre
        VARCHAR email
        VARCHAR password
        TIMESTAMP created_at
    }

    CONEXION {
        INT id_conexion PK
        INT id_usuario FK
        VARCHAR nombre
        VARCHAR host
        INT puerto
        VARCHAR database_name
        VARCHAR username
        VARCHAR password
        TIMESTAMP created_at
    }

    BASEDEDATOS {
        INT id_database PK
        INT id_conexion FK
        VARCHAR nombre
    }

    TABLA {
        INT id_tabla PK
        INT id_database FK
        VARCHAR nombre
        INT total_registros
    }

    CONSULTASQL {
        INT id_consulta PK
        INT id_usuario FK
        INT id_conexion FK
        TEXT consulta
        VARCHAR estado
        TIMESTAMP fecha_ejecucion
    }

    HISTORIAL {
        INT id_historial PK
        INT id_usuario FK
        VARCHAR accion
        TIMESTAMP fecha_evento
    }

    USUARIO ||--o{ CONEXION : posee
    CONEXION ||--o{ BASEDEDATOS : contiene
    BASEDEDATOS ||--o{ TABLA : incluye
    USUARIO ||--o{ CONSULTASQL : ejecuta
    CONEXION ||--o{ CONSULTASQL : utiliza
    USUARIO ||--o{ HISTORIAL : genera