package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class IngredienteRepetidoException extends HamburguesaException{
	
	//Atributos 
	private ArrayList<String> nombreIngredientes;
	
	public IngredienteRepetidoException(ArrayList<String> nombreIngredientes) {
		this.nombreIngredientes = nombreIngredientes;
	}
	@Override
	void repetido() throws ElementoRepetidoException{
		Set<String> verificacion = new HashSet<>();
		for (String ing: nombreIngredientes) {
			if (!verificacion.add(ing)) {
                throw new ElementoRepetidoException("Existe un ingrediente repetido: " + ing);
            }
		}
		
		
	}


}
