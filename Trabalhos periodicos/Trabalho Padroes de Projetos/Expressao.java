abstract class Expressao{
	Operando folha[] = new Operando[1];
	int pos = 0;
	double retorno;
	Observer ob1=new Observador_neg();
	Observer ob2=new Observador_pos();

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