public class Quadrado extends Figura {

	
	int cor;

	Quadrado(float x[], float y[]) {
		super(x, y);
	}

	public void desenhar(int coloracao) {
		this.cor=coloracao;
	}

	@Override
	public String toString() {
		return "Figura quadrada";
	}
}