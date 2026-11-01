#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
echo "[1/3] Maven"; mvn -version
echo "[2/3] Ciclo de vida, pruebas y cobertura"; mvn clean verify
echo "[3/3] Ejecución corta"; mvn -q exec:java -Dexec.args="--quick"
echo "Correcto: comprueba target/surefire-reports y target/site/jacoco/"
