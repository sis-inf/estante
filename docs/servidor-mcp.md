# Servidor MCP para asistentes de IA

## Descripción

El servidor MCP (Model Context Protocol) permite que asistentes de inteligencia artificial interactúen con el proyecto mediante una interfaz segura. Por defecto, esta funcionalidad se encuentra desactivada para evitar accesos no deseados.

## Características

- Está desactivado por defecto.
- Expone únicamente operaciones de lectura.
- No modifica archivos ni datos del proyecto.
- Puede habilitarse de forma consciente cuando sea necesario.

## Operaciones disponibles

Cuando el servidor está habilitado, permite consultar información del proyecto mediante operaciones de solo lectura, como:

- Lectura de archivos.
- Consulta de documentación.
- Exploración de la estructura del proyecto.

No permite realizar operaciones de escritura.

## Cómo habilitarlo

Para utilizar el servidor MCP:

1. Revisar la configuración correspondiente del proyecto.
2. Habilitar el servidor de forma explícita.
3. Conectar un cliente compatible con MCP.
4. Utilizar únicamente las operaciones de lectura disponibles.

## Seguridad

El servidor permanece desactivado por defecto como medida de seguridad. Esto reduce el riesgo de accesos accidentales y garantiza que únicamente se habilite cuando el usuario lo decida conscientemente.

## Conclusión

El servidor MCP facilita la integración de asistentes de IA con el proyecto de manera controlada y segura, ofreciendo acceso de solo lectura y requiriendo una activación explícita antes de su uso.
