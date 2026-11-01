# ADR-001 · Adoptar Maven

**Estado:** aceptada para la práctica.

## Contexto

El proyecto original se entregó en un `.rar` con configuración de Ant/NetBeans y sin un `pom.xml` utilizable como proyecto Maven estándar.

## Decisión

Mover los `.java` a `src/main/java`, integrar las instancias en `src/main/resources`, añadir pruebas en `src/test/java` y versionar las fases y plugins desde un único `pom.xml`.

## Consecuencias

- Positivas: estructura uniforme, IDE, dependencias declarativas, empaquetado reproducible y conexión directa con SonarScanner for Maven.
- Costes: descargar plugins y dependencias al primer uso; requiere migrar las rutas de lectura.

## Alternativas

Conservar Ant, o usar Gradle. No se eligieron porque la práctica exige experimentar explícitamente con las fases de Maven.
