
public class Circulo extends Figura {
	float raio;
	Circulo(float x[], float y[],float r){
		super (x,y);
		this.raio=r;
	}
	
	@Override
	public float calcularArea() {
		float S;
		S= (float) (3.14*this.raio*this.raio);
		return S;
	}
	@Override
	public String toString() {
		return "Circulo";
	}
	
}
