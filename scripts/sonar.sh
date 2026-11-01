#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
: "${SONAR_TOKEN:?Debes exportar SONAR_TOKEN obtenido en tu SonarQube}"
SONAR_HOST_URL="${SONAR_HOST_URL:-http://localhost:9000}"
echo "SonarQube: ${SONAR_HOST_URL}"
mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356:sonar   -Dsonar.host.url="$SONAR_HOST_URL"
echo "Consulta el informe en $SONAR_HOST_URL/dashboard?id=expositotop-practica3"
