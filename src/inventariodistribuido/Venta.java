package inventariodistribuido;

public class Venta extends Transaccion {
    // Atributos específicos de la clase Venta
    private int idProducto;        // ID del producto que está siendo vendido
    private int cantidad;          // Cantidad de productos vendidos
    private double precioUnitario; // Precio por unidad del producto vendido
    
    // Constructor: inicializa los valores de los atributos de la clase Venta
    public Venta(String tipo, int idSucursal, int idProducto, int cantidad, double precioUnitario) {
        super(); // Llama al constructor de la clase Transaccion (hereda los atributos comunes)
        
        // Establece los valores para los atributos heredados de la clase Transaccion
        this.setTipo(tipo);         // Asigna el tipo de transacción (Compra o Venta)
        this.setIdSucursal(idSucursal); // Asigna el ID de la sucursal
        
        // Establece los valores específicos de la venta
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Métodos Getters y Setters para acceder y modificar los valores de los atributos
    
    // Obtiene el ID del producto involucrado en la venta
    @Override
    public int getIdProducto() {
        return idProducto;
    }

    // Establece el ID del producto involucrado en la venta
    @Override
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    // Obtiene la cantidad de productos vendidos
    @Override
    public int getCantidad() {
        return cantidad;
    }

    // Establece la cantidad de productos vendidos
    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Obtiene el precio unitario del producto vendido
    @Override
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    // Establece el precio unitario del producto vendido
    @Override
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}

