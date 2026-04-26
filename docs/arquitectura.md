# Arquitectura del Sistema - Proyecto Estante

## Visión general
El sistema Estante está diseñado bajo un patrón de arquitectura de **N-Capas** (Presentación, Negocio y Datos). Esta estructura permite separar las responsabilidades, facilitando el mantenimiento y permitiendo que el sistema de gestión de biblioteca sea escalable y robusto frente a cambios futuros.

## Componentes principales
1. **Módulo de Interfaz (GUI):** Gestiona la interacción con el bibliotecario, capturando datos de búsqueda y registro de libros.
2. **Controlador de Préstamos:** Contiene la lógica para validar si un usuario puede llevarse un libro (verificación de multas y stock).
3. **Gestor de Inventario:** Administra las altas, bajas y modificaciones del catálogo bibliográfico.
4. **Capa de Persistencia:** Maneja el almacenamiento de datos en archivos planos o bases de datos relacionales.

## Diagrama de arquitectura

*El sistema separa la interfaz de usuario de la lógica de procesamiento y el almacenamiento final.*

## Tecnologías utilizadas

| Componente | Tecnología | Versión | Justificación |
|---|---|---|---|
| Lenguaje Core | Python | 3.10+ | Facilidad para manejo de estructuras de datos y legibilidad. |
| Gestión de Datos | SQLite / JSON | N/A | Portabilidad y ligereza para un sistema local de biblioteca. |
| Control de Versiones | Git / GitHub | 2.x | Estándar de la industria para el trabajo colaborativo en el FNI. |
| Documentación | Markdown | N/A | Integración nativa con GitHub y facilidad de lectura. |

## Decisiones de diseño

### Decisión 1: Trunk-Based Development (Uso de rama DEV)
**Contexto:** Necesidad de mantener una rama estable para producción mientras se integran nuevas funciones.
**Decisión:** Se adopta el flujo de trabajo basado en la rama `dev`. Ningún cambio llega a `main` sin ser probado previamente.
**Consecuencias:** Mayor estabilidad en el código base y un historial de cambios más limpio y organizado.

## Flujo de datos
1. **Entrada:** El bibliotecario ingresa el ISBN del libro y el CI del usuario.
2. **Procesamiento:** El sistema consulta la base de datos para verificar la disponibilidad del ejemplar y el estado del socio.
3. **Validación:** Se aplican las reglas de negocio (ej. el usuario no debe tener más de 3 libros).
4. **Almacenamiento:** Se registra la transacción con la fecha de salida y la fecha límite de devolución.
5. **Salida:** El sistema confirma el préstamo y actualiza el estado del libro a "Prestado".