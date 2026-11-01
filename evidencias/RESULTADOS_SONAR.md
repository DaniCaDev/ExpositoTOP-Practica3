# Calidad de código · SonarQube

## Configuración

SonarQube se usa con el objetivo de identificar problemas de fiabilidad, seguridad y mantenibilidad. El POM fija `sonar.projectKey`, URL local `http://localhost:9000` y la ruta de cobertura XML de JaCoCo.

```bash
export SONAR_TOKEN="TU_TOKEN_LOCAL"
./scripts/sonar.sh
```

El script ejecuta `mvn clean verify` y el objetivo versionado de SonarScanner for Maven. El servidor debe estar activo antes del análisis.

## Hallazgos al inspeccionar el código original

> Estos son **hallazgos manuales comprobados sobre los archivos originales**, NO incidencias ni métricas certificadas por un informe de SonarQube.

| Archivo | Hallazgo | Solución propuesta o realizada |
|---|---|---|
| `TOPTWReader.java` | `System.exit(0)` durante un fallo de lectura | **Corregido:** excepción informativa y cierre automático |
| `ExpositoUtilities.java` | `reader.close()` cuando el lector puede ser `null` | **Corregido:** `try-with-resources` |
| `mainTOPTW.java` | Nombre de clase sin convención y pruebas extensas por defecto | **Corregido:** `MainTOPTW` y opción `--quick` |
| `TOPTWEvaluator.java` | Evaluación comentada y método sin efectos | **Pendiente:** implementar la evaluación y probarla |
| `TOPTWGRASP.java` | Bloque de búsqueda local comentado y gran volumen de salida | **Pendiente:** implementar búsqueda local y logging configurable |
| `TOPTWSolution.java` | Método `setWaitingTime(int,int)` reduce la precisión del campo `double[]` | **Pendiente:** aceptar `double` y cubrir con pruebas |

## Evidencias reales pendientes de completar

Después de ejecutar SonarQube, anota en `evidencias/RESULTADOS_SONAR.md`: versión, fecha, Quality Gate, bugs/issues, vulnerabilidades, *code smells*, duplicación, deuda técnica, cobertura, capturas del panel y, si es posible, reglas/identificadores. Sin esas capturas no debe declararse el análisis como concluido.

## Métricas vs. observaciones

Una compilación correcta prueba que el código Java compila; **no prueba** ausencia de errores lógicos ni conformidad con el Quality Gate. JaCoCo mide cobertura de pruebas, que no implica calidad funcional. SonarQube es análisis estático y no reemplaza pruebas ni revisión humana.


## Análisis real inicial de SonarQube (01/11/2026)

Esta primera medición se obtuvo **antes** de aplicar la revisión de código.

- Quality Gate: **PASSED**
- Incidencias abiertas: **5** (fiabilidad: 1 bloqueante, 1 alta y 3 medias).
- Seguridad: **0** incidencias.
- Duplicación: **0,0 %**.
- Cobertura de pruebas: **12,6 %**.
- Código analizado: aproximadamente **1,2 K líneas**.
- Esfuerzo total estimado para solucionar incidencias: **35 minutos**.

Las cinco incidencias se atribuyen a: cierre inseguro de BufferedWriter,
creación repetida de Random, contrato de iteración de PowerSet, resultado de
readLine sin comprobar, y sobrecarga confusa de equals(TOPTWSolution).

**Validación pendiente:** volver a ejecutar `mvn clean verify` y el análisis de
SonarQube en Windows tras estas correcciones. Hasta entonces no hay una segunda
medición contrastada y no debe declararse una reducción real de incidencias.
