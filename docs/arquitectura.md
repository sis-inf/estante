# Arquitectura del Sistema - Proyecto Estante

## Descripción general

El sistema está diseñado bajo una arquitectura en capas que separa la presentación, la lógica de negocio y el acceso a datos. Esto permite mantener un código organizado, escalable y fácil de mantener.

## Tecnologías utilizadas

| Tecnología   | Versión | Descripción |
|-------------|--------|-------------|
| Java        | 17     | Lenguaje principal de la aplicación |
| PostgreSQL  | 15     | Sistema de gestión de base de datos |
| JDBC Driver | 42.x   | Conector entre Java y PostgreSQL |
| Maven       | 3.x    | Herramienta de gestión de dependencias |

## Diagrama de arquitectura

```
Usuario (CLI)
     ↓
Aplicación Java (Lógica de negocio)
     ↓
JDBC Driver
     ↓
PostgreSQL (Base de datos)
```

## Capas del sistema

- **Presentación (CLI):** Interfaz donde el usuario interactúa con el sistema mediante comandos.
- **Lógica de negocio (Java):** Procesa las operaciones y reglas del sistema.
- **Datos (PostgreSQL):** Almacena y gestiona la información.

## Flujo de datos

1. El usuario ingresa una consulta o comando en la interfaz CLI.
2. La aplicación Java procesa la solicitud.
3. Se envía la consulta a PostgreSQL mediante JDBC.
4. PostgreSQL ejecuta la consulta y devuelve los resultados.
5. La aplicación muestra el resultado en pantalla.

## Decisiones técnicas

Se eligió PostgreSQL por ser un sistema robusto, confiable y ampliamente utilizado.  
El uso de JDBC permite una integración directa con Java de forma estándar.

## Nota

Este documento describe la arquitectura general del sistema.
