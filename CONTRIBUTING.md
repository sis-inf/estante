# Cómo contribuir a este proyecto

Gracias por tu interés en contribuir. Este proyecto usa
el **Forking Workflow**. Lee este documento antes de empezar.

---

## Flujo de trabajo

### 1. Haz fork del repositorio

Botón **Fork** en la esquina superior derecha de GitHub.

### 2. Clona tu fork

```bash
git clone https://github.com/TU-USUARIO/PROYECTO.git

cd PROYECTO
```

### 3. Agrega el repo original como upstream

```bash
git remote add upstream https://github.com/sis-inf/estante.git
```

### 4. Sincroniza antes de trabajar

```bash
git checkout dev

git pull upstream dev
```

### 5. Crea tu rama de trabajo

```bash
git checkout -b tipo/[`descripcion-corta`]
```
#### Convencion de nombres de ramas

tipo/[`descripcion corta`]

**Tipos de ramas:**

- `feat/` → nueva funcionalidad
- `fix/` → corrección de errores
- `docs/` → documentación
- `test/` → pruebas
- `chore/` → tareas generales o configuración
- `refactor/` → mejoras internas
- `security/` → mejoras de seguridad
- `data/` → análisis de datos

**Ejemplos de ramas:**
- feat/endpoint-metricas-cpu
- docs/readme-instalacion
- fix/calculo-ram-incorrecto
- test/pruebas-unitarias-cpu
- chore/configurar-github-actions
- security/analisis-dependencias

### 6. Trabaja y haz commits pequeños

```bash
git add .

git commit -m "tipo: descripción corta en presente - Closes #N"
```
> [!NOTE]
> Siempre referencia el issue correspondiente en el commit: Closes #N

### 7. Sube tu rama a tu fork

```bash
git push origin tipo/descripcion-corta
```

### 8. Abre un Pull Request

- Base: `sis-inf/estante` → rama `dev`
- Compare: `TU-USUARIO/PROYECTO` → tu rama

#### Recomendaciones para el PR

- Explica claramente los cambios realizados
- Referencia el issue correspondiente (`Closes #N`)
- Mantén el PR enfocado en un solo objetivo
- Añade evidencia (logs, capturas, etc.) si aplica

---

## Convención de commits

| Tipo | Cuándo usarlo |
|---|---|
| `feat:` | Nueva funcionalidad |
| `fix:` | Corrección de error |
| `docs:` | Documentación |
| `test:` | Pruebas |
| `chore:` | Configuración o CI/CD |
| `refactor:` | Mejora sin cambiar comportamiento |
| `security:` | Mejora de seguridad |
| `data:` | Análisis de datos |

### Ejemplos

feat: agregar endpoint /metrics para CPU
fix: corregir cálculo de porcentaje de RAM
docs: agregar guía de instalación en Windows
test: agregar pruebas unitarias para módulo de disco
chore: configurar GitHub Actions para CI

---

## Reglas importantes

- ❌ Nunca hagas push directo a `main` o `dev`
- ❌ Nunca trabajes directamente en `main` o `dev`
- ✅ Un issue = una rama = un PR
- ✅ Todo PR debe referenciar su issue con `Closes #N`
- ✅ El PR debe pasar el CI antes de ser mergeado
- ✅ Todo PR necesita al menos una revisión

---

## Ramas del proyecto

| Rama | Propósito |
|---|---|
| `main` | Versión estable — solo recibe merges desde `dev` |
| `dev` | Rama de desarrollo principal |
| `feat/*` | Nuevas funcionalidades |
| `fix/*` | Correcciones |
| `docs/*` | Documentación |
| `test/*` | Pruebas |
| `chore/*` | Configuración |

---

## ¿No sabes por dónde empezar?

1. Revisa los issues abiertos con la etiqueta `good first issue`
2. Comenta en el issue que quieres trabajar
3. Espera confirmación antes de empezar
4. Sigue los pasos de este documento

---

**¡Gracias por contribuir al proyecto!**