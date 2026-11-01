package top;

import java.util.Arrays;

/**
 * \brief Punto de entrada del recomendador turístico TOPTW.
 *
 * En modo normal recorre las 29 instancias originales. Para demostraciones
 * use --quick o --instance junto a --iterations.
 */
public final class MainTOPTW {
    private MainTOPTW() { }

    /**
     * \brief Ejecuta las experimentaciones con el algoritmo GRASP.
     * \param args --quick, --instance nombre.txt, --iterations N
     */
    public static void main(String[] args) {
        String[] instances = {
            "c101.txt", "c102.txt", "c103.txt", "c104.txt", "c105.txt", "c106.txt", "c107.txt", "c108.txt", "c109.txt",
            "r101.txt", "r102.txt", "r103.txt", "r104.txt", "r105.txt", "r106.txt", "r107.txt", "r108.txt", "r109.txt", "r110.txt", "r111.txt", "r112.txt",
            "rc101.txt", "rc102.txt", "rc103.txt", "rc104.txt", "rc105.txt", "rc106.txt", "rc107.txt", "rc108.txt"
        };
        int iterations = 10000;
        int[] rclSizes = {3, 5, 7};
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--quick":
                    instances = new String[]{"c101Recortada.txt"};
                    iterations = 1;
                    rclSizes = new int[]{3};
                    break;
                case "--instance":
                    if (++i >= args.length) throw new IllegalArgumentException("Falta nombre de instancia");
                    instances = new String[]{args[i]};
                    break;
                case "--iterations":
                    if (++i >= args.length) throw new IllegalArgumentException("Falta número de iteraciones");
                    iterations = Integer.parseInt(args[i]);
                    break;
                default: throw new IllegalArgumentException("Argumento desconocido: " + args[i]);
            }
        }
        if (iterations <= 0) throw new IllegalArgumentException("Las iteraciones deben ser positivas");
        for (String instance : instances) {
            TOPTW problem = TOPTWReader.readProblem("Instances/TOPTW/" + instance);
            TOPTWSolution solution = new TOPTWSolution(problem);
            TOPTWGRASP grasp = new TOPTWGRASP(solution);
            System.out.println(" --> Instancia: " + instance);
            for (int rcl : rclSizes) {
                System.out.println("RCL=" + rcl + ", iteraciones=" + iterations);
                grasp.GRASP(iterations, rcl);
            }
        }
    }
}
