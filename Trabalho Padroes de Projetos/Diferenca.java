class Diferenca extends Operador{
	int calcular() {
		return esquerda.calcular() - direita.calcular();
	}
}