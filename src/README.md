# 📚 Estructura de la carpeta `src`

```bash
src/
├── ui/
├── controller/
├── model/
├── service/
├── db/
└── util/
```

Este directorio contiene la organización del código fuente siguiendo una arquitectura por capas, separando la interfaz gráfica, y el acceso a datos.

---

## 📂 Descripción de Directorios

### 🔹 `ui/`

Contiene la interfaz gráfica de usuario desarrollada con JavaFX.

Ejemplos:

* Vistas (`.fxml`)
* Componentes visuales

---

### 🔹 `controller/`

Gestiona la interacción entre la interfaz y la lógica del sistema.

Responsabilidades:

* Manejo de eventos
* Comunicación con la capa de servicios

---

### 🔹 `model/`

Define las entidades del dominio del sistema.

Ejemplo:

* Atributos y métodos de acceso
* Clases de datos (`POJOs`)

> [!NOTE]
> Un **POJO** (Plain Old Java Object) es un objeto Java simple que no depende de frameworks, no extiende clases especiales ni implementa interfaces específicas, siguiendo solo las convenciones básicas del lenguaje.

---

### 🔹 `service/`

Implementa la lógica de negocio de la aplicación.

Responsabilidades:

* Procesamiento de datos
* Aplicación de reglas de negocio
* Coordinación con la capa de acceso a datos

---

### 🔹 `db/`

Encapsula el acceso a la base de datos.

Responsabilidades:

* Conexión (`JDBC`)
* Consultas SQL
* Operaciones CRUD

---

### 🔹 `util/`

Contiene funciones auxiliares reutilizables.

Responsabilidades:

* Validaciones
* Formateo
* Utilidades generales

---

## 🧩 Principios de Diseño

* **Separación de responsabilidades**: Cada capa cumple una función específica dentro del sistema.
* **Modularidad**: El código se organiza en componentes independientes y reutilizables.
* **Escalabilidad**: La estructura permite agregar nuevas funcionalidades sin afectar las existentes.
* **Mantenibilidad**: Facilita la comprensión y modificación del código a lo largo del tiempo.