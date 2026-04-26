# 📘 Guía de Estandares de Codigo en Java
## 🎯 Objetivo

Esta guía define convenciones para escribir código Java limpio, consistente y mantenible, siguiendo buenas prácticas.

## 🧾 1. Nombres de clases

- Usar `PascalCase` (UpperCamelCase).
- Los nombres deben ser sustantivos.
- Evitar abreviaciones innecesarias.
- Cada archivo debe contener una sola clase pública.

### ✅ Ejemplos:

```java
class UserService {
}

class PaymentProcessor {
}
```

## ⚙️ 2. Nombres de métodos y variables

- Usar `camelCase`.
- Los métodos deben representar acciones (verbos).
- Las variables deben ser descriptivas.
- Constantes en `UPPER_SNAKE_CASE`.

### ✅ Ejemplos:

```java
int userAge = 25;
double totalPrice = 100.5;

public int calculateTotal(int price, int quantity) {
  return price * quantity;
}

public boolean isValidInput(String input) {
  return input != null && !input.isEmpty();
}
```

## 📁 3. Nombres de archivos y paquetes
- Archivos deben tener el mismo nombre que la clase (`UserService.java`).
- Usar `lowercase` para paquetes.
- Seguir estructura tipo dominio invertido.

### ✅ Ejemplos:

```java
package com.example.project.service;

public class UserService {
}
```

* 👉 Google recomienda una estructura clara de archivos con:

```bash
package
import
clase principal
```

## 📐 4. Indentación y formato

- Usar 2 espacios por nivel.
- No usar tabs.
- Máximo 100 caracteres por línea.
- Una instrucción por línea.

### ✅ Ejemplo:

```java
if (isValid) {
  processData();
}
```

* 👉 Cada nuevo bloque incrementa la indentación en +2 espacios

## 💬 5. Comentarios y documentación

- Usar Javadoc `(/** */)` para clases y métodos públicos.
- Explicar el propósito, no lo obvio.
- Incluir `@param`, `@return` cuando sea necesario.

### ✅ Ejemplo:

```java
/**
 * Calcula el precio total de una compra
 *
 * @param price precio unitario
 * @param quantity cantidad de productos
 * @return precio total calculado
 */
public int calculateTotal(int price, int quantity) {
  return price * quantity;
}
```

* 👉 Javadoc debe comenzar con un resumen claro del método

## 🧩 6. Ejemplo de clase bien documentada

```java
/**
 * Servicio encargado de manejar operaciones de usuarios.
 */
public class UserService {

  private String name;

  /**
   * Constructor de la clase UserService.
   *
   * @param name nombre del usuario
   */
  public UserService(String name) {
    this.name = name;
  }

  /**
   * Obtiene el nombre del usuario.
   *
   * @return nombre del usuario
   */
  public String getName() {
    return name;
  }

  /**
   * Verifica si el nombre es válido.
   *
   * @return true si el nombre es válido, false en caso contrario
   */
  public boolean isValidName() {
    return name != null && !name.isEmpty();
  }
}
```

## 🚫 7. Buenas prácticas clave

- No usar imports con `*` (wildcards)
- Usar `@Override` cuando corresponda
- No ignorar excepciones
- Mantener métodos pequeños y claros
- Una clase por archivo
- Código organizado y legible

## 🔥 Conclusión rápida

- `PascalCase` → clases
- `camelCase` → métodos y variables
- `UPPER_SNAKE_CASE` → constantes
- 2 espacios → indentación
- 100 caracteres → límite por línea
- Javadoc → documentación
- Consistencia > preferencias personales
- Código → Español

> [!NOTE]
> - Los ejemplos presentados son ilustrativos.
> - No es necesario que el código sea exactamente igual,
> - pero sí respetar las buenas prácticas descritas.