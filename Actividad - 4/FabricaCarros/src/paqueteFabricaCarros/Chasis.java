package paqueteFabricaCarros;

public class Chasis {
	

private float peso;
private Material material;
public Chasis(float peso, Material material) {
	super();
	this.peso = peso;
	this.material = material;
}
public float getPeso() {
	return peso;
}
public void setPeso(float peso) {
	this.peso = peso;
}
public Material getMaterial() {
	return material;
}
public void setMaterial(Material material) {
	this.material = material;
}

@Override
public String toString() {
	return "Chasis [peso=" + peso + ", material=" + material + "]";
}



}