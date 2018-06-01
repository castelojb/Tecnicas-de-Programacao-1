
public class Observador_neg extends Observer {
	Observador_neg(){}
	void update(double valor) {
		if(valor<0) {
			System.out.println("Numero positivo");
		}
	}
}
