class Multiplicacao extends Operador{
	
	Multiplicacao(Expressao esquerda, Expressao direita){
		super(esquerda,  direita);
	}
	double calcular() {
		return esquerda.calcular() * direita.calcular();
	}
}