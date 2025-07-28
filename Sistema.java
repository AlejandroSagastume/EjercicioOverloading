import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SistemaBoletos {
    List<Localidad> localidades;
    Random random;

    public SistemaBoletos() {
        localidades = new ArrayList<>();
        // Inicializar las 3 localidades con 20 boletos cada una
        localidades.add(new Localidad(1, 100.0, 20)); // Localidad 1: $100
        localidades.add(new Localidad(5, 500.0, 20)); // Localidad 5: $500
        localidades.add(new Localidad(10, 1000.0, 20)); // Localidad 10: $1000
        random = new Random();
    }

    // Método para procesar la compra de un comprador
    public void procesarCompra(Comprador comprador) {
        System.out.println("\n--- Procesando solicitud de " + comprador.getNombre() + " ---");

        // Paso 1: Generar y validar ticket
        Ticket miTicket = new Ticket();
        System.out.println("Tu número de ticket es: " + miTicket.getNumero());

        // Generar los dos números aleatorios para la validación
        int numA = random.nextInt(15000) + 1;
        int numB = random.nextInt(15000) + 1;
        miTicket.validarTicket(numA, numB); // Validar el ticket

        System.out.println("Validando ticket con rango (" + Math.min(numA, numB) + " - " + Math.max(numA, numB) + ")");

        if (!miTicket.getEsValido()) {
            System.out.println("Lo sentimos, tu ticket no es apto para comprar boletos esta vez.");
            return; // Terminar el proceso para este comprador
        }
        System.out.println("¡Felicidades! Tu ticket es apto para comprar boletos.");

        // Paso 2: Asignar localidad aleatoria
        int indiceLocalidad = random.nextInt(localidades.size()); // Elige 0, 1 o 2
        Localidad localidadAsignada = localidades.get(indiceLocalidad);
        System.out.println("Se te ha asignado la Localidad " + localidadAsignada.getIdLocalidad() + ".");

        // Paso 3: Realizar validaciones de compra
        // Validar espacio total en la localidad (si ya no quedan boletos)
        if (localidadAsignada.getDisponibles() == 0) {
            System.out.println("Lo sentimos, la Localidad " + localidadAsignada.getIdLocalidad() + " ya no tiene espacio.");
            return;
        }

        // Validar disponibilidad de boletos deseados
        int boletosAComprar = comprador.getCantidadBoletos();
        if (!localidadAsignada.hayEspacio(boletosAComprar)) {
            System.out.println("No hay suficientes boletos disponibles en la Localidad " + localidadAsignada.getIdLocalidad() + " para tu solicitud.");
            System.out.println("Solo podemos ofrecerte " + localidadAsignada.getDisponibles() + " boletos.");
            boletosAComprar = localidadAsignada.getDisponibles(); // Vender lo que queda
        }

        // Validar presupuesto del comprador
        double costoTotal = boletosAComprar * localidadAsignada.getPrecio();
        if (costoTotal > comprador.getPresupuestoMaximo()) {
            System.out.println("Tu presupuesto de $" + comprador.getPresupuestoMaximo() + " no es suficiente para comprar " + boletosAComprar + " boletos en la Localidad " + localidadAsignada.getIdLocalidad() + " (Costo: $" + costoTotal + ").");
            System.out.println("Proceso de compra terminado para este comprador.");
            return;
        }

        // Si todas las validaciones pasan, se procede a la venta
        if (localidadAsignada.vender(boletosAComprar)) {
            System.out.println("¡Compra exitosa! Has comprado " + boletosAComprar + " boletos en la Localidad " + localidadAsignada.getIdLocalidad() + " por $" + costoTotal + ".");
        } else {
            // Esto debería ser raro si las validaciones previas funcionaron bien
            System.out.println("Ocurrió un error inesperado al intentar vender los boletos. Intenta de nuevo.");
        }
    }

    // Método para consultar la disponibilidad de boletos en todas las localidades
    public void consultarDisponibilidadTotal() {
        System.out.println("\n--- Disponibilidad Total ---");
        int totalDisponiblesGlobal = 0;
        for (Localidad loc : localidades) {
            System.out.println("Localidad " + loc.getIdLocalidad() + " (Precio: $" + loc.getPrecio() + "):");
            System.out.println("  Boletos Vendidos: " + loc.getVendidosAqui());
            System.out.println("  Boletos Disponibles: " + loc.getDisponibles());
            totalDisponiblesGlobal += loc.getDisponibles();
        }
        System.out.println("Total de boletos disponibles en todas las localidades: " + totalDisponiblesGlobal);
    }

    // Método para consultar la disponibilidad de una localidad específica
    public void consultarDisponibilidadIndividual(int idBuscado) {
        System.out.println("\n--- Disponibilidad Individual ---");
        boolean encontrada = false;
        for (Localidad loc : localidades) {
            if (loc.getIdLocalidad() == idBuscado) {
                System.out.println("Localidad " + loc.getIdLocalidad() + " (Precio: $" + loc.getPrecio() + "):");
                System.out.println("  Boletos Vendidos: " + loc.getVendidosAqui());
                System.out.println("  Boletos Disponibles: " + loc.getDisponibles());
                encontrada = true;
                break; // Salir del bucle una vez encontrada
            }
        }
        if (!encontrada) {
            System.out.println("Localidad con ID " + idBuscado + " no encontrada. IDs válidos: 1, 5, 10.");
        }
    }

    // Método para generar el reporte de ingresos (caja)
    public void generarReporteCaja() {
        System.out.println("\n--- Reporte de Caja ---");
        double ingresosTotales = 0;
        for (Localidad loc : localidades) {
            double ingresosLocalidad = loc.getVendidosAqui() * loc.getPrecio();
            System.out.println("Ingresos Localidad " + loc.getIdLocalidad() + ": $" + ingresosLocalidad);
            ingresosTotales += ingresosLocalidad;
        }
        System.out.println("Ingresos totales generados por todas las ventas: $" + ingresosTotales);
    }
}
