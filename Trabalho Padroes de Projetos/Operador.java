abstract class Operador extends Expressao{
	Expressao esquerda, direita;

	Operador(Expressao esquerda, Expressao direita){
		this.esquerda = esquerda;
		this.direita = direita;
		if (esquerda instanceof Operando) {
			this.armazena(esquerda);
			
		}
		if (direita  instanceof Operando) {
			this.armazena(direita);
			
		}
	}
	Operador(Expressao esquerda){
		this.esquerda = esquerda;
		
	}

}