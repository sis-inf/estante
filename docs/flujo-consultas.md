# Flujo de ejecución de consultas

## 1. Visión general

Este documento describe el flujo interno que sigue el sistema desde que se abre una conexión hasta la ejecución de una consulta SELECT.

---

## 2. Diagrama de flujo (ASCII)


[Usuario]
│
▼
[UI / Entrada de consulta]
│
▼
[open() - Conexión a BD]
│
▼
[Validación de query]
│
▼
[executeSelect()]
│
▼
[ResultadoQuery]
│
▼
[UI muestra resultados]


---

## 3. Flujo paso a paso

### Paso 1: Entrada del usuario
El usuario ingresa una consulta SQL desde la interfaz.

### Paso 2: Apertura de conexión (open)
Se establece conexión con la base de datos.

### Paso 3: Validación de la consulta
Se identifica el tipo de query.

### Paso 4: Ejecución (executeSelect)
Se ejecuta la consulta SELECT.

### Paso 5: Resultado
Se construye un ResultadoQuery.

### Paso 6: Visualización
Se muestran los resultados al usuario.
<!-- actualización -->