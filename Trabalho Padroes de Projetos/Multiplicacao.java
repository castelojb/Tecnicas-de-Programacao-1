class Multiplicacao extends Operador{
	Double calcular() {
		return esquerda.calcular() * direita.calcular();
	}
}