# Casos de Uso - Proyecto Estante

Este documento describe los actores y las operaciones principales que se pueden realizar en el sistema de gestión bibliográfica.

## 1. Actores del Sistema
- **Bibliotecario:** Personal encargado de administrar el inventario de libros, registrar nuevos socios y gestionar los préstamos y devoluciones.
- **Socio:** Usuario de la biblioteca que puede consultar la disponibilidad de libros y solicitar préstamos.

## 2. Casos de Uso Principales

### CU-01: Registrar Nuevo Libro
- **Actor:** Bibliotecario
- **Descripción:** Permite ingresar una nueva obra al catálogo de la biblioteca.
- **Flujo Principal:**
  1. El bibliotecario ingresa los datos (Título, Autor, ISBN).
  2. El sistema valida que el ISBN no esté duplicado.
  3. Se crea el registro en la tabla `libros`.

### CU-02: Consultar Disponibilidad
- **Actor:** Socio / Bibliotecario
- **Descripción:** Buscar si un libro específico tiene ejemplares libres para préstamo.
- **Flujo Principal:**
  1. Se ingresa el título o autor en el buscador.
  2. El sistema filtra los registros coincidentes.
  3. Se muestra el estado del libro (Disponible/Prestado).

### CU-03: Registrar Préstamo
- **Actor:** Bibliotecario
- **Descripción:** Asignar un libro a un socio por un tiempo determinado.
- **Flujo Principal:**
  1. Se selecciona el libro y el socio.
  2. El sistema verifica que el socio no tenga multas pendientes.
  3. Se genera un registro en la tabla `prestamos` con la fecha de devolución.

### CU-04: Actualizar Datos de Socio
- **Actor:** Bibliotecario
- **Descripción:** Modificar la información de contacto o identidad de un socio existente.
- **Flujo Principal:**
  1. Se busca al socio por su DNI.
  2. Se editan los campos necesarios (email, teléfono).
  3. El sistema actualiza el registro en la base de datos.

### CU-05: Registrar Devolución de Libro
- **Actor:** Bibliotecario
- **Descripción:** Finalizar un préstamo activo cuando el socio entrega el libro.
- **Flujo Principal:**
  1. Se identifica el préstamo mediante el ID o el libro.
  2. Se marca el estado del préstamo como "Devuelto".
  3. El libro vuelve a estar disponible para nuevos préstamos.

### CU-06: Eliminar Registro de Libro
- **Actor:** Bibliotecario
- **Descripción:** Dar de baja un libro del catálogo por pérdida o deterioro.
- **Flujo Principal:**
  1. Se selecciona el libro a eliminar.
  2. El sistema verifica que no existan préstamos activos para ese ejemplar.
  3. Se elimina el registro de la tabla `libros`.