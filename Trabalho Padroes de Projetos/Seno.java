class Seno extends Operador{

	Seno(Expressao valor){
		super(valor);
	}

	double calcular() {
		return Math.sin(Math.toRadians(esquerda.calcular()));
	}
}