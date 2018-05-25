

public class Main {

	public static void main(String[] args) {
		Controler c1= new Controler();
		c1.setArq(args[0]);
		
		System.out.println(""+c1.getTituloColuna(4,0)+" "+c1.media(4,0));
		
		


	}

}
