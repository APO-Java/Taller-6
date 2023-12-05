package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import modelo.MaximoPrecioException;
import modelo.Pedido;
import modelo.ProductoMenu;

public class PedidoTest {
	
	@Test 
	public void TestPrecioTotal() throws MaximoPrecioException{
		Pedido pedido = new Pedido("Leonardo", "Cra 59 22b 31");
		pedido.agregarProducto(new ProductoMenu("Corral", 20000));
		pedido.agregarProducto(new ProductoMenu("Agua", 3000));
		assertEquals(23000, pedido.getPrecioNetoPedido());
		assertEquals(4370, pedido.getPrecioIVAPedido());
		assertEquals(27370, pedido.getPrecioTotalPedido());
		pedido.agregarProducto(new ProductoMenu("Corral Cara", 120000));
		assertEquals(143000, pedido.getPrecioNetoPedido());
		assertThrows(MaximoPrecioException.class, () -> pedido.agregarProducto(new ProductoMenu("Fallo", 10000)));
		assertEquals(143000, pedido.getPrecioNetoPedido());
		
	}
	
	@Test
	public void TestCerrarPedido() {
		Pedido pedido = new Pedido("Leonardo", "Cra 59 22b 31");
		assertEquals(true, pedido.getPedidoAbierto());
		pedido.cerrarPedido();
		assertEquals(false, pedido.getPedidoAbierto());
		
	}
	
	@Test
	public void TestFactura() throws MaximoPrecioException {
		Pedido pedido = new Pedido("Leonardo", "Cra 59 22b 31");
		pedido.agregarProducto(new ProductoMenu("Corral", 20000));
		pedido.agregarProducto(new ProductoMenu("Agua", 3000));
		String factura = "=".repeat(20)+"\nidPedido: " + pedido.getIdPedido() + 
				"\nDatos cliente:\nNombre: " + "Leonardo" + "\nDireccion: " + "Cra 59 22b 31"+
				"\n"+"=".repeat(20) + "\nLos items en el pedido son:";
		factura += "\n--" + "Corral" + " $20000";
		factura += "\n--" + "Agua" + " $3000";
		
		factura +="\n"+"=".repeat(20)+"\nSubtotal: " + pedido.getPrecioNetoPedido() +
				"\nIVA (19%): " + pedido.getPrecioIVAPedido() + 
				"\n"+"=".repeat(20)+"\nTotal: " + pedido.getPrecioTotalPedido()+
				"\n";
		assertEquals(factura, pedido.generarTextoFactura());
		File file = new File("TestPedido");
		pedido.guardarFactura(file);
		assertTrue(file.exists());
	
	}
}
