# GUIA COMPLETA PARA WINDOWS 10/11 Y CURSOR

**Práctica 3 — Laboratorio de Desarrollo y Herramientas — Curso 2026-2027**

Este documento reemplaza los comandos macOS de la versión anterior. Trabaja en **PowerShell**, desde la carpeta **Practica3_ExpositoTOP** que contiene `pom.xml`. **No necesitas recrear el proyecto**: viene con Java, Maven, tests, Doxygen, MkDocs y el informe reorganizado.

## Actividades 1-3: instalar, estudiar y comprobar Maven

1. En **PowerShell** instala Java JDK 21 con `winget install --id EclipseAdoptium.Temurin.21.JDK -e`. Reinicia la terminal y comprueba `java -version` y `javac -version`.
2. Descarga desde https://maven.apache.org/download.cgi el **Binary zip archive** de Apache Maven 3. Descomprime en `C:\Herramientas\apache-maven-3.10.0` (ajustando la versión real) y agrega `C:\Herramientas\apache-maven-3.10.0\bin` al **Path** de Windows. Reinicia la terminal y comprueba `mvn -version`. Si falla, revisa `JAVA_HOME` y `Path`.
3. Instala Cursor, abre la carpeta **Practica3_ExpositoTOP**, instala extensiones **Extension Pack for Java** y **Maven for Java**. `Ctrl+Shift+P` permite buscar Maven; también encontrarás un explorador Maven si la extensión está activa.
4. Estudia https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html y `pom.xml`; las órdenes `validate`, `compile`, `test`, `package`, `install`, `deploy` explican las fases que pide el guion.

## Actividad 4: preparar y comprobar el proyecto

1. Descomprime `Practica3_ExpositoTOP_WINDOWS.zip` en una ruta **sin espacios ni caracteres especiales** si es posible, p. ej. `C:\Proyectos\Practica3_ExpositoTOP`.
2. En Cursor elige **File > Open Folder**, selecciona **la carpeta que contiene `pom.xml`** (no abras solo `src`).
3. **Terminal > New Terminal** y selecciona PowerShell. Confirma `Test-Path .\pom.xml` => `True`.
4. Ejecuta:

```powershell
mvn clean verify
java -jar .\target\expositotop-1.0.0-SNAPSHOT.jar --quick
```

**Evidencias**: `BUILD SUCCESS`, resultado `Tests run` de Surefire, `target\site\jacoco\jacoco.xml`, salida de ejecución. Puedes registrar los comandos:

```powershell
mvn clean verify *>&1 | Tee-Object -FilePath .\evidencias\maven-verify.log
java -jar .\target\expositotop-1.0.0-SNAPSHOT.jar --quick *>&1 | Tee-Object -FilePath .\evidencias\ejecucion-windows.log
```

**Atención**: el proyecto está compilado para **Java 17** (`maven.compiler.release=17`), pero usar JDK 21 para compilar es válido.

## Actividad 5: ejecutar las 6 fases Maven desde terminal Y Cursor

Para generar un log íntegro en Windows:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\capturar_evidencias.ps1
```

Las fases exigidas se prueban en este orden: `validate`, `compile`, `test`, `package`, `install`, `deploy`. **`deploy` usa un repositorio de prueba local**, no publica el proyecto en GitHub ni en Internet. Comprueba si aparece `.artifacts\maven-repository\`. Si Windows da un error de ruta `file://...` en `deploy`, envía ese log para ajustar la ruta del repositorio.

Desde **Cursor** abre el panel Maven de la extensión y ejecuta los mismos objetivos (uno a uno); captura el panel y la terminal. Si no aparece el panel, busca `Maven: Execute Commands` en `Ctrl+Shift+P`; la ubicación exacta depende de la versión de la extensión.

**Entregar**: `evidencias\maven-ciclo-vida.log` y 1-2 capturas Maven de Cursor.

## Actividad 6: plugins Maven

Ya están configurados en `pom.xml`: Compiler, Surefire, JAR, Exec, JaCoCo y configuración de SonarScanner para Maven. Explica sus objetivos, fases y aportaciones; revisa `docs\maven.md`. Para cobertura: `mvn clean verify` y abre el archivo `target\site\jacoco\index.html` si existe.

## Actividades 7 y 8: SonarQube en Windows

