class Operando extends Expressao{
	float valor;

	Operando(float valor){
		this.valor = valor;
	}

	float calcular(){
		return valor;
	}


}