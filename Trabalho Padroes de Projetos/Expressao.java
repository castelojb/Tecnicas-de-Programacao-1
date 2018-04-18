abstract class Expressao{
	Operando folha[] = new Operando[1];
	int pos = 0;

	void armazena(Operando op){
		if (pos == folha.length) {
			Operando aux[] = new Operando[folha.length + 10];
			for(int i = 0; i < pos; i++){//copia o conjunto para expancao
  				 aux[i] = this.folha[i];
			}

			folha = aux;
		}
		
		folha[pos] = op;
		pos++;
	}
	
	void leafUpdate(int pos, float valor) {
		folha[pos].valor= valor;
	}

	abstract double calcular();

}