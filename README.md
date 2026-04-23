ESTANTE
Estante es un gestor de bases de datos en Java que permite conectar, explorar y administrar información de manera sencilla desde una sola aplicación.
¿Qué es Estante?
Estante es un gestor de bases de datos desarrollado en Java que permite crear, administrar y consultar información de forma clara y organizada. Está diseñado para facilitar la interacción con diferentes motores de bases de datos sin necesidad de herramientas externas complejas.
¿Para quién es?
  •	Estudiantes que están aprendiendo bases de datos
  •	Desarrolladores que necesitan una herramienta simple
  •	Usuarios que desean gestionar datos sin complicaciones
 ¿Qué problema resuelve?
  •	Evita usar múltiples herramientas para gestionar bases de datos
  •	Simplifica la ejecución de consultas SQL
  •	Facilita la visualización y manipulación de datos 
Bases de datos soportadas
Estante utiliza JDBC (Java Database Connectivity), por lo que soporta:
  •	MySQL
  •	PostgreSQL
  •	SQLite
Además, puede extenderse a cualquier sistema compatible con JDBC.
 Instalación
1. Clonar el repositorio
git clone https://github.com/tu-usuario/estante.git
cd estante
2. Requisitos previos
  •	Java JDK 8 o superior
  •	Maven o Gradle
Verificar:
java -version
3. Compilar el proyecto
Con Maven:
mvn clean install
Con Gradle:
gradle build
Uso rápido
1.	Configurar la base de datos en config.properties:
        db.url=jdbc:mysql://localhost:3306/estante
       	db.user=tu_usuario
        db.password=tu_contraseña
2.	Ejecutar la aplicación:
mvn exec:java
o:
java -jar target/estante.jar
3.	Interactuar con la aplicación:
  •	Ver tablas
  •	Ejecutar consultas SQL
  •	Gestionar registros
 Interfaz de usuario
Estante ofrece una interfaz intuitiva que incluye:
  •	 Explorador de bases de datos y tablas
  •	 Visualización de datos en tablas
  •	 Editor de consultas SQL
  •	 Operaciones CRUD (crear, editar, eliminar)
  •	Búsqueda y filtrado de información
 Funcionalidades principales
  •	Conexión a múltiples bases de datos
  •	Ejecución de consultas SQL
  •	Visualización estructurada de datos
  •	Gestión de tablas y registros
## Documentación
Ver la carpeta [docs/](docs/)

## Contribuir
Ver [CONTRIBUTING.md](CONTRIBUTING.md)

## Licencia
MIT — ver [LICENSE](LICENSE)
