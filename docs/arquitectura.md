# Arquitectura del Sistema - Proyecto Estante

## Descripción general

El sistema está diseñado bajo una arquitectura en capas que separa la presentación, la lógica de negocio y el acceso a datos. Esta organización permite mantener un código modular, escalable y fácil de mantener.

La aplicación funciona como un gestor de bases de datos con interfaz gráfica JavaFX que permite conectarse a múltiples motores de base de datos (SQLite, MySQL, PostgreSQL), ejecutar consultas SQL y exportar resultados en distintos formatos.

Actualmente el proyecto se encuentra en desarrollo activo, con múltiples módulos incorporados durante el sprint actual.

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

| Tecnología      | Versión  | Descripción |
|----------------|---------|-------------|
| Java           | 17/21   | Lenguaje principal de la aplicación |
| JavaFX         | 21.0.2  | Framework de interfaz gráfica |
| SQLite         | 3.46.0  | Motor de base de datos embebido |
| MySQL          | 8.x     | Motor de base de datos relacional |
| PostgreSQL     | 15+     | Motor de base de datos relacional |
| JDBC           | -       | Conector entre Java y bases de datos |
| Maven          | 3.x     | Herramienta de gestión de dependencias |
| Apache POI     | 5.2.5   | Generación de archivos Excel |
| RichTextFX     | 0.11.2  | Editor de código con resaltado de sintaxis |
| Jackson        | 2.16.1  | Serialización/deserialización JSON |
| JaCoCo         | 0.8.11  | Cobertura de tests |
| SpotBugs       | 4.8.6.6 | Análisis estático de bugs |

---

# Arquitectura general

El sistema sigue una arquitectura MVC (Model-View-Controller) con capas bien definidas.

## Diagrama general

```text
+---------------------------+
|      Usuario              |
|    (Interfaz JavaFX)      |
+-------------+-------------+
              |
              v
+---------------------------+
|  Capa de Presentación     |
|  Controllers JavaFX       |
|  Vistas FXML              |
+-------------+-------------+
              |
              v
+---------------------------+
|  Capa de Servicios        |
|  EjecutorQuery            |
|  EjecutorQueryAsync       |
|  ExploradorEsquemas       |
|  ExportadorCSV            |
|  ExportadorJSON           |
|  ExportadorExcel          |
|  ImportadorCSV            |
|  HistorialQuerys          |
|  ValidadorSQL             |
|  GeneradorSQL             |
|  GestorFavoritos          |
|  ConexionTester           |
+-------------+-------------+
              |
              v
+---------------------------+
|  Capa de Acceso a Datos   |
|  IConexionDAO             |
|  ConexionDAOSQLite        |
|  ConexionDAOMySQL         |
|  ConexionDAOPostgreSQL    |
|  IRepositorioConexiones   |
|  RepositorioConexionesJSON|
+-------------+-------------+
              |
              v
+---------------------------+
|  Bases de Datos           |
|  SQLite / MySQL /         |
|  PostgreSQL               |
+---------------------------+
```

---

# Capas del sistema

## 1. Capa de Presentación

Responsable de la interacción entre el usuario y el sistema mediante JavaFX.

### Controllers

- `App` — Punto de entrada, orquesta paneles y handlers
- `VentanaPrincipalController` — Controller de la ventana principal
- `PanelArbolConexionesController` — Árbol de conexiones con filtrado dinámico
- `PanelEditorSQLController` — Editor SQL con resaltado de sintaxis
- `PanelResultadoQueryController` — Muestra resultados de queries
- `PanelHistorialController` — Historial de queries ejecutadas
- `PanelInfoTablaController` — Información de columnas de tablas
- `PanelEstadisticasController` — Panel de estadísticas
- `BarraEstadoController` — Barra de estado inferior
- `DialogoNuevaConexionController` — Diálogo para crear conexiones
- `DialogoConfirmacionDML` — Confirmación de queries destructivas

---

## 2. Capa de Servicios

Contiene la lógica de negocio del sistema.

### Servicios existentes

