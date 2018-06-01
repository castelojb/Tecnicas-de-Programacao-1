class ContaXPTOExtreme extends ContaXPTO{
    
    public ContaXPTOExtreme(int id) {
        super(id);
    }
    
    public String toString() {
        return "\nTipo: Extreme\nSaldo: R$ " + saldo + "\nId: " + id;
    }

    @Override
    public double creditar(double valor) {//Inclusao, pois inclui a implementação de conta
        double div = 0.2 /100;
        double multi = div * valor;
        this.saldo = saldo + valor + multi;
        return saldo;
    }

    @Override
    public double debitar(double valor) {//Inclusao, pois inclui a implementação de conta
        double div = 0.2 /100;
        double multi = div * valor;
        this.saldo = (saldo - valor) + multi;
        return saldo;
    }
}