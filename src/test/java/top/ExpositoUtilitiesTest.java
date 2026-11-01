package top;

import static org.junit.jupiter.api.Assertions.*;
import es.ull.esit.utilities.ExpositoUtilities;
import org.junit.jupiter.api.Test;

class ExpositoUtilitiesTest {
    @Test void normalizaEspaciado() {
        assertEquals("uno dos tres", ExpositoUtilities.simplifyString("  uno   dos\ttres "));
    }
    @Test void multiplicacionMatricial() {
        double[][] c = ExpositoUtilities.multiplyMatrices(
            new double[][]{{1, 2}}, new double[][]{{3}, {4}});
        assertEquals(11.0, c[0][0], 0.00001);
    }
    @Test void matricesDeDimensionesIncompatibles() {
        assertNull(ExpositoUtilities.multiplyMatrices(
            new double[][]{{1, 2}}, new double[][]{{3, 4}}));
    }
}
