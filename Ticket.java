import java.util.Random;

class Ticket {
    int numeroTicket;
    boolean esValido; // Si pasó la validación de rango

    public Ticket() {
        Random rand = new Random();
        this.numeroTicket = rand.nextInt(15000) + 1; // Número entre 1 y 15000
        this.esValido = false; // Por defecto no es válido hasta que se valide
    }

    // Método para validar el ticket con dos números aleatorios (a y b)
    public void validarTicket(int a, int b) {
        // Asegurarse que 'min' sea el menor y 'max' el mayor para el rango
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        if (numeroTicket >= min && numeroTicket <= max) {
            esValido = true;
        } else {
            esValido = false;
        }
    }

    // Getter para saber si el ticket es válido
    public boolean getEsValido() {
        return esValido;
    }

    // Getter para obtener el número del ticket
    public int getNumero() {
        return numeroTicket;
    }
}
