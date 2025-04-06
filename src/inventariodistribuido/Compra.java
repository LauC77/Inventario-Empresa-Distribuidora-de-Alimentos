package inventariodistribuido;

public class Compra extends Transaccion {
    // Atributos específicos para una compra
    private int idProducto; // ID del producto que se compra
    private int cantidad;   // Cantidad de productos comprados
    private double precioUnitario; // Precio por unidad del producto comprado

    // Constructor: Inicializa los atributos de la clase Compra
    public Compra(int idProducto, int cantidad, double precioUnitario) {
        // Llamada al constructor de la clase padre (Transaccion)
        super(); 
        this.idProducto = idProducto; // Establece el ID del producto que se compra
        this.cantidad = cantidad;     // Establece la cantidad de productos comprados
        this.precioUnitario = precioUnitario; // Establece el precio por unidad del producto
    }

    // Getter y Setter para el id del producto
    @Override
    public int getIdProducto() {
        return idProducto; // Devuelve el ID del producto
    }

    @Override
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto; // Establece el ID del producto
    }

    // Getter y Setter para la cantidad de productos
    @Override
    public int getCantidad() {
        return cantidad; // Devuelve la cantidad de productos comprados
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad; // Establece la cantidad de productos comprados
    }

    // Getter y Setter para el precio unitario del producto
    @Override
    public double getPrecioUnitario() {
        return precioUnitario; // Devuelve el precio por unidad del producto
    }

    @Override
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario; // Establece el precio por unidad del producto
    }

    // Método para calcular el costo total de la compra (precioUnitario * cantidad)
    public double calcularTotal() {
        return precioUnitario * cantidad; // Calcula el costo total multiplicando el precio unitario por la cantidad
    }
}
