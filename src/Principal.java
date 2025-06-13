import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultarMonedas conversor = new ConsultarMonedas();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nElige una opción válida del conversor de monedas:");
            System.out.println("(1) Dólar ==> Peso Argentino");
            System.out.println("(2) Peso Argentino ==> Dólar");
            System.out.println("(3) Real Brasileño ==> Dólar");
            System.out.println("(4) Dólar ==> Real Brasileño");
            System.out.println("(5) Dólar ==> Peso Colombiano");
            System.out.println("(6) Peso Colombiano ==> Dólar");
            System.out.println("(7) Salir");

            System.out.print("Opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 7) {
                continuar = false;
                System.out.println("Gracias por usar el conversor.");
                break;
            }

            String base = "", destino = "";

            switch (opcion) {
                case 1 -> { base = "USD"; destino = "ARS"; }
                case 2 -> { base = "ARS"; destino = "USD"; }
                case 3 -> { base = "BRL"; destino = "USD"; }
                case 4 -> { base = "USD"; destino = "BRL"; }
                case 5 -> { base = "USD"; destino = "COP"; }
                case 6 -> { base = "COP"; destino = "USD"; }
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            try {
                Monedas datos = conversor.busquedaMonedas(base);
                Double tasa = datos.conversion_rates().get(destino);
                GeneradorDeArchivo generador = new GeneradorDeArchivo();
                generador.guardarJson(datos);

                if (tasa != null) {
                    double resultado = cantidad * tasa;
                    System.out.printf("Resultado: %.2f %s = %.2f %s\n", cantidad, base, resultado, destino);
                } else {
                    System.out.println("No se encontró la tasa de cambio.");
                }

            }

            catch (Exception e) {
                System.out.println("Error al obtener datos: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
