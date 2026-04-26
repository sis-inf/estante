# Requerimientos del Proyecto

## Requerimientos Funcionales

| ID | Descripción | Prioridad | Estado |
|---|---|---|---|
| RF-001 |Conectar a una base de datos PostgreSQL mediante JDBC | Alta | Pendiente |
| RF-002 |Mostrar tablas disponibles en la base de datos  | Alta | Pendiente |
| RF-003 |Ejecutar consultas SQL  ingresadas por el usuario | Alta | |
| RF-004 |Mostrar los resultados  de las consulatas en formato tabular o cualquier otro formato| Alta | Pendiente |
| RF-005 |Permitir insertar nuevos registros a las tablas  | Media|Pendiente |
| RF-006 |Permitir actualizar registros existentes  | Media| Pendiente|
| RF-007 |Permitir eliminar registros  en la base de datos  | Media | Pendiente |
| RF-008 |Validar errores en consultas SQL y mostrar  mensajes | Alta | Pendiente| 
| RF-009 |Permitir  seleccionar  diferentes bases de datos |Baja|Pendiente|
| RF-010 |Permitir  guardar historial de consultas realizadas |Baja|Pendiente|


## Requerimientos No Funcionales

| ID | Descripción | Categoría | Estado |
|---|---|---|---|
| RNF-001 | El sistema debe mostrar los resultados de las consultas de SQL en tiempo máximo de 2 segundos | Rendimiento | Pendiente |
| RNF-002 | La interfaz debe permitir que un usuario realice una consulta básica en menos de 4 clics desde el inicio | Usabilidad |  Pendiente |
| RNF-003 | El sistema debe ser capaz de procesar hasta 1000 registros en una tabla sin bloqueos visuales | Rendimiento | Pendiente |
| RNF-004 | Los mensajes de error de la base de datos deben mostrarse en un lenguaje comprensible para el usuario | Usabilidad | Pendiente | 
| RNF-005 | El software debe de garantizar la integridad de los datos durante la ejecucion en multiples consultas| Fiabilidad |Pendiente | 
| RNF-006 | La aplicacion debe cargar su interfaz principal en un tiempo maximo de 3 segundos | Rendimiento | Pendiente |
| RNF-007 | El sistema debe de contar con un historial de consultas de facil acceso para el usuario | Usabilidad | Pendiente |
| RNF-008 | La interfaz debe mantener un diseño consistente en todas sus ventana y dialogos | Usabilidad | Pendiente |

## Requerimientos de Sistema

| ID | Descripción |
|---|---|
| RS-001 | Compatible con sistemas operativos Windows 10/11 y distribuciones de Linux | 
| RS-002 | Se requiere tener instalado el JDK 17 o superior como versión mínima para su ejecución |
| RS-003 | El tamaño del paquete del instalador final no debe exceder de los 100 MB de almacenamiento |
| RS-004 | El Hardware minimo requerido es un procesador de 2 nucleos y 4 GB de memoria RAM |
| RS-005 | Se requiere una resolucion de pantalla minima de 1280x720 para una vizualizacion correcta | 
| RS-006 | El sistema debe tener acceso a los puertos de la red estandar para conectar con PostgreSQL |
| RS-007 | Se requiere un espacio libre en disco de la menos de 200 MB para logs y archivos temporales |
