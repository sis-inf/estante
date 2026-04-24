# Arquitectura del Sistema

## Visión general
El sistema "Estante" está diseñado bajo una arquitectura de tres capas para asegurar el orden, la escalabilidad y una clara separación de responsabilidades entre la interfaz, la lógica y los datos.

## Componentes principales
1. **Capa de Vista**: Desarrollada en JavaFX, maneja la interacción directa con el usuario.
2. **Capa de Lógica**: Contiene las reglas de negocio y procesa la información.
3. **Capa de Base de Datos**: Gestiona la persistencia mediante JDBC para conectar con el servidor.

## Diagrama de arquitectura
El flujo sigue el patrón MVC (Modelo-Vista-Controlador), donde la vista envía acciones al controlador, este aplica la lógica y se comunica con el modelo de datos.

## Tecnologías utilizadas

| Componente | Tecnología | Versión | Justificación |
|---|---|---|---|
| Interfaz | JavaFX | 17+ | Estándar moderno para aplicaciones de escritorio en Java. |
| Lenguaje | Java | 17+ | Lenguaje robusto y orientado a objetos. |
| Conector | JDBC | 4.2+ | Permite la comunicación estándar con motores SQL. |

## Decisiones de diseño

### Decisión 1
**Contexto:** Necesidad de separar la interfaz del código de base de datos.
**Decisión:** Implementar el patrón MVC.
**Consecuencias:** Código más limpio, fácil de mantener y probar.

## Flujo de datos
El usuario realiza una acción en la **Vista** -> La **Lógica** valida la petición -> El conector **JDBC** realiza la consulta a la base de datos -> El resultado retorna a la Vista.
