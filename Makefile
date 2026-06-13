.PHONY: build test package run clean coverage checkstyle help

build:
	mvn compile

test:
	mvn test

package:
	mvn package -DskipTests

run:
	mvn javafx:run

clean:
	mvn clean

coverage:
	mvn jacoco:report

checkstyle:
	mvn checkstyle:check

help:
	@echo "Targets disponibles:"
	@echo "  build       - Compila el proyecto (mvn compile)"
	@echo "  test        - Ejecuta los tests (mvn test)"
	@echo "  package     - Empaqueta el proyecto sin tests (mvn package -DskipTests)"
	@echo "  run         - Ejecuta la aplicacion JavaFX (mvn javafx:run)"
	@echo "  clean       - Limpia los artefactos generados (mvn clean)"
	@echo "  coverage    - Genera reporte de cobertura JaCoCo (mvn jacoco:report)"
	@echo "  checkstyle  - Verifica estilo de codigo (mvn checkstyle:check)"
	@echo "  help        - Muestra este mensaje de ayuda"