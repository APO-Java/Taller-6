package test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

public class ProductoAjustadoTest {
	
	@Test
	public void testPrecio() {
		ProductoAjustado prod = new ProductoAjustado(new ProductoMenu("Criolla", 15000));
		assertEquals(15000, prod.getPrecio());
		prod.agregarIngrediente(new Ingrediente("Tomate", 2000));
		prod.agregarIngrediente(new Ingrediente("Lechuga", 1000));
		assertEquals(18000, prod.getPrecio());
		prod.eliminarIngrediente(new Ingrediente("Tomate", 2000));
		assertEquals(16000, prod.getPrecio());
	}
	@Test
	public void testFactura() {
		ProductoAjustado prod = new ProductoAjustado(new ProductoMenu("Criolla", 15000));
		String factura = prod.generarTextoFactura();
		assertEquals("Criolla  $15000",factura);
		prod.agregarIngrediente(new Ingrediente("Tomate", 2000));
		prod.agregarIngrediente(new Ingrediente("Lechuga", 1000));
		prod.eliminarIngrediente(new Ingrediente("Rugula", 900));
		String texto = "Criolla" + " con Tomate, Lechuga;  sin Rugula " + " $18000";
		assertEquals(texto, prod.generarTextoFactura());
		
	}
}
