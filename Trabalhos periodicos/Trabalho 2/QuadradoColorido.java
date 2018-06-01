public class QuadradoColorido extends Quadrado implements Colorida {
	int cor;

	QuadradoColorido(float x[], float y[], int coloracao){
		super(x, y);
		this.cor = coloracao;
	}

	@Override
	public void colorir(int cor){
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Figura Quadrada Colorida";
	}

}