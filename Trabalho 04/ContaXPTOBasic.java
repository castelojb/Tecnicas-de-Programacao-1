class ContaXPTOBasic extends ContaXPTO{
   
    public ContaXPTOBasic(int id) {
        super(id);
    }

    public String toString() {
        return "\nTipo: Basic\nSaldo: R$ " + saldo + "\nId: " + id;
    }

    @Override
    public double creditar(double valor) {//Inclusao, pois inclui a implementação de conta
        this.saldo += valor;
        return saldo;
    }

    @Override
    public double debitar(double valor) {//Inclusao, pois inclui a implementação de conta
        this.saldo -= valor;
        return saldo;
    }
}
