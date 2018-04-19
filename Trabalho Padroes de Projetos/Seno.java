class Seno extends Operador{

	Seno(Expressao valor){
		super(valor);
	}

	double calcular() {
		retorno= Math.sin(Math.toRadians(esquerda.calcular()));
		
		ob1.update(retorno);
		ob2.update(retorno);
		
		return retorno;
	}
}