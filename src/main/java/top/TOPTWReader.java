package top;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import es.ull.esit.utilities.ExpositoUtilities;

/**
 * \brief Lector de instancias TOPTW con ventanas temporales.
 *
 * Lee los puntos de interés, sus coordenadas, beneficios y restricciones
 * temporales a partir de un recurso del classpath o de un archivo local.
 */
public class TOPTWReader {

    /**
     * \brief Construye un problema TOPTW a partir de un fichero de instancia.
     *
     * Primero localiza el recurso incluido en el JAR; si no existe, intenta
     * abrir la ruta en disco. No termina la JVM cuando falla la lectura.
     *
     * \param filePath Ruta lógica del recurso o archivo local.
     * \return Problema con matriz de distancias calculada.
     * \throws IllegalArgumentException Si la ruta no existe o los datos son inválidos.
     */
    public static TOPTW readProblem(String filePath) {
        try (BufferedReader reader = openReader(filePath)) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new IllegalArgumentException("Instancia vacía: " + filePath);
            }
            String[] parts = ExpositoUtilities.simplifyString(firstLine).split(" ");
            TOPTW problem = new TOPTW(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]));
            String header = reader.readLine(); // encabezado auxiliar D Q
            if (header == null) {
                throw new IllegalArgumentException("Falta el encabezado auxiliar D Q: " + filePath);
            }
            for (int i = 0; i <= problem.getPOIs(); i++) {
                String line = reader.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("Instancia incompleta en punto " + i + ": " + filePath);
                }
                parts = ExpositoUtilities.simplifyString(line).split(" ");
                problem.setX(i, Double.parseDouble(parts[1]));
                problem.setY(i, Double.parseDouble(parts[2]));
                problem.setServiceTime(i, Double.parseDouble(parts[3]));
                problem.setScore(i, Double.parseDouble(parts[4]));
                if (i == 0) {
                    problem.setReadyTime(i, Double.parseDouble(parts[7]));
                    problem.setDueTime(i, Double.parseDouble(parts[8]));
                } else {
                    problem.setReadyTime(i, Double.parseDouble(parts[8]));
                    problem.setDueTime(i, Double.parseDouble(parts[9]));
                }
            }
            problem.calculateDistanceMatrix();
            problem.setMaxTimePerRoute(problem.getDueTime(0));
            return problem;
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("No se pudo leer la instancia: " + filePath, ex);
        }
    }

    private static BufferedReader openReader(String filePath) throws IOException {
        InputStream stream = TOPTWReader.class.getClassLoader().getResourceAsStream(filePath);
        if (stream != null) {
            return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        }
        return Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8);
    }
}
