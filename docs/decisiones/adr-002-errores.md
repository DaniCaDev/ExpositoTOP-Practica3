# ADR-002 · Manejar errores sin terminar la JVM

**Estado:** aceptada e implementada parcialmente.

## Contexto

En `TOPTWReader.java`, el código original imprimía la excepción y ejecutaba `System.exit(0)` ante problemas de lectura. El cero representa habitualmente una terminación correcta y dificulta la integración con pruebas, IDE y automatización.

## Decisión

Usar `try-with-resources` y propagar una excepción `IllegalArgumentException` con la ruta de entrada. Priorizar recursos del classpath para que las instancias estén disponibles en el JAR.

## Consecuencias

El consumidor puede decidir qué hacer ante el error sin terminar forzosamente el proceso. Se han añadido pruebas para rutas inexistentes. El formato de instancias aún necesita una validación de esquema más exhaustiva.
