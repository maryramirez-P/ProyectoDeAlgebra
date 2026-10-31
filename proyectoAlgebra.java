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

   // ==================== 2. GAUSS-JORDAN ====================

    static void gaussJordan() {
        System.out.println("\n==============================================");
        System.out.println("       REDUCCIÓN DE GAUSS-JORDAN");
        System.out.println("==============================================");

        int n = pedirEntero("Ingrese el tamaño n de la matriz: ");

        if (n <= 0) {
            System.out.println("El tamaño debe ser mayor que cero.");
            return;
        }

        double[][] matriz = new double[n][n];

        System.out.println("INGRESE LOS DATOS:");
        for (int i = 0; i < n; i++) {
            System.out.println("Ingrese los " + n + " numeros del renglon " + (i + 1) + ":");
            for (int j = 0; j < n; j++) {
                matriz[i][j] = pedirDouble("  Elemento [" + (i + 1) + "][" + (j + 1) + "]: ");
            }
        }

        for (int i = 0; i < n; i++) {
            double pivote = matriz[i][i];

            if (pivote == 0) {
                System.out.println("\nLa matriz no tiene solución (pivote cero en la posición " + (i + 1) + ").");
                return;
            }

            for (int j = 0; j < n; j++) {
                matriz[i][j] /= pivote;
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double numeroAEliminar = matriz[k][i];
                    for (int j = 0; j < n; j++) {
                        matriz[k][j] = matriz[k][j] - (numeroAEliminar * matriz[i][j]);
                    }
                }
            }
        }

        System.out.println("\nMATRIZ RESULTANTE (identidad):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double valor = matriz[i][j];
                if (valor == (int) valor) {
                    System.out.print((int) valor + " ");
                } else {
                    System.out.print(valor + " ");
                }
            }
            System.out.println();
        }
    }

   *// ==================== 3. FACTORIZACIÓN LU ====================*

    static void mostrarMatriz(String nombre, double[][] m) {
        System.out.println();
        System.out.println(nombre);
        for (int i = 0; i < 3; i++) {
            System.out.print("[ ");
            for (int j = 0; j < 3; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    static void factorizacionLU() {
        System.out.println("\n======================================");
        System.out.println("   FACTORIZACIÓN LU DE UNA MATRIZ 3x3");
        System.out.println("           MÉTODO DE DOOLITTLE");
        System.out.println("======================================");
        System.out.println();
        System.out.println("Ejemplos válidos: 5, 2.5, 3/4, -7/3");
        System.out.println();

        System.out.println("INGRESE LOS ELEMENTOS DE LA MATRIZ A");
        System.out.println("--------------------------------------");