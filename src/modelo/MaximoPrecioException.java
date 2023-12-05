package modelo;

@SuppressWarnings("serial")
public class MaximoPrecioException extends Exception {
	public MaximoPrecioException() {
        super("No se pueden añadir más items. Se supera el límite de precio del pedido,\n el cual es 150000");
    }
}
