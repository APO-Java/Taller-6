package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.ProductoMenu;

public class ProductoMenuTest {
	
	@Test
	public void testFactura() {
		ProductoMenu productoMenu = new ProductoMenu("Corral", 20000);
		String factura = productoMenu.generarTextoFactura();
		assertEquals("Corral $20000", factura);
    }
}
