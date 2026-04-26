# Consultas Frecuentes - Proyecto Estante

Este documento sirve como guía de referencia para las operaciones más comunes dentro del gestor de biblioteca.

## 1. Crear un nuevo registro (Crear)
**Descripción:** Registrar un nuevo libro en el catálogo.
- **Entrada esperada:** Título: "Cien años de soledad", Autor: "Gabriel García Márquez", ISBN: "9780307474728".
- **Resultado esperado:** Mensaje de confirmación: "Libro registrado exitosamente con ID 105".

## 2. Listar todos los libros (Listar)
**Descripción:** Mostrar la lista completa de libros almacenados.
- **Entrada esperada:** Comando de "Mostrar Todo".
- **Resultado esperado:** Una tabla con ID, Título, Autor y Estado de todos los libros en la base de datos.

## 3. Buscar un libro por título (Buscar)
**Descripción:** Localizar un libro específico mediante una palabra clave.
- **Entrada esperada:** Palabra clave: "Quijote".
- **Resultado esperado:** Detalle del libro "Don Quijote de la Mancha" (Autor, ISBN, disponibilidad).

## 4. Consultar historial de un socio (Leer)
**Descripción:** Ver qué libros ha solicitado un socio específico.
- **Entrada esperada:** DNI del socio: "12345678".
- **Resultado esperado:** Lista de préstamos activos e históricos asociados a ese DNI.

## 5. Actualizar información de un socio (Actualizar)
**Descripción:** Cambiar el correo electrónico o teléfono de un usuario.
- **Entrada esperada:** ID de socio: 42, Nuevo Email: "rodrigo.nuevo@email.com".
- **Resultado esperado:** Notificación de "Datos actualizados correctamente" y persistencia en la tabla `socios`.

## 6. Dar de baja un ejemplar (Eliminar)
**Descripción:** Eliminar un libro del sistema por pérdida o retiro.
- **Entrada esperada:** ID del libro: 88.
- **Resultado esperado:** Eliminación física o lógica del registro, confirmando que ya no aparece en las búsquedas.