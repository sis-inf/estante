# ATAJOS DE TECLADO – SISTEMA DE GESTIÓN DE BD

## 1. Contexto

Una aplicación de gestión de bases de datos debe contar con atajos de teclado que permitan mejorar la productividad del usuario.

En este proyecto no existía documentación centralizada que describa los atajos implementados ni los atajos estándar recomendados. Por ello, se realizó un análisis directo del código fuente para identificar las combinaciones existentes y proponer mejoras futuras.

## 2. Atajos implementados

Actualmente, el sistema cuenta con un único atajo de teclado implementado:

| Atajo | Acción | Estado |
|------|--------|--------|
| Ctrl + Enter | Ejecutar consulta SQL | Implementado |

### Evidencia en el código fuente

```java
areaSQL.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
    if (event.getCode() == KeyCode.ENTER && event.isControlDown()) {
        event.consume();
        disparar();
    }
});

Este fragmento de código demuestra que cuando el usuario presiona Ctrl + Enter, el sistema ejecuta la consulta SQL escrita en el editor, consumiendo el evento del teclado para evitar comportamientos por defecto.
```

## 3. Atajos planificados (no implementados)

Los siguientes atajos no se encuentran implementados en el código actual, pero se proponen como estándares comunes en herramientas de gestión de bases de datos:

| Atajo | Acción |
|------|--------|
| Ctrl + W | Cerrar conexión activa |
| Ctrl + / | Comentar línea SQL |
| Ctrl + Space | Autocompletar código SQL |
| F5 | Refrescar árbol de conexiones |

Estos atajos se consideran mejoras futuras para aumentar la productividad y facilitar el uso del sistema.
```

## 4. Cómo agregar un atajo en JavaFX

En JavaFX, los atajos de teclado pueden implementarse mediante KeyEvent o KeyCombination.

Ejemplo usando KeyEvent:

areaSQL.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
    if (event.getCode() == KeyCode.F5) {
        event.consume();
        refrescarArbolConexiones();
    }
});

Ejemplo usando KeyCombination (recomendado en JavaFX):

KeyCombination shortcut = new KeyCodeCombination(
        KeyCode.SPACE,
        KeyCombination.CONTROL_DOWN
);

scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
    if (shortcut.match(event)) {
        event.consume();
        autocompletar();
    }
});

Este método es más escalable y recomendado para sistemas con múltiples atajos.
```
## 5. Conclusión

El sistema actualmente implementa un atajo funcional (Ctrl + Enter) que permite ejecutar consultas SQL de forma rápida desde el editor, mejorando la eficiencia del usuario.

Se identificaron además atajos estándar utilizados en herramientas similares que aún no han sido implementados, por lo que se recomienda su incorporación en futuras versiones para mejorar la experiencia de uso, la productividad y la usabilidad del sistema.