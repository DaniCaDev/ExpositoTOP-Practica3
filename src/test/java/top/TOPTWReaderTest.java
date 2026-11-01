package top;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/** Pruebas unitarias de importación y distancias del problema TOPTW. */
class TOPTWReaderTest {
    @Test void cargaInstanciaIncluidaEnElJar() {
        TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        assertEquals(20, problem.getPOIs());
        assertEquals(3, problem.getVehicles());
        assertEquals(40.0, problem.getX(0), 0.00001);
        assertEquals(50.0, problem.getY(0), 0.00001);
    }
    @Test void matrizEsSimetrica() {
        TOPTW p = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        assertEquals(p.getDistance(1, 2), p.getDistance(2, 1), 0.00001);
        assertEquals(0.0, p.getDistance(0, 0), 0.00001);
    }
    @Test void archivoInexistenteNoCierraJvm() {
        assertThrows(IllegalArgumentException.class,
                () -> TOPTWReader.readProblem("no-existe.txt"));
    }
    @Test void tiempoMaximoSeDerivaDelDeposito() {
        TOPTW p = TOPTWReader.readProblem("Instances/TOPTW/c101Recortada.txt");
        assertEquals(p.getDueTime(0), p.getMaxTimePerRoute(), 0.00001);
    }
}
