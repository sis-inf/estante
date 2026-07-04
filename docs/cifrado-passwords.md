# Cifrado de contraseñas de conexión

## Descripción

El proyecto utiliza cifrado AES para proteger las contraseñas de conexión almacenadas. Este mecanismo evita que las credenciales queden expuestas en texto plano y proporciona una capa adicional de seguridad para la configuración.

## Esquema de cifrado

El algoritmo AES (Advanced Encryption Standard) permite cifrar la información utilizando una clave secreta compartida entre el proceso de cifrado y el de descifrado.

Su propósito es proteger los datos almacenados frente a accesos no autorizados.

## Derivación de la clave

La clave utilizada para el cifrado se deriva a partir de una contraseña o secreto configurado por el usuario o el entorno.

La derivación garantiza que la clave tenga el formato y longitud adecuados para ser utilizada por el algoritmo AES.

## ¿Qué protege?

Este mecanismo ayuda a proteger:

- Contraseñas almacenadas.
- Credenciales de conexión.
- Configuración sensible guardada en disco.

## Limitaciones

Es importante conocer las limitaciones del enfoque:

- No protege las contraseñas mientras están siendo utilizadas en memoria.
- No reemplaza una correcta gestión de secretos.
- La seguridad depende de mantener protegida la clave utilizada para el cifrado.
- No evita accesos no autorizados si un atacante obtiene la clave de cifrado.

## Recomendaciones

- Utilizar claves robustas.
- Mantener las claves fuera del código fuente.
- Rotar periódicamente los secretos cuando sea necesario.
- Restringir el acceso a la configuración sensible.
