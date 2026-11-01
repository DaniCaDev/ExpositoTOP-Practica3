# Ejecutar: powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\docs.ps1
$ErrorActionPreference = 'Stop'
Set-Location (Split-Path $PSScriptRoot -Parent)
New-Item -ItemType Directory -Force -Path '.artifacts' | Out-Null
$python = Join-Path (Get-Location).Path '.venv\Scripts\python.exe'
if (-not (Test-Path $python)) { throw 'No encuentro .venv. Ejecuta: py -3.12 -m venv .venv y luego instala requirements-docs.txt.' }
& doxygen Doxyfile
if ($LASTEXITCODE -ne 0) { throw 'Doxygen ha fallado. Comprueba doxygen --version y dot -V.' }
& $python -m mkdocs build --clean
if ($LASTEXITCODE -ne 0) { throw 'MkDocs o mkdocs-with-pdf ha fallado; envia el error exacto. En Windows WeasyPrint puede requerir Pango (MSYS2).' }
$html = '.\site\index.html'
$pdf = '.\site\pdf\documentacion-tecnica.pdf'
if (-not (Test-Path $html)) { throw 'No encuentro site\index.html' }
if (-not (Test-Path $pdf)) { throw 'No encuentro site\pdf\documentacion-tecnica.pdf; revisa la configuracion del plugin with-pdf.' }
Write-Host 'Portal y PDF generados:' $html $pdf
