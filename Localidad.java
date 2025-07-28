class Localidad {
    int idLocalidad;
    double precioBoleto;
    int boletosDisponibles;
    int boletosVendidosAqui; // Para el reporte de caja

    public Localidad(int id, double precio, int disponibles) {
        this.idLocalidad = id;
        this.precioBoleto = precio;
        this.boletosDisponibles = disponibles;
        this.boletosVendidosAqui = 0; // Empieza en cero
    }

    // Método para vender boletos
    // Devuelve true si se vendió la cantidad solicitada, false si no hay suficiente
    public boolean vender(int cantidad) {
        if (boletosDisponibles >= cantidad) {
            boletosDisponibles -= cantidad;
            boletosVendidosAqui += cantidad;
            return true;
        } else {
            return false;
        }
    }

    // Método para ver si hay espacio para una cantidad específica
    public boolean hayEspacio(int cantidad) {
        return boletosDisponibles >= cantidad;
    }

    // Getters para acceder a los atributos
    public int getIdLocalidad() {
        return idLocalidad;
    }

    public double getPrecio() {
        return precioBoleto;
    }

    public int getDisponibles() {
        return boletosDisponibles;
    }

    public int getVendidosAqui() {
        return boletosVendidosAqui;
    }

    // Setter para ajustar boletos vendidos si se vende menos de lo pedido
    
    public void setBoletosVendidosAqui(int vendidos) {
        this.boletosVendidosAqui = vendidos;
    }
}
