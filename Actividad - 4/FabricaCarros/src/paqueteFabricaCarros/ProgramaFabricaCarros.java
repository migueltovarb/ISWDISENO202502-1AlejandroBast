package paqueteFabricaCarros;

import java.util.ArrayList;
import java.util.List;

public class ProgramaFabricaCarros {

	public static void main(String[] args) {
		
		Chasis chasisModelo=new Chasis(500f,Material.ACERO);
		Llanta llantaModelo=new Llanta(16f,"TodoTerreno");
		
		
		Planta miPlanta=new Planta("Planta pasto", llantaModelo, chasisModelo,Color.AZUL);
		
		
		List<Planta> listadoPlantas=new ArrayList<Planta>();
		listadoPlantas.add(miPlanta);
		
		Fabrica miFabrica=new Fabrica("Chevrolet",listadoPlantas);
		
		Carro miNuevoCarro=miPlanta.fabricar();
		
		System.out.println(miNuevoCarro);
	}

}