1. Instala Docker Desktop (https://www.docker.com/products/docker-desktop/); configura/activa WSL 2 si Docker te lo solicita. Necesitas RAM suficiente; SonarQube Community recomienda al menos **4 GB** para una instalación pequeña. 
2. Con Docker Desktop en marcha:

```powershell
docker --version
docker run -d --name sonarqube-practica3 -p 9000:9000 -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true sonarqube:latest
```

3. Abre http://localhost:9000 cuando esté listo. Si Docker avisa de que el nombre ya existe, comprueba `docker ps -a` y usa `docker start sonarqube-practica3` para reutilizarlo. Si ya tienes SonarQube levantado, no crees otro.
4. Entra con `admin`/`admin` inicialmente si el servidor acaba de crearse, cambia contraseña, crea el proyecto **local** clave `expositotop-practica3`, genera un token de análisis y **NO lo envíes ni lo captures**.
5. En la misma terminal PowerShell escribe:

```powershell
$env:SONAR_TOKEN = "PEGA_TU_TOKEN_PRIVADO"
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\sonar.ps1 *>&1 | Tee-Object -FilePath .\evidencias\sonar-ejecucion.log
Remove-Item Env:SONAR_TOKEN
```

**Precaución**: escribir token en terminal puede guardarse en historial; alternativa: `$env:SONAR_TOKEN = Read-Host 'Pega el token de SonarQube'` (la entrada puede verse según la versión de PowerShell). Evita subir archivos con secretos. Si hay errores, elimina cualquier token antes de compartir logs.

6. En http://localhost:9000/dashboard?id=expositotop-practica3 captura Quality Gate, cobertura, duplicaciones, fiabilidad, mantenibilidad, seguridad y 2-3 incidencias del panel (regla, ruta, línea, severidad). Anota números reales en `evidencias\RESULTADOS_SONAR.md`. Redactaremos correcciones solo después del primer informe.

## Actividad 9.1: Doxygen y Graphviz

Instala con PowerShell:

```powershell
winget install --id DimitriVanHeesch.Doxygen -e
winget install --id Graphviz.Graphviz -e
```

Reabre terminal y comprueba `doxygen --version` y `dot -V`.

```powershell
New-Item -ItemType Directory -Force .artifacts | Out-Null
doxygen Doxyfile
Start-Process .\.artifacts\doxygen\html\index.html
```

El `Doxyfile` ya tiene `HAVE_DOT = YES`, `DOT_IMAGE_FORMAT=svg` y usa `src/main/java`. Captura índice de clases, una clase Java y un grafo. Si hay avisos, pasa `.artifacts\doxygen-warnings.log`.

## Actividades 9.2, 9.3 y 9.4: MkDocs, Material, MkDoxy y PDF técnico

Instala Python 3.12:

```powershell
winget install --id Python.Python.3.12 -e
```

Reinicia Cursor/PowerShell. Desde `Practica3_ExpositoTOP`:

```powershell
py -3.12 -m venv .venv
.\.venv\Scripts\python.exe -m pip install --upgrade pip
.\.venv\Scripts\python.exe -m pip install -r requirements-docs.txt
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\docs.ps1
```

**No necesitas activar el venv**, por lo que se evitan errores de política de scripts de PowerShell. Verifica:

```powershell
Start-Process .\site\index.html
Start-Process .\site\pdf\documentacion-tecnica.pdf
```

Para servir el portal local:

```powershell
.\.venv\Scripts\python.exe -m mkdocs serve
```

Abre http://127.0.0.1:8000 mientras el servidor esté activo. Los `.md` generados con asistencia de IA ya están en `docs/` (manual de usuario, arquitectura, Maven, calidad y decisiones ADR); revísalos antes de entregar.

**Importante en Windows:** `mkdocs-with-pdf` depende de **WeasyPrint/Pango**; si el comando falla con `cannot load library`, instala MSYS2 desde https://www.msys2.org/, abre su consola **UCRT64**, ejecuta `pacman -S mingw-w64-ucrt-x86_64-pango`, cierra esa consola y vuelve a PowerShell:

```powershell
$env:WEASYPRINT_DLL_DIRECTORIES = 'C:\msys64\ucrt64\bin'
.\.venv\Scripts\python.exe -m weasyprint --info
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\docs.ps1
```

Si la compilación del portal todavía falla, envía la salida exacta. **No sustituyas el PDF de MkDocs por el PDF del informe**: son dos entregables diferentes.

## GitHub y entrega

Después de que el PDF del portal exista y el análisis SonarQube esté completado:

```powershell
Copy-Item .\site\pdf\documentacion-tecnica.pdf .\docs\entregable-documentacion.pdf
winget install --id Git.Git -e
# Reabrir PowerShell si Git acaba de instalarse.
git init
git add .
git status
git commit -m "Practica 3: Maven, SonarQube, Doxygen y MkDocs"
git branch -M main
```

Crea un repositorio vacío en GitHub y copia su URL HTTPS. Después:

```powershell
git remote add origin https://github.com/TU_USUARIO/practica3-expositotop.git
git push -u origin main
```

No publiques `SONAR_TOKEN`, contraseñas, `.venv`, `target`, `site`, `.artifacts` ni logs. GitHub puede pedir autenticación mediante navegador o gestor de credenciales. Agrega la **URL real del repositorio** al informe reorganizado. El despliegue GitHub Pages es opcional según el guion.

## Qué pasarme para cerrar el informe

- `evidencias/maven-ciclo-vida.log`, `evidencias/maven-verify.log`, capturas desde Cursor y, si hay fallos, Surefire.
- Capturas SonarQube (dashboard y 2-3 incidencias concretas) y log de escaneo **sin secretos**.
- Capturas Doxygen con Graphviz, resultados de MkDocs y `site/pdf/documentacion-tecnica.pdf`.
- URL GitHub del repositorio.

**Estado honestamente reportado**: los scripts de Windows están preparados y empaquetados aquí, pero **no se han ejecutado en tu equipo**, así que la validación final y los datos del escáner dependen de tus ejecuciones.
