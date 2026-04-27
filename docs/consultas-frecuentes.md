# Guía de Operaciones y Consultas del DBMS - Estante

Este documento detalla las operaciones fundamentales de manipulación de datos (DML) que el administrador puede ejecutar sobre el motor de base de datos de **Estante**.

## 1. Inserción de Registros (Crear)
**Descripción:** Operación para persistir un nuevo registro en la tabla de datos bibliográficos.
- **Entrada esperada:** Atributos del objeto (Título, Autor, ISBN único).
- **Resultado esperado:** Asignación de un ID autoincremental y confirmación de escritura en el archivo de base de datos.

## 2. Recuperación de Información por ID (Leer)
**Descripción:** Acceso directo a un registro específico mediante su clave primaria.
- **Entrada esperada:** Identificador numérico (PK).
- **Resultado esperado:** Retorno de todos los campos asociados al puntero del registro solicitado.

## 3. Actualización de Atributos (Actualizar)
**Descripción:** Modificación de valores en campos específicos de un registro existente, manteniendo la integridad.
- **Entrada esperada:** ID del registro y el nuevo valor para el campo (ej: `stock` o `email`).
- **Resultado esperado:** Actualización de los datos en disco y mensaje de "Registro actualizado con éxito".

## 4. Eliminación de Registros (Eliminar)
**Descripción:** Remoción de una entrada de la base de datos, verificando que no existan restricciones de integridad referencial.
- **Entrada esperada:** ID del registro a eliminar.
- **Resultado esperado:** Eliminación física del registro y actualización de los índices del sistema.

## 5. Búsqueda por Índices Secundarios (Buscar)
**Descripción:** Consulta filtrada utilizando campos indexados para optimizar el tiempo de respuesta.
- **Entrada esperada:** Cadena de búsqueda (ej: ISBN o DNI).
- **Resultado esperado:** Conjunto de resultados que coinciden exactamente con el criterio de búsqueda indexado.

## 6. Listado General de Tablas (Listar)
**Descripción:** Operación de escaneo completo para visualizar todos los registros almacenados en una entidad.
- **Entrada esperada:** Comando de selección de tabla (ej: `Listar Libros`).
- **Resultado esperado:** Volcado de datos de todos los registros activos en la tabla seleccionada.