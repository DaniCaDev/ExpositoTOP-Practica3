> Versión con correcciones de SonarQube propuestas a partir del análisis inicial del 01/11/2026. Antes de declarar resueltas las 5 incidencias, reanalizar con SonarQube en Windows. Consultar `SIGUIENTE_PASO_WINDOWS.md`.

# ExpositoTOP · Práctica 3 (Windows + Cursor)

**Empieza por [GUIA_WINDOWS_CURSOR.md](GUIA_WINDOWS_CURSOR.md).**

El proyecto Java está adaptado a Maven. Incluye `pom.xml`, fuentes y tests JUnit, SonarScanner Maven, `Doxyfile`, `mkdocs.yml`, páginas en `docs/`, el informe reorganizado y scripts PowerShell nativos en `scripts/`.

## Inicio rápido

```powershell
mvn -version
mvn clean verify
java -jar .\target\expositotop-1.0.0-SNAPSHOT.jar --quick
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\capturar_evidencias.ps1
```

**IMPORTANTE**: quedan pendientes los resultados reales Maven/SonarQube/Doxygen/MkDocs en tu equipo Windows y la publicación en GitHub. El informe reorganizado incluye espacios para las evidencias finales.
