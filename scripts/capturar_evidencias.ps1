# Ejecutar: powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\capturar_evidencias.ps1
$ErrorActionPreference = 'Stop'
Set-Location (Split-Path $PSScriptRoot -Parent)
New-Item -ItemType Directory -Force -Path 'evidencias' | Out-Null
$log = Join-Path (Get-Location).Path 'evidencias\maven-ciclo-vida.log'
'=== Entorno Java ===' | Set-Content -Encoding UTF8 $log
& java -version 2>&1 | Out-File -Append -Encoding UTF8 $log
'=== Entorno Maven ===' | Out-File -Append -Encoding UTF8 $log
& mvn -version 2>&1 | Out-File -Append -Encoding UTF8 $log
if ($LASTEXITCODE -ne 0) { throw 'Maven no esta disponible: revisa PATH y JAVA_HOME' }
foreach ($fase in @('validate','compile','test','package','install','deploy')) {
    "`n=== mvn $fase ===" | Tee-Object -FilePath $log -Append
    & mvn -B $fase 2>&1 | Tee-Object -FilePath $log -Append
    if ($LASTEXITCODE -ne 0) { throw "La fase $fase ha fallado. Envia evidencias\maven-ciclo-vida.log." }
}
Write-Host 'Maven: 6 fases ejecutadas. Envia evidencias\maven-ciclo-vida.log.'
Write-Host 'Repite las fases desde el panel Maven de Cursor para acreditarlo en el IDE.'
