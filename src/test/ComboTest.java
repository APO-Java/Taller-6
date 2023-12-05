package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Combo;
import modelo.ProductoMenu;

public class ComboTest {
	
	@Test
	public void TestPrecio() {
		Combo combo = new Combo("Corralita", 0.1);
		combo.agregarItemACombo(new ProductoMenu("Corral", 20000));
		assertEquals(18000, combo.getPrecio());
		combo.agregarItemACombo(new ProductoMenu("Agua", 2000));
		combo.agregarItemACombo(new ProductoMenu("Papas Medianas", 6000));
		assertEquals(25200, combo.getPrecio());
	}
	
	@Test
	public void TestFactura() {
		Combo combo = new Combo("Corralita", 0.1);
		combo.agregarItemACombo(new ProductoMenu("Corral", 20000));
		assertEquals("Corralita" + " $18000" + "\n Items: \nCorral", combo.generarTextoFactura());
		combo.agregarItemACombo(new ProductoMenu("Agua", 2000));
		combo.agregarItemACombo(new ProductoMenu("Papas Medianas", 6000));
		String texto = "Corralita" + " $25200" + "\n Items: " + "\nCorral\nAgua\nPapas Medianas";
		assertEquals(texto, combo.generarTextoFactura());
	}

}
