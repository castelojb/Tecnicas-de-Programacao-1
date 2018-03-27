class Main {
	public static void main(String[] args) {
		float x[] = {0,0,2,2};
		float y[] = {0,2,2,0};
		Quadrado q1 = new QuadradoColorido(x, y, 1);
		float X[]= {0,0,3,3};
		float Y[]= {0,3,3,0};
		Quadrado q2 = new Quadrado(X, Y);
		q1.calcularArea();

		System.out.println(q1);
		System.out.println(q2);
		System.out.println(""+q1.calcularArea());
		System.out.println(""+q2.calcularArea());
		
		q1.mover(5, 5);
		q2.mover(5, 5);
		
		System.out.println(""+q1.calcularArea());
		System.out.println(""+q2.calcularArea());
		float cx[]= {0};
		float cy[]= {0};
		Circulo c1= new Circulo(cx,cy,2);
		Circulo c2= new Circulo(cx,cy,3);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(""+c1.calcularArea());
		System.out.println(""+c2.calcularArea());
		
		c1.mover(5, 5);
		c2.mover(5, 5);
		
		System.out.println(""+c1.calcularArea());
		System.out.println(""+c2.calcularArea());
		
		
	}
}