public class Circulo extends Figura{

	float raio;

	Circulo(float centroX[], float centroY[], float raio){
		super(centroX, centroY);
		this.raio = raio;
	}

	@Override
	public float calcularArea(){
		float area = (float)(3.141592 * this.raio * this.raio);
		return  area;
	}

	@Override
	public String toString() {
		return "Figura Circular";
	}
}