
public abstract class Conta {
	float saldo;
	int id;
	

	public Conta(int quem, float dinheiro) {// sobrecarga
		this.saldo=dinheiro;
		this.id=quem;
		
	}
	
	
}
