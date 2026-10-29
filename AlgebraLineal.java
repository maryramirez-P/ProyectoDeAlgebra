import java.util.Scanner;

public class AlgebraLineal {

    static Scanner sc = new Scanner(System.in);



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
   
}