- `EjecutorQuery` — Ejecuta queries SQL síncronamente
- `EjecutorQueryAsync` — Ejecuta queries en hilo de fondo con JavaFX Task
- `ExploradorEsquemas` — Lista esquemas, tablas y columnas vía JDBC Metadata
- `ExportadorCSV` — Exporta ResultadoQuery a archivo CSV
- `ExportadorJSON` — Exporta ResultadoQuery a archivo JSON
- `ExportadorExcel` — Exporta ResultadoQuery a archivo .xlsx con Apache POI
- `ImportadorCSV` — Importa datos desde CSV a una tabla existente
- `HistorialQuerys` — Registro FIFO de las últimas 50 queries ejecutadas
- `ValidadorSQL` — Valida y clasifica sentencias SQL
- `GeneradorSQL` — Genera sentencias SQL (CREATE TABLE, etc.)
- `GestorFavoritos` — Gestiona queries marcadas como favoritas
- `GeneradorCreateTable` — Genera DDL CREATE TABLE desde metadata

---

## 3. Capa de Acceso a Datos

Encargada de la comunicación con las bases de datos mediante JDBC.

### Interfaces

- `IConexionDAO` — Interfaz para abrir conexiones JDBC
- `IRepositorioConexiones` — Interfaz para persistir conexiones

### Implementaciones DAO

- `ConexionDAOSQLite` — Conexión a SQLite
- `ConexionDAOMySQL` — Conexión a MySQL
- `ConexionDAOPostgreSQL` — Conexión a PostgreSQL

### Repositorios

- `RepositorioConexionesJSON` — Persiste conexiones en archivo JSON local

---

## 4. Capa de Modelo

Define las entidades del dominio.

### Modelos

- `Conexion` — Datos de una conexión a base de datos
- `TipoMotor` — Enum: SQLITE, MYSQL, POSTGRESQL
- `ResultadoQuery` — Resultado de una query (lectura, escritura o error)
- `Esquema` — Representa un esquema de base de datos
- `Tabla` — Representa una tabla dentro de un esquema
- `Columna` — Representa una columna de una tabla
- `ColumnaInfo` — Record con info detallada de columna (nombre, tipo, nullable, default)
- `EntradaHistorial` — Record de una query ejecutada en el historial
- `ImportacionResultado` — Record con resultado de importación CSV
- `FavoritoQuery` — Query marcada como favorita

---

## Flujo de datos

1. El usuario interactúa con la interfaz JavaFX.
2. El controller recibe la acción y la delega al servicio correspondiente.
3. El servicio ejecuta la lógica de negocio.
4. Si requiere datos, el DAO abre una conexión JDBC y ejecuta la query.
5. La base de datos procesa la consulta y retorna resultados.
6. El servicio transforma los resultados en objetos del modelo.
7. El controller actualiza la vista con los datos recibidos.

---

# Gestión de dependencias

El proyecto utiliza `Maven` para:

- Administración de librerías
- Compilación del proyecto
- Gestión de dependencias
- Estandarización de builds
- Ejecución de tests y reportes de cobertura

---

## Decisiones técnicas

### Uso de Java 17/21

Se eligió Java por:

- Portabilidad multiplataforma
- Amplio ecosistema
- Records para modelos inmutables
- Pattern matching y switch expressions

### Uso de JavaFX

Se seleccionó JavaFX por:

- Integración nativa con Java
- FXML para separar vista de lógica
- Componentes ricos (TreeView, TableView, CodeArea)

### Soporte multi-motor

Se soportan SQLite, MySQL y PostgreSQL mediante el patrón DAO, permitiendo agregar nuevos motores sin modificar la lógica de negocio.

---

# Escalabilidad futura

La arquitectura actual permite incorporar:

- Soporte para más motores (Oracle, SQL Server)
- Sistema de autenticación de usuarios
- Optimización de queries con explain plan
- Soporte para múltiples conexiones simultáneas
- Reportes y estadísticas avanzadas
- Plugins de exportación adicionales

---

# Estado actual del sistema

El proyecto se encuentra en desarrollo activo. Durante el sprint actual se incorporaron los siguientes módulos:

- `ExportadorJSON` — Exportación a JSON
- `ExportadorExcel` — Exportación a Excel con Apache POI
- `ImportadorCSV` — Importación desde CSV
- `HistorialQuerys` — Historial de queries
- `ValidadorSQL` — Validación de sentencias SQL
- `GeneradorSQL` — Generación de SQL
- `GestorFavoritos` — Gestión de favoritos
- `ConexionDAOPostgreSQL` — Soporte para PostgreSQL
- `PanelHistorialController` — Panel de historial en UI
- `PanelInfoTablaController` — Panel de información de tabla
- Resaltado de sintaxis SQL en el editor
- Ejecución asíncrona de queries con spinner