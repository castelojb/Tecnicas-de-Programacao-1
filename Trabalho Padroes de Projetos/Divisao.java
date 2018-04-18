class Divisao extends Operador{
	
	Divisao(Expressao esquerda, Expressao direita){
		super( esquerda,  direita);
	}
	
	double calcular() {
		if (direita.calcular() != 0){
			return esquerda.calcular() / direita.calcular();
		}
		return -999999999;//Caso a divisão seja por zero retorna um valor descrepante
	}
}