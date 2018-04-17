class Soma extends Operador{
	
	Soma(Expressao esquerda, Expressao direita){
		super(esquerda,  direita);
	}
	double calcular() {
		return esquerda.calcular() + direita.calcular();
	}
}