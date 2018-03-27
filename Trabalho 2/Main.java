class Main {
	public static void main(String[] args) {
		float x[] = {1, 1};
		float y[] = {2, 2};
		Quadrado q1 = new QuadradoColorido(x, y, 1);
		Quadrado q2 = new Quadrado(x, y);

		System.out.println(q1);
	}
}