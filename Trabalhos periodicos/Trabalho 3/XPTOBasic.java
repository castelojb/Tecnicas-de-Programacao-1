
public class XPTOBasic extends Conta implements Açoes {

	public XPTOBasic(int quem, float dinheiro) {
		super(quem,dinheiro);
	}

	@Override
	public void Debito(float quanto) {
		this.saldo= this.saldo - quanto;

	}

	@Override
	public void Credito(float quanto) {
		this.saldo= this.saldo + quanto;

	}

	@Override
	public float Consultar() {
		return this.saldo;
	}
	
	@Override
	public String toString() {
		return ("id: "+this.id+"\nSaldo: "+this.saldo+"\nTipo: XPTOBasic");
	}

}
