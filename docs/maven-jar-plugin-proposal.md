# Propuesta: Configuración de maven-jar-plugin

## ¿Qué se propone?
Se propone configurar el plugin `maven-jar-plugin` para generar un archivo JAR ejecutable.

## ¿Para qué sirve?
Permite definir una clase principal (`Main-Class`) para que el proyecto pueda ejecutarse directamente con:

java -jar nombre.jar

## Configuración propuesta
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.3.0</version>
      <configuration>
        <archive>
          <manifest>
            <mainClass>com.ejemplo.Main</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>
