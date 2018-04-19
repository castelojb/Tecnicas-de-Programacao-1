class Divisao extends Operador{
	
	Divisao(Expressao esquerda, Expressao direita){
		super( esquerda,  direita);
	}
	
	double calcular() {
		if (direita.calcular() != 0){
			retorno= esquerda.calcular() / direita.calcular();
			ob1.update(retorno);
			ob2.update(retorno);
			return retorno;
		}
		return -999999999;//Caso a divisão seja por zero retorna um valor descrepante
	}
}