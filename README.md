# Estante

Gestor de base de datos con interfaz gráfica desarrollado en Java y JavaFX.

## ¿Qué es?

Estante es una aplicación que permite conectarse, explorar y administrar bases de datos MySQL mediante una interfaz gráfica intuitiva desarrollada con Java y JavaFX.

## ¿Para quién es?

Estante está dirigido a:

- Estudiantes de ingeniería
- Administradores de bases de datos (DBAs)
- Desarrolladores que trabajan con MySQL

## ¿Qué problema resuelve?

Estante ayuda a resolver:

- Tareas comunes de exploración y gestión de bases de datos
- Simplificar la administración de bases de datos para usuarios con menos experiencia técnica
- Visualizar datos de forma clara e intuitiva

## Instalación

### Requisitos del sistema

- Java 17 o superior
- JavaFX SDK compatible con tu versión de Java
- Un servidor MySQL en ejecución (local o remoto)
# Pasos

# 1. Clona el repositorio:
git clone https://github.com/tu-usuario/estante.git
cd estante

# 2. Compila el proyecto:
javac --module-path /ruta/a/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/**/*.java

# 3. Ejecuta la aplicación:    
java --module-path /ruta/a/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out Main  

## Uso rápido
- Abre Estante y completa los datos de conexión (host, puerto, usuario y contraseña)
- Selecciona la base de datos que deseas explorar
- Navega por los esquemas y tablas desde el panel lateral
- Ejecuta consultas SQL desde el editor integrado y visualiza los resultados en la tabla de resultados

## Documentación
Ver la carpeta [docs/](docs/)

## Contribuir
Ver [CONTRIBUTING.md](CONTRIBUTING.md)

## Licencia
MIT — ver [LICENSE](LICENSE)
## ✨ Cambio final
README actualizado correctamente.
