class Comprador {
    String nombre;
    String email;
    int cantidadBoletos;
    double presupuestoMaximo;

    // Constructor simple
    public Comprador(String nom, String mail, int cant, double presu) {
        this.nombre = nom;
        this.email = mail;
        this.cantidadBoletos = cant;
        this.presupuestoMaximo = presu;
    }

    // Método para mostrar info del comprador
    public void mostrarInfo() {
        System.out.println("--- Info Comprador ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Boletos deseados: " + cantidadBoletos);
        System.out.println("Presupuesto: $" + presupuestoMaximo);
    }

    // Getters para acceder a los datos 
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }
}
