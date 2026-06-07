# Guía de Instalación - DBMS Estante (Java Edition)
Este documento describe los pasos necesarios para configurar el entorno de desarrollo y ejecutar el Administrador de Base de Datos **Estante** de forma local.

## 1. Requisitos Previos
Para el despliegue del sistema, asegúrese de contar con:
- **Lenguaje de Programación:** [Java JDK 21](https://www.oracle.com/java/technologies/downloads/) o superior.
- **Gestor de Dependencias:** [Apache Maven](https://maven.apache.org/) 3.8+.
- **Sistema Operativo:** Windows 10/11, Linux o macOS.
- **Motor de BD Interno:** SQLite (Gestionado mediante JDBC).

> **⚠️ Nota sobre JavaFX:** A partir de Java 11, JavaFX **ya no está incluido** en el JDK estándar y debe gestionarse como dependencia externa. En este proyecto, **Maven gestiona JavaFX automáticamente** a través de las dependencias `org.openjfx` (`javafx-controls` y `javafx-fxml` v21.0.2) declaradas en el `pom.xml`. **No es necesario descargar ni instalar JavaFX por separado.**

## 2. Clonación del Repositorio
Obtenga el código fuente del motor de base de datos mediante Git:
```bash
git clone https://github.com/sis-inf/estante.git
cd estante
```

## 3. Compilación y Ejecución con Maven

Maven resuelve y descarga automáticamente todas las dependencias del proyecto, incluyendo `javafx-controls` y `javafx-fxml` (versión `21.0.2`), sin ninguna configuración adicional de su parte.

Para compilar el proyecto:
```bash
mvn clean compile
```

Para ejecutar la aplicación:
```bash
mvn javafx:run
```

El comando `mvn javafx:run` utiliza el plugin `org.openjfx:javafx-maven-plugin:0.0.8`, configurado en el `pom.xml` para lanzar la clase principal `edu.sisinf.estante.App`. No es necesario especificar `--module-path` ni `--add-modules` manualmente.
