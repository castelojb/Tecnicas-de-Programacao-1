class ContaXPTOPlus extends ContaXPTO{
    
    public ContaXPTOPlus(int id) {
        super(id);
    }
    
    public String toString() {
        return "\nTipo: Plus\nSaldo: R$ " + saldo + "\nId: " + id;
    }

    public double creditar(double valor) {//Inclusao, pois inclui a implementação de conta
        double div = 0.5 /100;
        double multi = div * valor;
        this.saldo = saldo + valor + multi;
        return saldo;
    }

    public double debitar(double valor) {//Inclusao, pois inclui a implementação de conta
        this.saldo -= valor;
        return saldo;
    }
}