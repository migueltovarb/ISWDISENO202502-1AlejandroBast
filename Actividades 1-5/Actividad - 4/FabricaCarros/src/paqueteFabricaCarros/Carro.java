package paqueteFabricaCarros;

import java.util.List;

public class Carro {
private List<Llanta> llantas;
private Chasis chasis;
private Color color;
public Carro(List<Llanta> llantas, Chasis chasis, Color color) {
	super();
	this.llantas = llantas;
	this.chasis = chasis;
	this.color = color;
}
public List<Llanta> getLlantas() {
	return llantas;
}
public void setLlantas(List<Llanta> llantas) {
	this.llantas = llantas;
}
public Chasis getChasis() {
	return chasis;
}
public void setChasis(Chasis chasis) {
	this.chasis = chasis;
}
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}

	@Override
	public String toString() {
		return "Carro [llantas=" + llantas + ", chasis=" + chasis + ", color=" + color + "]";
	}
}