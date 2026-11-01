# Documentación automática con Doxygen

## Comentarios

Las clases principales incluyen comentarios compatibles con Doxygen `\brief` y descripciones detalladas. Se comentaron operaciones fundamentales de lectura, construcción de rutas y distancias. El código original no documentaba sistemáticamente todas las clases y métodos.

Ejemplo incluido en `TOPTWReader.java`:

```java
/**
 * \brief Construye un problema TOPTW a partir de un fichero de instancia.
 * \param filePath Ruta lógica del recurso o archivo local.
 * \return Problema con matriz de distancias calculada.
 */
public static TOPTW readProblem(String filePath) { /* ... */ }
```

## Doxyfile y Graphviz

Configuraciones principales:

```ini
INPUT = src/main/java
RECURSIVE = YES
FILE_PATTERNS = *.java
OPTIMIZE_OUTPUT_JAVA = YES
HAVE_DOT = YES
DOT_IMAGE_FORMAT = svg
GENERATE_HTML = YES
GENERATE_LATEX = YES
```

Generación:

```bash
mkdir -p .artifacts
doxygen Doxyfile
open .artifacts/doxygen/html/index.html
```

Graphviz proporciona diagramas de colaboración y herencia cuando la estructura del código permite dibujarlos. El número de grafos depende de las relaciones reales entre clases.

## Integración con MkDocs

El plugin **MkDoxy** se configura en `mkdocs.yml` para extraer automáticamente la referencia de la API Java. MkDocs utiliza el tema **Material**, y el plugin `with-pdf` genera un PDF técnico desde las páginas Markdown.

```bash
python3 -m venv .venv
source .venv/bin/activate
python -m pip install -r requirements-docs.txt
./scripts/docs.sh
```

El HTML de Doxygen se genera en `.artifacts/doxygen/html/` y el portal en `site/`. Comprueba que `site/pdf/documentacion-tecnica.pdf` exista tras construir el portal.
