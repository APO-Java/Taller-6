package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProductoRepetidoException extends HamburguesaException{
	
	//Atributos
	private ArrayList<String> nombreMenu;

	public ProductoRepetidoException(ArrayList<String> nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	@Override
	void repetido() throws ElementoRepetidoException {
		Set<String> verificacion = new HashSet<>();
		for (String ing: nombreMenu) {
			if (!verificacion.add(ing)) {
                throw new ElementoRepetidoException("Existe un producto repetido: " + ing);
            }
		}
		
	}
	
}
