# Ejecutar desde PowerShell despues de asignar $env:SONAR_TOKEN
$ErrorActionPreference = 'Stop'
Set-Location (Split-Path $PSScriptRoot -Parent)
if ([string]::IsNullOrWhiteSpace($env:SONAR_TOKEN)) {
    throw 'Falta SONAR_TOKEN. Crea uno en SonarQube y define $env:SONAR_TOKEN en la terminal.'
}
$hostUrl = if ($env:SONAR_HOST_URL) { $env:SONAR_HOST_URL } else { 'http://localhost:9000' }
Write-Host "Analizando en $hostUrl"
& mvn clean verify 'org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356:sonar' "-Dsonar.host.url=$hostUrl"
if ($LASTEXITCODE -ne 0) { throw 'El analisis SonarQube no termino correctamente' }
Write-Host "Abre $hostUrl/dashboard?id=expositotop-practica3"
