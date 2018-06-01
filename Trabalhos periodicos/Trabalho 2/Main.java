class Main {
	public static void main(String[] args) {
		//Hariamy: Questão 3: Teste das classes criadas.
		System.out.println("############   QUESTÃO 3   ############\n");

		float x[] = {0, 0, 17, 17};
		float y[] = {0, 17, 17, 0};
		Quadrado q1 = new QuadradoColorido(x, y, 1);

		System.out.println("A área da " + q1 + " antes de mover os pontos é igual a: " + q1.calcularArea());
		q1.mover(5, 5);
		System.out.println("A área da " + q1 + " depois de mover os pontos é igual a: " + q1.calcularArea()+"\n");

		float x1[] = {0, 0, 2, 2};
		float y1[] = {0, 2, 2, 0};
		Quadrado q2 = new Quadrado(x1, y1);

		System.out.println("A área da " + q2 + " antes de mover os pontos é igual a: " + q2.calcularArea());
		q1.mover(5, 5);
		System.out.println("A área da " + q2 + " depois de mover os pontos é igual a: " + q2.calcularArea()+"\n");

		float cx[] = {0};
		float cy[] = {0};
		float raio = 9;
		Circulo c1 = new CirculoColorido(cx, cy, raio, 1);

		System.out.println("A área da " + c1 + " antes de mover os pontos é igual a: " + c1.calcularArea());
		q1.mover(5, 5);
		System.out.println("A área da " + c1 + " depois de mover os pontos é igual a: " + c1.calcularArea()+"\n");


		float cx1[] = {2};
		float cy1[] = {2};
		float raio1 = 3;
		Circulo c2 = new Circulo(cx1, cy1, raio1);

		System.out.println("A área da " + c2 + " antes de mover os pontos é igual a: " + c2.calcularArea());
		q1.mover(5, 5);
		System.out.println("A área da " + c2 + " depois de mover os pontos é igual a: " + c2.calcularArea()+"\n");



		//Hariamy: Questão 5: Print somente das figuras coloridas a partir de um vetor contendo 10 figuras.
		Figura figuras[] = new Figura[10];

		Quadrado q3 = new QuadradoColorido(x, y, 1);
		Quadrado q4 = new Quadrado(x, y);
		Quadrado q5 = new QuadradoColorido(x, y, 10);

		Circulo c3 = new CirculoColorido(cx, cy, raio, 20);
		Circulo c4 = new Circulo(cx, cy, raio);
		Circulo c5 = new CirculoColorido(cx, cy, raio, 30);

		figuras[0] = q1;
		figuras[1] = c1;
		figuras[2] = q2;
		figuras[3] = c2;
		figuras[4] = q3;
		figuras[5] = c3;
		figuras[6] = q4;
		figuras[7] = c4;
		figuras[8] = q5;
		figuras[9] = c5;

		System.out.println("\n############   QUESTÃO 5   ############\n");
		for (int i = 0; i < 10; i++){
			if (figuras[i] instanceof Colorida){
				System.out.println("A área da " + figuras[i] + " é igual a: " + figuras[i].calcularArea());
			}
		}


	}
}