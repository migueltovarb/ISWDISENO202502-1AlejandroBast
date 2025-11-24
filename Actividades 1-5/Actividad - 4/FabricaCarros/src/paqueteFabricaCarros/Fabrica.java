package paqueteFabricaCarros;

import java.util.List;

public class Fabrica {
private String nombre;
private List<Planta> planta;
public Fabrica(String nombre, List<Planta> planta) {
	super();
	this.nombre = nombre;
	this.planta = planta;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public List<Planta> getPlanta() {
	return planta;
}
public void setPlanta(List<Planta> planta) {
	this.planta = planta;
}
@Override
public String toString() {
	return "Fabrica [nombre=" + nombre + ", planta=" + planta + "]";
}


}