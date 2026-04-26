# Propuesta: Método executeUpdate()

## ¿Qué se propone?
Se propone agregar un método llamado `executeUpdate()` para ejecutar sentencias SQL de tipo INSERT y UPDATE.

## ¿Para qué sirve?
Este método permitirá realizar operaciones de escritura en la base de datos y devolver la cantidad de filas afectadas. De esta forma se puede saber si una operación fue exitosa o no.

## Configuración propuesta
Se sugiere:

- Que el método retorne un valor entero (`int`) indicando la cantidad de filas modificadas
- Restringir el uso del método para sentencias SELECT
- Manejar posibles errores mediante `SQLException`
- Documentar el método dentro de la interfaz correspondiente
- 
