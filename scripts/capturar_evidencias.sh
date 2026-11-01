#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p evidencias
{
 printf '=== Java ===\n'; java -version 2>&1
 printf '\n=== Maven ===\n'; mvn -version
 for phase in validate compile test package install deploy; do
   printf '\n=== mvn %s ===\n' "$phase"
   mvn -B "$phase" || exit 1
 done
} 2>&1 | tee evidencias/maven-ciclo-vida.log
echo "Verifica también desde Cursor y adjunta capturas reales a evidencias/."
