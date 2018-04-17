abstract class Expressao{
	Expressao folha[] = new Expressao[1];
	int pos = 0;

	void armazena(Expressao op){
		if (pos == folha.length) {
			Expressao aux[] = new Expressao[folha.length + 10];
			for(int i = 0; i < pos; i++){//copia o conjunto para expancao
  				 aux[i] = this.folha[i];
			}

			folha = aux;
		}
		
		folha[pos] = op;
		pos++;
	}

	abstract double calcular();

}