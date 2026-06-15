#!/bin/bash

FALLOS=0

echo "Iniciando verificación de conformidad con estructura Maven..."

JAVA_INVALIDOS=$(find . -name "*.java" ! -path "./src/main/java/*" ! -path "./src/test/java/*" ! -path "./scripts/*" ! -path "./target/*")

if [ ! -z "$JAVA_INVALIDOS" ]; then
    echo "[ERROR] Archivos .java detectados en rutas no permitidas:"
    echo "$JAVA_INVALIDOS"
    FALLOS=$((FALLOS + 1))
else
    echo "[OK] Estructura de archivos fuentes .java correcta."
fi

FXML_INVALIDOS=$(find . -name "*.fxml" ! -path "./src/main/resources/*" ! -path "./target/*")

if [ ! -z "$FXML_INVALIDOS" ]; then
    echo "[ERROR] Archivos .fxml detectados fuera de src/main/resources/:"
    echo "$FXML_INVALIDOS"
    FALLOS=$((FALLOS + 1))
else
    echo "[OK] Estructura de archivos de interfaz .fxml correcta."
fi

if [ $FALLOS -gt 0 ]; then
    echo "[FALLO] La estructura del proyecto no cumple con el estándar requerido."
    exit 1
else
    echo "[ÉXITO] Verificación de estructura Maven completada sin novedades."
    exit 0
fi