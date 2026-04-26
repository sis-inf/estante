# Propuesta: Manejo de timeout en conexión

## ¿Qué se propone?
Se propone agregar una configuración de tiempo límite (timeout) al momento de establecer una conexión a la base de datos.

## ¿Para qué sirve?
Esto permite evitar que la aplicación se quede esperando indefinidamente cuando no se puede establecer la conexión.  
Con un timeout definido, se puede controlar mejor el comportamiento del sistema en caso de fallos o lentitud en la red.

## Configuración propuesta
Se sugiere:

- Agregar un campo `timeoutSeconds` en la clase de configuración (DBConfig)
- Utilizar `Properties` con `loginTimeout`
- Lanzar una excepción si el tiempo es excedido
- Incluir una prueba comentada en el método `main()`
