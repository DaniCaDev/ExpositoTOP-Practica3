# Manual de usuario

## Requisitos

JDK 17 o superior; Apache Maven 3.9+ recomendado. Para generar documentación: Python 3.11+, Doxygen, Graphviz y los módulos de `requirements-docs.txt`.

## Ejecución rápida

Desde la raíz del proyecto:

```bash
mvn clean verify
java -jar target/expositotop-1.0.0-SNAPSHOT.jar --quick
```

`--quick` selecciona una instancia reducida, una iteración y RCL de tamaño 3. La ejecución normal (`java -jar ...` sin argumentos) conserva las 29 instancias y los ciclos largos originales.

## Ejecución controlada

```bash
java -jar target/expositotop-1.0.0-SNAPSHOT.jar --instance c101.txt --iterations 2
```

Parámetros disponibles:

| Argumento | Descripción |
|---|---|
| `--quick` | Demostración con `c101Recortada.txt` |
| `--instance NOMBRE` | Nombre de una instancia incluida en `Instances/TOPTW/` |
| `--iterations N` | Número positivo de iteraciones por RCL |

**Aviso:** las soluciones pueden variar entre ejecuciones porque la selección incluye aleatoriedad; la puntuación mostrada no es una métrica de SonarQube.

## Resolución de problemas

- **`mvn: command not found`:** instala Maven y vuelve a abrir la terminal.
- **`java: command not found`:** verifica el JDK con `java -version`.
- **No se encuentra la instancia:** comprueba el nombre exacto y que el recurso se empaquetó dentro de `target/*.jar`.
- **La ejecución produce demasiado texto:** utiliza `--quick` y pocas iteraciones.
