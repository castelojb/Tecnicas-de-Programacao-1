class Diferenca extends Operador{
	
	Diferenca(Expressao esquerda, Expressao direita){
		super(esquerda,  direita);
	}
	
	double calcular() {
		retorno= esquerda.calcular() - direita.calcular();
		ob1.update(retorno);
		ob2.update(retorno);
		
		return retorno;
	}
}