import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        SistemaBoletos miSistema = new SistemaBoletos(); 
        Comprador compradorActual = null; 

        int opcion; 
        do {
            System.out.println("\n--- Menú The Eras Tour ---");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir del programa");
            System.out.print("Por favor, elige una opción (1-6): ");

            
            opcion = scanner.nextInt(); 
            scanner.nextLine(); 

            
            switch (opcion) {
                case 1:
                    System.out.println("\n--- Creando un Nuevo Comprador ---");
                    System.out.print("Ingresa el nombre del comprador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingresa el email del comprador: ");
                    String email = scanner.nextLine();
                    System.out.print("¿Cuántos boletos desea comprar?: ");
                    int cant = scanner.nextInt();
                    System.out.print("¿Cuál es su presupuesto máximo para la compra?: ");
                    double presu = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea

                    
                    compradorActual = new Comprador(nombre, email, cant, presu);
                    System.out.println("¡Comprador " + compradorActual.getNombre() + " ha sido registrado y está activo!");
                    break;

                case 2:
                    
                    if (compradorActual == null) {
                        System.out.println("¡Atención! Primero debes crear un comprador (Opción 1) antes de solicitar boletos.");
                    } else {
                        miSistema.procesarCompra(compradorActual); 
                    }
                    break;

                case 3:
                    miSistema.consultarDisponibilidadTotal(); 
                    break;

                case 4:
                    System.out.print("Ingresa el ID de la localidad que quieres consultar (1, 5 o 10): ");
                    int idLoc = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    miSistema.consultarDisponibilidadIndividual(idLoc); 
                    break;

                case 5:
                    miSistema.generarReporteCaja(); 
                    break;

                case 6:
                    System.out.println("Saliendo del programa. ¡Esperamos verte en el próximo concierto de Taylor Swift!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingresa un número del 1 al 6.");
            }
        } while (opcion != 6); 

        scanner.close();
    }
}