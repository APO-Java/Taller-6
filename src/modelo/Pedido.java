package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {
	//Atributos
	
	private static int numeroPedidos;
	private int idPedido;
	private boolean pedidoAbierto;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	//Metodos
	//Constructor
	
	public Pedido (String nombreCliente, String direccionCliente) 
	{
		numeroPedidos ++;
		this.idPedido = numeroPedidos;
		this.direccionCliente = direccionCliente;
		this.nombreCliente = nombreCliente;
		this.itemsPedido = new ArrayList<>();
		this.pedidoAbierto = true;
		
		
	}
	
	//Getters y Setters
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoltem) throws MaximoPrecioException {
		itemsPedido.add(nuevoltem);
		int precioActual = getPrecioNetoPedido();
		if(precioActual > 150000) {
			itemsPedido.remove(itemsPedido.size() - 1);
			throw new MaximoPrecioException();
			
		}
	}
	
	public void cerrarPedido()
	{
		pedidoAbierto = false;
	}
	
	public int getPrecioNetoPedido()
	{
		int precioNeto = 0; 
		for (int i=0; i < itemsPedido.size(); i++)
		{
			precioNeto += itemsPedido.get(i).getPrecio();
		}
		return precioNeto;
	}
	public int getPrecioIVAPedido()
	{
		return (int) (getPrecioNetoPedido() * 0.19);
	}
	public int getPrecioTotalPedido()
	{
		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}
	
	
	public ArrayList<Producto> getItemsPedido()
	{
		return itemsPedido;
	}
	// Otros
	
	public String generarTextoFactura() 
	{
		String factura = "=".repeat(20)+"\nidPedido: " + idPedido + 
				"\nDatos cliente:\nNombre: " + nombreCliente + "\nDireccion: " + direccionCliente+
				"\n"+"=".repeat(20) + "\nLos items en el pedido son:";
		for (int i = 0; i < itemsPedido.size(); i++) {
			factura += "\n--" + itemsPedido.get(i).generarTextoFactura();
		}
		factura +="\n"+"=".repeat(20)+"\nSubtotal: " + getPrecioNetoPedido() +
				"\nIVA (19%): " + getPrecioIVAPedido() + 
				"\n"+"=".repeat(20)+"\nTotal: " + getPrecioTotalPedido()+
				"\n";
		return factura;
	}
	
	public void guardarFactura(File archivo)
	{
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(archivo.getName()));
			writer.write(generarTextoFactura());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumeroPedidos() {
		return numeroPedidos;
	}
	public boolean getPedidoAbierto() {
		return pedidoAbierto;
	}
}
