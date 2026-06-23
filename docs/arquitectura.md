# Arquitectura del Sistema - Proyecto Estante

## Descripción general

El sistema está diseñado bajo una arquitectura en capas que separa la presentación, la lógica de negocio y el acceso a datos. Esta organización permite mantener un código modular, escalable y fácil de mantener.

La aplicación funciona como un gestor de bases de datos orientado a la
administración y manipulación de información mediante una interfaz de línea
de comandos (CLI).

Actualmente el proyecto se encuentra en desarrollo, por lo que algunos módulos
podrán modificarse o ampliarse conforme evolucione el sistema.

---

# Objetivos de la arquitectura

La arquitectura del sistema busca:

- Separar responsabilidades entre componentes
- Facilitar el mantenimiento del código
- Permitir escalabilidad futura
- Reducir el acoplamiento entre módulos
- Facilitar pruebas y depuración
- Mantener una estructura organizada del proyecto

---

## Tecnologías utilizadas

| Tecnología   | Versión | Descripción |
|-------------|--------|-------------|
| Java        | 17     | Lenguaje principal de la aplicación |
| PostgreSQL  | 15     | Sistema de gestión de base de datos |
| JDBC Driver | 42.x   | Conector entre Java y PostgreSQL |
| Maven       | 3.x    | Herramienta de gestión de dependencias |

---

# Arquitectura general

El sistema sigue una arquitectura cliente-servidor simplificada donde
la aplicación Java actúa como intermediaria entre el usuario y la base
de datos PostgreSQL.

## Diagrama general

```text
+----------------------+
|      Usuario         |
|        (CLI)         |
+----------+-----------+
           |
           v
+----------------------+
|  Capa de Presentación|
|     Interfaz CLI     |
+----------+-----------+
           |
           v
+----------------------+
| Lógica de Negocio    |
| Validaciones         |
| Procesamiento        |
| Operaciones CRUD     |
+----------+-----------+
           |
           v
+----------------------+
| Acceso a Datos       |
| JDBC / Persistencia  |
+----------+-----------+
           |
           v
+----------------------+
| PostgreSQL           |
| Base de Datos        |
+----------------------+
```

---

# Capas del sistema

## 1. Capa de Presentación

Responsable de la interacción entre el usuario y el sistema.

### Responsabilidades

- Mostrar menús y opciones
- Recibir comandos y consultas
- Mostrar resultados
- Gestionar mensajes de error
- Validar entradas básicas

### Componentes esperados

- CLI principal
- Menús interactivos
- Control de navegación

---

## 2. Capa de Lógica de Negocio

Contiene las reglas principales del sistema y coordina las operaciones.

### Responsabilidades

- Procesar solicitudes del usuario
- Validar reglas del sistema
- Gestionar operaciones CRUD
- Coordinar acceso a datos
- Manejar flujo interno de operaciones

### Posibles módulos

- Gestión de tablas
- Gestión de registros
- Validación de datos
- Procesador de consultas

---

## 3. Capa de Acceso a Datos

Encargada de la comunicación con PostgreSQL mediante JDBC.

### Responsabilidades

- Abrir conexiones con la base de datos
- Ejecutar consultas SQL
- Recuperar resultados
- Manejar transacciones
- Gestionar errores de persistencia

### Componentes esperados

- DAO (Data Access Object)
- Gestor de conexiones
- Ejecutores SQL

---

## 4. Capa de Datos

Representa el sistema PostgreSQL donde se almacena la información.

### Responsabilidades

- Persistencia de datos
- Integridad de la información
- Ejecución de consultas
- Gestión de tablas y registros

---

## Flujo de datos

El flujo general del sistema funciona de la siguiente manera:

1. El usuario ingresa una consulta o comando en la interfaz CLI.
2. La capa de presentación recibe y valida la entrada.
3. La lógica de negocio procesa la operación solicitada.
4. La capa de acceso a datos genera y ejecuta consultas SQL mediante JDBC.
5. PostgreSQL procesa la consulta.
6. Los resultados retornan a la aplicación.
7. La aplicación muestra la respuesta al usuario.

---

# Gestión de dependencias

El proyecto utiliza `Maven` para:

- Administración de librerías
- Compilación del proyecto
- Gestión de dependencias
- Estandarización de builds

Esto facilita la portabilidad y configuración del entorno de desarrollo.

---

## Decisiones técnicas

## Uso de Java

Se eligió Java por:

- Portabilidad multiplataforma
- Amplio ecosistema
- Integración estable con PostgreSQL
- Facilidad para manejar aplicaciones orientadas a objetos

## Uso de PostgreSQL

Se seleccionó PostgreSQL debido a:

- Robustez y estabilidad
- Soporte para consultas complejas
- Integridad y consistencia de datos
- Amplio uso en entornos profesionales

## Uso de JDBC

JDBC permite:

- Comunicación estándar con PostgreSQL
- Independencia del motor de base de datos
- Manejo directo de consultas SQL

---

# Escalabilidad futura

La arquitectura actual permite incorporar nuevas funcionalidades como:

- Interfaces gráficas
- Sistema de autenticación
- Optimización de consultas
- Soporte para múltiples conexiones
- Nuevos módulos de administración
- Reportes y estadísticas

---

# Estado actual del sistema

El proyecto se encuentra en una etapa inicial de desarrollo.
La arquitectura presentada representa la base estructural sobre la cual
evolucionará el sistema Estante.

Algunos componentes descritos pueden implementarse progresivamente en futuras versiones.