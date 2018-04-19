
public class Observador_pos extends Observer {
	
	Observador_pos(){}
	void update(double valor) {
		if(valor>0) {
			System.out.println("Numero positivo");
		}
	}
}
