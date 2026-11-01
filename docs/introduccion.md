# Introducción y alcance

## Problema que se resuelve

RTVRPTW, en el archivo original denominado `ExpositoTOP`, aborda la construcción de itinerarios turísticos que deben respetar ventanas temporales. Cada punto tiene coordenadas, una recompensa, un tiempo de servicio y una hora mínima/máxima de visita. La solución intenta seleccionar y ordenar visitas en varias rutas factibles.

La heurística `TOPTWGRASP` emplea listas restringidas de candidatos (RCL) y selección aleatoria para construir rutas. El programa original posee un lazo de 10 000 iteraciones para cada uno de varios tamaños de RCL y 29 instancias, que puede generar mucho texto en consola.

## Origen y límites del código

El código y las instancias proceden exclusivamente del archivo `ExpositoTOP.rar` proporcionado con la práctica. No se afirma que la búsqueda local esté implementada: su bloque aparece como comentario en `TOPTWGRASP` y el evaluador `TOPTWEvaluator.evaluate()` es un método cuyo cuerpo efectivo está vacío.

La migración mantiene el algoritmo de construcción. Las modificaciones se centran en empaquetado, entradas de ejecución, control de errores, documentación y pruebas.

## Organización de los ficheros

```text
src/main/java/       Clases Java originales adaptadas
src/main/resources/  Conjunto de instancias de los problemas
src/test/java/       Pruebas unitarias nuevas
pom.xml              Ciclo de vida y plugins de Maven
Doxyfile             Configuración de Doxygen y Graphviz
mkdocs.yml           Portal MkDocs / MkDoxy / Material
scripts/             Automatización de comprobaciones
```
