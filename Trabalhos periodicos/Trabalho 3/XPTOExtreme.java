
public class XPTOExtreme extends Conta implements Açoes {

	public XPTOExtreme(int quem, float dinheiro) {
		super(quem,dinheiro);
	}

	@Override
	public void Debito(float quanto) {
		this.saldo= (float) (this.saldo - (quanto*0.998));

	}

	@Override
	public void Credito(float quanto) {
		this.saldo= (float) (this.saldo + (quanto*1.002));


	}

	@Override
	public float Consultar() {
		
		return this.saldo;
	}
	
	@Override
	public String toString() {
		return ("id: "+this.id+"\nSaldo: "+this.saldo+"\nTipo: XPTOExtreme");
	}

}
