class Operando extends Expressao{
	float valor;

	Operando(float valor){
		this.valor = valor;
	}

	double calcular(){
		return valor;
	}


}