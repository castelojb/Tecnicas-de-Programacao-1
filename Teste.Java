interface ContaXPTO{

    double consultarSaldo();

    double creditar(double i);

    double debitar(double i);

    String toString();

}
class ContaXPTOBasic implements ContaXPTO{
    double saldo;
    int id;

    public ContaXPTOBasic(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContaXPTOBasic{" +
                "saldo=" + saldo +
                ", Id=" + id +
                '}';
    }

    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    @Override
    public double creditar(double i) {
        this.saldo += i;
        return saldo;
    }

    @Override
    public double debitar(double i) {
        this.saldo -= i;
        return saldo;
    }
}

class ContaXPTOPlus implements ContaXPTO{
    double saldo;
    int id;

    public ContaXPTOPlus(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContaXPTOPlus{" +
                "saldo=" + saldo +
                ", Id=" + id +
                '}';
    }

    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    @Override
    public double creditar(double i) {
        double div = 0.5 /100;
        double multi = div * i;
        this.saldo = saldo + i + multi;
        return saldo;
    }

    @Override
    public double debitar(double i) {
        this.saldo -= i;
        return saldo;
    }
}

class ContaXPTOExtreme implements ContaXPTO{
    double saldo;
    int id;

    public ContaXPTOExtreme(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContaXPTOExtreme{" +
                "saldo=" + saldo +
                ", Id=" + id +
                '}';
    }

    @Override
    public double consultarSaldo() {
        return this.saldo;
    }

    @Override
    public double creditar(double i) {
        double div = 0.2 /100;
        double multi = div * i;
        this.saldo = saldo + i + multi;
        return saldo;
    }

    @Override
    public double debitar(double i) {
        double div = 0.2 /100;
        double multi = div * i;
        this.saldo = (saldo - i) + multi;
        return saldo;
    }
}



class Sistema  {

    ContaXPTO[] contas;

    public Sistema() {
        this.contas = new ContaXPTO[4];
    }

    public void criarContaXPTOBasic(int i){
        this.contas[i] = new ContaXPTOBasic(i);
    }

    public void criarContaXPTOPlus(int i){
        this.contas[i] = new ContaXPTOPlus(i);
    }

    public void criarContaXPTOExtreme(int i){
        this.contas[i] = new ContaXPTOExtreme(i);
    }

    public void creditar(int i,double c){
        this.contas[i].creditar(c);
    }

    public void debitar(int i,double c){
        this.contas[i].debitar(c);
    }

    public double consultarSaldo(int i){
        return this.contas[i].consultarSaldo();
    }



}

public class Teste{
    public static void main(String[] args) {
        Sistema s = new Sistema();
        s.criarContaXPTOBasic(1);
        s.criarContaXPTOPlus(2);
        s.criarContaXPTOExtreme(3);

        for (int i = 1; i <=3 ; i++) s.creditar(i,1000);

        for (int i = 1; i <=3 ; i++) s.debitar(i,10);

        System.out.println(s.consultarSaldo(1));
        System.out.println(s.consultarSaldo(2));
        System.out.println(s.consultarSaldo(3));

    }
}
