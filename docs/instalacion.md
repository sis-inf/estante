# Guía de Instalación - DBMS Estante (Java Edition)

Este documento describe los pasos necesarios para configurar el entorno de desarrollo y ejecutar el Administrador de Base de Datos **Estante** de forma local.

## 1. Requisitos Previos
Para el despliegue del sistema, asegúrese de contar con:
- **Lenguaje de Programación:** [Java JDK 17](https://www.oracle.com/java/technologies/downloads/) o superior.
- **Gestor de Dependencias:** [Apache Maven](https://maven.apache.org/) 3.8+.
- **Sistema Operativo:** Windows 10/11, Linux o macOS.
- **Motor de BD Interno:** SQLite (Gestionado mediante JDBC).

> **⚠️ Nota sobre JavaFX:**
> A partir de Java 11, **JavaFX ya no está incluido en el JDK**. Sin embargo, **no es necesario descargarlo ni instalarlo por separado**. El archivo `pom.xml` del proyecto gestiona automáticamente las dependencias de JavaFX mediante las librerías `org.openjfx`. Al ejecutar `mvn javafx:run`, Maven descarga y utiliza las versiones correctas de JavaFX de forma transparente.

## 2. Clonación del Repositorio
Obtenga el código fuente del motor de base de datos mediante Git:
```bash

