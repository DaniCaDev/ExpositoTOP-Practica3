# Maven · Construcción del proyecto

## POM y convención de directorios

El archivo `pom.xml` declara `groupId=es.ull.esit`, `artifactId=expositotop`, versión `1.0.0-SNAPSHOT`, empaquetado JAR y Java 17. Maven utiliza la estructura `src/main/java`, `src/main/resources` y `src/test/java`.

## Fases del ciclo de vida

| Fase | Comando | Qué comprueba |
|---|---|---|
| validate | `mvn validate` | Sintaxis y metadatos del proyecto |
| compile | `mvn compile` | Compilación del código fuente |
| test | `mvn test` | Ejecución de las pruebas JUnit 5 |
| package | `mvn package` | Generación del JAR en `target/` |
| install | `mvn install` | Copia al repositorio Maven local `~/.m2` |
| deploy | `mvn deploy` | Copia a `.artifacts/maven-repository` local; no publica en Internet |

**Importante:** al ejecutar una fase, Maven ejecuta las anteriores del mismo ciclo. Para análisis de cobertura usa `mvn clean verify`, que ejecuta JaCoCo en la fase `verify`.

## Plugins configurados

| Plugin | Finalidad |
|---|---|
| `maven-compiler-plugin` | Compilar Java 17 |
| `maven-surefire-plugin` | Ejecutar JUnit 5 |
| `maven-jar-plugin` | Establecer la clase principal del JAR |
| `exec-maven-plugin` | Ejecutar `MainTOPTW` sin llamar directamente a Java |
| `jacoco-maven-plugin` | Instrumentar pruebas y generar XML para SonarQube |
| `sonar-maven-plugin` | Enviar el análisis al servidor SonarQube |

## Uso desde Cursor

Abre la carpeta que contiene `pom.xml`, instala **Extension Pack for Java** y **Maven for Java** si falta. En el panel *Maven*, despliega el proyecto y ejecuta `Lifecycle > validate`, `compile`, `test`, `package`, `install`, `deploy`. También puedes abrir la terminal integrada y ejecutar los comandos anteriores. Registra capturas de las dos modalidades para la entrega.
