# Qué hacer después del primer análisis SonarQube (Windows + Cursor)

**Este ZIP es una copia con 5 correcciones propuestas aplicadas a código Java.**
Las métricas iniciales corresponden a la versión **anterior**: Quality Gate PASSED,
5 issues (1 Blocker, 1 High, 3 Medium), 0 problemas de seguridad,
0,0 % de duplicación y 12,6 % de cobertura.

## 1. Haz copia de seguridad

Cierra el proyecto anterior en Cursor y guarda tus archivos si has realizado
cambios propios. Extrae este ZIP en una **carpeta nueva** y ábrela en Cursor,
seleccionando la carpeta que contiene `pom.xml`.

## 2. Compila y ejecuta todas las pruebas

Desde la terminal PowerShell de Cursor:

```powershell
mvn clean verify
```

Se ha incorporado `PowerSetTest.java` como prueba nueva de independencia
entre iteradores, además de las siete pruebas anteriores. Si se ejecutan
correctamente, Maven debe mostrar **8 pruebas superadas** y `BUILD SUCCESS`.

## 3. Repite el análisis SonarQube

En el mismo PowerShell introduce tu token nuevo, sin publicarlo:

```powershell
$tokenSeguro = Read-Host "Token de SonarQube" -AsSecureString
$env:SONAR_TOKEN = [System.Net.NetworkCredential]::new("", $tokenSeguro).Password
mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar "-Dsonar.projectKey=expositotop-practica3" "-Dsonar.host.url=http://localhost:9000"
Remove-Item Env:SONAR_TOKEN
Remove-Variable tokenSeguro
```

El proyecto `expositotop-practica3` debe ser el mismo para comparar mediciones.

## 4. Guarda las evidencias

1. Captura final de Maven donde se vea `BUILD SUCCESS` y el número de pruebas.
2. Captura final de SonarQube > Overview (Quality Gate, issues, duplicación, cobertura).
3. Captura final de SonarQube > Analysis > Issues (filtros y número de avisos).
4. Guarda esas capturas separadas de la medición inicial.

## 5. Importante para el informe

No afirmes que las incidencias han desaparecido hasta ver un segundo análisis.
Aun cuando SonarQube marque 0 issues, la cobertura del 12,6 % inicial
requiere más pruebas; una única prueba nueva puede mejorarla poco.

## 6. Después de SonarQube

Ejecutaremos Doxygen + Graphviz y MkDocs para generar el portal y PDF técnico,
y finalmente publicaremos el repositorio en GitHub.
