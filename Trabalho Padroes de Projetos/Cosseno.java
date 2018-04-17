class Cosseno extends Operador{

	Cosseno(Expressao valor){
		super(valor);
	}

	double calcular() {
		return Math.cos(Math.toRadians(esquerda.calcular()));
	}
}