
public class XPTOPlus extends Conta implements Açoes {

	public XPTOPlus(int quem, float dinheiro) {
		super(quem,dinheiro);
	}

	@Override
	public void Debito(float quanto) {
		this.saldo= this.saldo-quanto;

	}

	@Override
	public void Credito(float quanto) {
		this.saldo= (float) (this.saldo + (quanto*1.005));

	}

	@Override
	public float Consultar() {
		return this.saldo;
	}
	
	@Override
	public String toString() {
		return ("id: "+this.id+"\nSaldo: "+this.saldo+"\nTipo: XPTOPlus");
	}

}
