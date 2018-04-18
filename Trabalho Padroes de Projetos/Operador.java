abstract class Operador extends Expressao{
	Expressao esquerda, direita;

	Operador(Expressao esquerda, Expressao direita){
		this.esquerda = esquerda;
		this.direita = direita;
		if (esquerda instanceof Operando) {
			this.armazena((Operando)esquerda);
			
		}
		if (direita  instanceof Operando) {
			this.armazena((Operando)direita);
			
		}
	}
	Operador(Expressao esquerda){
		this.esquerda=esquerda;
		
		if(esquerda instanceof Operando) {
			this.armazena((Operando)esquerda);
		}
		
	}

}