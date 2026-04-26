# Propuesta: Método executeSelect

## ¿Qué se propone?
Se propone la creación del método `executeSelect` en la clase ConnectionProvider para ejecutar consultas de tipo SELECT.

## ¿Para qué sirve?
Este método permitirá ejecutar consultas SELECT y obtener los resultados de la base de datos de manera estructurada, facilitando su uso dentro del sistema.

## Comportamiento propuesto
- Utilizar `Statement` y `ResultSet` para ejecutar la consulta
- Convertir el `ResultSet` en un objeto `QueryResult`
- Cerrar los recursos en un bloque `finally`
- Validar que la consulta sea de tipo SELECT
- Lanzar una excepción si la consulta no cumple con el tipo esperado
