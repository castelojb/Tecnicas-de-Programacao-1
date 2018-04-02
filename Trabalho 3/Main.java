
public class Main {

	public static void main(String[] args) {
		Sistema s = new Sistema();
		s.criarContaXPTOBasic(1);
		s.criarContaXPTOPlus(2);
		s.criarContaXPTOExtreme(3);
		for(int i = 1; i<=3; i++) {
			s.creditar(i,1000);
		}
		for(int i = 1; i<=3; i++) {
			s.debitar(i,10);
		}
		//todas as igualdades devem ser verdadeiras
		System.out.println(""+s.consultarSaldo(1));
		System.out.println(""+s.consultarSaldo(2));
		System.out.println(""+s.consultarSaldo(3));
		
			
	}

}
