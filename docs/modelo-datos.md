# Modelo de Datos - Proyecto Estante

## 1. Visión General
El modelo de datos del proyecto Estante está diseñado para gestionar de manera eficiente el inventario de una biblioteca, permitiendo el control de ejemplares, la identificación de usuarios y el seguimiento riguroso de los préstamos realizados.

## 2. Entidades y Atributos

### 2.1. Libros
Representa el catálogo físico de la biblioteca.
- `id_libro` (PK): Identificador único.
- `isbn`: Código internacional normalizado.
- `titulo`: Nombre de la obra.
- `autor`: Escritor o creador.
- `estado`: Estado actual (Disponible, Prestado, En reparación).

### 2.2. Usuarios
Personas registradas en el sistema para solicitar préstamos.
- `id_usuario` (PK): Identificador único.
- `nombre_completo`: Nombre del socio.
- `ci`: Carnet de Identidad.
- `telefono`: Contacto directo.

### 2.3. Préstamos
Relación que vincula a un usuario con un libro por un tiempo determinado.
- `id_prestamo` (PK): Identificador del registro.
- `id_libro` (FK): Referencia al libro prestado.
- `id_usuario` (FK): Referencia al usuario que solicita.
- `fecha_salida`: Fecha en que se retira el libro.
- `fecha_devolucion`: Fecha límite para la entrega.

## 3. Relaciones (Modelo Lógico)
- Un **Usuario** puede realizar **N Préstamos**.
- Un **Libro** puede estar registrado en **N Préstamos** a lo largo del tiempo (pero solo uno activo a la vez).
- La entidad **Préstamo** sirve como puente entre Usuarios y Libros.

## 4. Reglas de Negocio
- No se puede eliminar un usuario que tenga préstamos pendientes.
- El campo `isbn` debe ser único para evitar duplicados en el catálogo.
- La `fecha_devolucion` no puede ser menor a la `fecha_salida`.