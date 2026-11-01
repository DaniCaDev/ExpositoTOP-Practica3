#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
command -v doxygen >/dev/null || { echo "Falta doxygen"; exit 1; }
command -v dot >/dev/null || { echo "Falta graphviz"; exit 1; }
command -v mkdocs >/dev/null || { echo "Activa el entorno .venv de MkDocs"; exit 1; }
mkdir -p .artifacts
echo "Generando Doxygen (HTML, grafos SVG y LaTeX)"
doxygen Doxyfile
echo "Generando MkDocs + PDF con mkdocs-with-pdf"
mkdocs build --clean
printf '\nListo: site/index.html, site/pdf/documentacion-tecnica.pdf, .artifacts/doxygen/html/index.html\n'
