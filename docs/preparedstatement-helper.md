# Propuesta: PreparedStatement Helper

## ¿Qué se propone?
Se propone crear un helper para ejecutar consultas SQL utilizando PreparedStatement de manera más segura y reutilizable.

## ¿Para qué sirve?
Permite ejecutar consultas parametrizadas evitando inyección SQL y facilitando el manejo de parámetros dinámicos.

## Ejemplo de uso propuesto
executeQuery("SELECT * FROM usuarios WHERE id = ?", 1);

## Beneficios
- Evita SQL Injection
- Código más limpio
- Reutilización de lógica
- Manejo simplificado de parámetros

## Nota
Esta es solo una propuesta. No se implementa código fuente aún.
