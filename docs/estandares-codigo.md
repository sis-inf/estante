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

## 🔌 2. Nombres de interfaces

- Las interfaces deben usar `PascalCase`.
- En este proyecto se mantiene la convención de utilizar el prefijo `I` para identificar interfaces.
- El prefijo `I` permite diferenciar claramente una interfaz de su implementación.
- Las implementaciones pueden usar un nombre descriptivo relacionado con la funcionalidad.

#### ✅ Ejemplos:

```java
public interface IConexionDAO {
    void conectar();
}

public class ConexionDAOImpl implements IConexionDAO {
    public void conectar() {
        // implementación
    }
}

public interface IRepositorioConexiones {
    void guardar();
}
```

* 👉 Esta convención ya está establecida en el proyecto y debe mantenerse para nuevas interfaces.

## ⚙️ 3. Nombres de métodos y variables

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

## 📁 4. Nombres de archivos y paquetes
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

## 🧪 8. Ejemplos de código correcto e incorrecto

Esta sección complementa las reglas anteriores con ejemplos lado a lado (✅ correcto / ❌ incorrecto) para los casos donde el error es más común en la práctica.

### 8.1 Cierre de recursos JDBC

Usar siempre `try-with-resources`, que cierra los recursos automáticamente aunque ocurra una excepción.

#### ✅ Correcto

```java
public List<User> findAll() throws SQLException {
  String sql = "SELECT id, name FROM users";

  try (Connection conn = dataSource.getConnection();
       PreparedStatement stmt = conn.prepareStatement(sql);
       ResultSet rs = stmt.executeQuery()) {

    List<User> users = new ArrayList<>();
    while (rs.next()) {
      users.add(new User(rs.getInt("id"), rs.getString("name")));
    }
    return users;
  }
}
```

#### ❌ Incorrecto

```java
public List<User> findAll() throws SQLException {
  Connection conn = null;
  PreparedStatement stmt = null;
  ResultSet rs = null;

  try {
    conn = dataSource.getConnection();
    stmt = conn.prepareStatement("SELECT id, name FROM users");
    rs = stmt.executeQuery();

    List<User> users = new ArrayList<>();
    while (rs.next()) {
      users.add(new User(rs.getInt("id"), rs.getString("name")));
    }
    return users;
  } finally {
    // Si una de estas líneas lanza excepción, las siguientes no se ejecutan
    // y el recurso correspondiente queda abierto.
    rs.close();
    stmt.close();
    conn.close();
  }
}
```

### 8.2 Nombres de clases de acceso a datos

Las clases responsables de acceder a datos deben llevar el sufijo `DAO`, que es el estándar reconocido en la industria. Evitar nombres ambiguos o inventados.

#### ✅ Correcto

```java
public class UserDAO {
  public User findById(int id) {
    // ...
    return null;
  }
}
```

#### ❌ Incorrecto

```java
public class UserManagador {
  public User buscarPorId(int id) {
    // ...
    return null;
  }
}
```

### 8.3 PreparedStatement vs concatenación de Strings

Usar siempre `PreparedStatement` con parámetros. Concatenar valores directamente en el SQL expone la aplicación a **SQL injection**.

#### ✅ Correcto

```java
public User findByEmail(String email) throws SQLException {
  String sql = "SELECT id, name FROM users WHERE email = ?";

  try (Connection conn = dataSource.getConnection();
       PreparedStatement stmt = conn.prepareStatement(sql)) {

    stmt.setString(1, email);

    try (ResultSet rs = stmt.executeQuery()) {
      if (rs.next()) {
        return new User(rs.getInt("id"), rs.getString("name"));
      }
      return null;
    }
  }
}
```

#### ❌ Incorrecto

```java
public User findByEmail(String email) throws SQLException {
  // Si email = "x' OR '1'='1", la consulta devuelve TODOS los usuarios.
  String sql = "SELECT id, name FROM users WHERE email = '" + email + "'";

  try (Connection conn = dataSource.getConnection();
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery(sql)) {

    if (rs.next()) {
      return new User(rs.getInt("id"), rs.getString("name"));
    }
    return null;
  }
}
```

### 8.4 Records para modelos inmutables

Para modelos de datos simples e inmutables, usar `record` en lugar de una clase con campos, getters, `equals()`, `hashCode()` y `toString()` escritos a mano.

#### ✅ Correcto

```java
public record User(int id, String name, String email) {
}
```

#### ❌ Incorrecto

```java
public class User {
  private final int id;
  private final String name;
  private final String email;

  public User(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User other = (User) o;
    return id == other.id
        && Objects.equals(name, other.name)
        && Objects.equals(email, other.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }

  @Override
  public String toString() {
    return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
  }
}
```

* 👉 `record` genera automáticamente el constructor, los accesores (`id()`, `name()`, `email()`), `equals()`, `hashCode()` y `toString()`.

## 🔥 Conclusión rápida

- `PascalCase` → clases
- `camelCase` → métodos y variables
- `UPPER_SNAKE_CASE` → constantes
- 2 espacios → indentación
- 100 caracteres → límite por línea
- Javadoc → documentación
- Consistencia > preferencias personales
- Código → Inglés

> [!NOTE]
> - Los ejemplos presentados son ilustrativos.
> - No es necesario que el código sea exactamente igual,
> - pero sí respetar las buenas prácticas descritas.

## ✅ Conclusión

El proyecto utilizará nombres en inglés para clases, métodos, variables y funciones con el objetivo de mantener consistencia con las buenas prácticas de desarrollo en Java y facilitar la comprensión del código.
