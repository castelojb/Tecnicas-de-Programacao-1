
public class Sistema {
	Conta conta[];
	int serie;
	public Sistema() {
		this.conta= new Conta[10];
		this.serie=0;
	}
	
	boolean setElemento(Conta novo,int indice) {
		if(indice<0) {
			return false;
		}
		if(this.conta[indice] != null) {
			return false;
		}
		if(indice>=this.conta.length) {
			Conta expancao[]=new Conta[this.conta.length+10];
			System.arraycopy(this.conta, 0, expancao, 0, this.conta.length);
			this.conta=expancao;
			return this.setElemento(novo, indice);
		}
		this.conta[indice]=novo;
		return true;
	}
	
	boolean consultarExistencia(int indice) {
		if(indice<0) {
			return false;
		}
		if(indice>this.conta.length-1) {
			return false;
		}
		if(this.conta[indice]!=null) {
			return false;
		}
		return true;
	}
	
	
	boolean criarContaXPTOBasic(int indice) {

			Conta nova= new XPTOBasic(this.serie,0);
			this.serie++;
			return this.setElemento(nova, indice);
	}
	boolean criarContaXPTOPlus(int indice) {
		Conta nova= new XPTOPlus(this.serie,0);
		this.serie++;
		return this.setElemento(nova, indice);
	}
	boolean criarContaXPTOExtreme(int indice) {
		Conta nova= new XPTOExtreme(this.serie,0);
		this.serie++;
		return this.setElemento(nova, indice);
	}
	boolean creditar(int indice, float valor) {
		if(this.consultarExistencia(indice)) {
			return false;
		}
		((Açoes) this.conta[indice]).Credito(valor);
		return true;
	}
	
	boolean debitar(int indice, float valor) {
		if(this.consultarExistencia(indice)) {
			return false;
		}
		((Açoes) this.conta[indice]).Debito(valor);
		return true;
	}

	float consultarSaldo(int indice) {
		if(this.consultarExistencia(indice)== true) {
			return -9999999;
		}
		return ((Açoes) this.conta[indice]).Consultar();
		
	}
}
