abstract class ContaXPTO{
	double saldo;
    int id;

    public ContaXPTO(int id) {
        this.id = id;
        this.saldo = 0;
    }

    public double consultarSaldo() {
        return this.saldo;
    }

    abstract double creditar(double valor);

    abstract double debitar(double valor);

}