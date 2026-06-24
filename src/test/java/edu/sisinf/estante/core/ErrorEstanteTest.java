package edu.sisinf.estante.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorEstanteTest {

```
@Test
void errorConexionDebeExtenderErrorEstante() {
    ErrorConexion error = new ErrorConexion("Error de conexión");

    assertTrue(error instanceof ErrorEstante);
    assertEquals("Error de conexión", error.getMessage());
}

@Test
void errorPersistenciaDebeExtenderErrorEstante() {
    ErrorPersistencia error = new ErrorPersistencia("Error de persistencia");

    assertTrue(error instanceof ErrorEstante);
    assertEquals("Error de persistencia", error.getMessage());
}

@Test
void errorQueryDebeExtenderErrorEstante() {
    ErrorQuery error = new ErrorQuery("Error de consulta");

    assertTrue(error instanceof ErrorEstante);
    assertEquals("Error de consulta", error.getMessage());
}

@Test
void errorEstanteDebeSerRuntimeException() {
    ErrorEstante error = new ErrorEstante("Error base");

    assertTrue(error instanceof RuntimeException);
    assertEquals("Error base", error.getMessage());
}

@Test
void debePoderLanzarseSinTryCatch() {
    assertThrows(ErrorEstante.class, () -> {
        throw new ErrorEstante("Excepción sin try/catch");
    });
}
```

}
