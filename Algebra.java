import java.util.Scanner;

public class AlgebraLineal {

    static Scanner sc = new Scanner(System.in);

    // ==================== MÉTODOS AUXILIARES ====================

    static double pedirNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                if (texto.contains("/")) {
                    String[] partes = texto.split("/");
                    double arriba = Double.parseDouble(partes[0].trim());
                    double abajo  = Double.parseDouble(partes[1].trim());
                    if (abajo == 0) {
                        System.out.println("El denominador no puede ser cero.");
                        continue;
                    }
                    return arriba / abajo;
                } else {
                    return Double.parseDouble(texto);
                }
            } catch (Exception e) {
                System.out.println("Dato incorrecto. Ejemplos: 5, 2.5, 3/4");
            }
        }
    }

    static double pedirDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Dato incorrecto. Ingrese un número válido.");
            }
        }
    }

    static int pedirEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Dato incorrecto. Ingrese un entero válido.");
            }
        }
    }

    // ==================== MENÚ PRINCIPAL ====================

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n==============================================");
            System.out.println("       ÁLGEBRA LINEAL - MENÚ PRINCIPAL");
            System.out.println("==============================================");
            System.out.println("1. Representación matricial de una transformación lineal T: R² → R³");
            System.out.println("2. Reducción de Gauss-Jordan (matriz n x n)");
            System.out.println("3. Factorización LU de una matriz 3x3 (Doolittle)");
            System.out.println("0. Salir");
            System.out.println("==============================================");
            opcion = pedirEntero("Seleccione una temática: ");

            switch (opcion) {
                case 1:
                    representacionMatricial();
                    break;
                case 2:
                    gaussJordan();
                    break;
                case 3:
                    factorizacionLU();
                    break;
                case 0:
                    System.out.println("\nSaliendo del programa... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ==================== 1. REPRESENTACIÓN MATRICIAL ====================

    static void representacionMatricial() {
        System.out.println("\n==============================================");
        System.out.println(" REPRESENTACIÓN MATRICIAL");
        System.out.println(" TRANSFORMACIÓN LINEAL T: R² → R³");
        System.out.println("==============================================");

        System.out.println("\nIngrese los coeficientes de la transformación:");
        System.out.println("T(x, y) = (a1*x + b1*y, a2*x + b2*y, a3*x + b3*y)");

        System.out.print("\nCoeficiente de x en la primera componente: ");
        double a1 = pedirDouble("");

        System.out.print("Coeficiente de y en la primera componente: ");
        double b1 = pedirDouble("");

        System.out.print("\nCoeficiente de x en la segunda componente: ");
        double a2 = pedirDouble("");

        System.out.print("Coeficiente de y en la segunda componente: ");
        double b2 = pedirDouble("");

        System.out.print("\nCoeficiente de x en la tercera componente: ");
        double a3 = pedirDouble("");

        System.out.print("Coeficiente de y en la tercera componente: ");
        double b3 = pedirDouble("");

        double[][] matriz = {
                {a1, b1},
                {a2, b2},
                {a3, b3}
        };

        System.out.println("\n==============================================");
        System.out.println("TRANSFORMACIÓN LINEAL INGRESADA");
        System.out.println("==============================================");

        System.out.printf(
                "T(x, y) = (%.2fx + %.2fy, %.2fx + %.2fy, %.2fx + %.2fy)%n",
                a1, b1, a2, b2, a3, b3
        );

        System.out.println("\n==============================================");
        System.out.println("MATRIZ DE LA TRANSFORMACIÓN");
        System.out.println("==============================================");

        for (double[] fila : matriz) {
            System.out.print("[ ");
            for (double elemento : fila) {
                System.out.printf("%8.2f ", elemento);
            }
            System.out.println("]");
        }

        System.out.println("\nDimensión de la matriz: 3 x 2");
        System.out.println("Dominio: R²");
        System.out.println("Codominio: R³");
        System.out.println("\nColumnas → componentes de R² (x, y)");
        System.out.println("Filas    → componentes de R³");
    }
