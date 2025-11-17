package Circulo;

public class ProgramaCirculo {
	
	public static void main (String[] args) {
		
		Circulo miCirculo=new Circulo();
		double area=miCirculo.getArea();//inicial
		System.out.println("area: "+ area);
		miCirculo.setRadio(300);
		area=miCirculo.getArea();//despues de cambiar del radio
		
		System.out.println("area: "+ area);
		
		Circulo miSegundoCirculo=new Circulo(400);
		area=miSegundoCirculo.getArea();
		System.out.println("area: "+ area);
		
		double perimetro=miSegundoCirculo.getPerimetro();
		System.out.println("Perimetro:" + perimetro);
		
		System.out.println(miSegundoCirculo);
		}

}
