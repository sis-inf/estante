# Propuesta: MySqlConnectionProvider

## ¿Qué se propone?

Se propone implementar una clase llamada MySqlConnectionProvider que permita gestionar conexiones a una base de datos MySQL utilizando JDBC.

## ¿Para qué sirve?

Esta clase permitirá centralizar la lógica de conexión a la base de datos, facilitando su reutilización dentro del sistema.

## Comportamiento propuesto

- Implementar la interfaz ConnectionProvider
- Utilizar DriverManager.getConnection() para establecer la conexión
- Manejar excepciones SQLException con mensajes claros
- Permitir configurar la URL, usuario y contraseña

## Ejemplo de uso propuesto

Se podría crear una instancia de la clase con los datos de conexión y utilizar un método para obtener la conexión a la base de datos...
