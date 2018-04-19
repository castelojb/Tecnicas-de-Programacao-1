class Cosseno extends Operador{

	Cosseno(Expressao valor){
		super(valor);
	}

	double calcular() {
		retorno= Math.cos(Math.toRadians(esquerda.calcular()));
		ob1.update(retorno);
		ob2.update(retorno);
		return retorno;
	}
}