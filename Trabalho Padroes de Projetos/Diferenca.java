class Diferenca extends Operador{
	
	Diferenca(Expressao esquerda, Expressao direita){
		super(esquerda,  direita);
	}
	
	double calcular() {
		return esquerda.calcular() - direita.calcular();
	}
}