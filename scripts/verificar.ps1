# Ejecutar: powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\verificar.ps1
$ErrorActionPreference = 'Stop'
Set-Location (Split-Path $PSScriptRoot -Parent)
Write-Host '1/3: comprobar Maven y Java'
& mvn -version
if ($LASTEXITCODE -ne 0) { throw 'No se pudo ejecutar Maven' }
Write-Host '2/3: construir el proyecto y ejecutar pruebas'
& mvn clean verify
if ($LASTEXITCODE -ne 0) { throw 'mvn clean verify ha fallado' }
Write-Host '3/3: ejecutar una instancia corta'
& java -jar '.\target\expositotop-1.0.0-SNAPSHOT.jar' --quick
if ($LASTEXITCODE -ne 0) { throw 'La ejecucion del JAR ha fallado' }
Write-Host 'Comprobacion terminada. Revisar target\surefire-reports y target\site\jacoco.'
