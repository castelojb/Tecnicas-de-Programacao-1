class Main{
	public static void main(String[] args) {
		Expressao op1, op2, op3, op4, op5, op6, op10;
/*
		op1 = new Operando(2);
		op2 = new Diferenca();
		op3 = new Soma();
		op4 = new Operando(1);
		op5 = new Operando(3);
		op6 = new Divisao();

		op6.adicionar(op2, op3);
		op3.adicionar(op1, op2);
		op2.adicionar(op4, op5);
*/
		op10 = new Cosseno(new Operando(0));

		System.out.println(op10.calcular());
	}
}