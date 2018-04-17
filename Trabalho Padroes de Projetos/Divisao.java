class Divisao extends Operador{
	int calcular() {
	Divisao(Expressao esquerda, Expressao direita){
		Super( esquerda,  direita);
	}
		if (direita.calcular() != 0){
			return esquerda.calcular() * direita.calcular();
		}
		return -999999999;//Caso a divisão seja por zero retorna um valor descrepante
	}
}