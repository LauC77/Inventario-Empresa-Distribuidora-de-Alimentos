package inventariodistribuido;

public class Transaccion {
    // Atributos de la clase Transacción:
    private String tipo;           // Tipo de transacción: "Compra" o "Venta"
    private int idSucursal;        // ID de la sucursal donde se realiza la transacción
    private int idProducto;        // ID del producto que está siendo comprado o vendido
    private int cantidad;          // Cantidad de productos involucrados en la transacción
    private double precioUnitario; // Precio de cada unidad del producto en la transacción

    // Métodos Getters y Setters: Estos permiten acceder y modificar los valores de los atributos.

    // Obtiene el tipo de la transacción (Compra o Venta)
    public String getTipo() {
        return tipo;
    }

    // Establece el tipo de transacción (Compra o Venta)
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Obtiene el ID de la sucursal donde se realizó la transacción
    public int getIdSucursal() {
        return idSucursal;
    }

    // Establece el ID de la sucursal
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    // Obtiene el ID del producto relacionado con la transacción
    public int getIdProducto() {
        return idProducto;
    }

    // Establece el ID del producto relacionado con la transacción
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    // Obtiene la cantidad de productos involucrados en la transacción
    public int getCantidad() {
        return cantidad;
    }

    // Establece la cantidad de productos involucrados
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Obtiene el precio unitario del producto involucrado en la transacción
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    // Establece el precio unitario del producto involucrado en la transacción
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}

