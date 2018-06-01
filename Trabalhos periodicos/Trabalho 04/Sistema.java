class Sistema  {

    ContaXPTO[] contas;

    public Sistema() {
        this.contas = new ContaXPTO[10];
    }

    public void criarContaXPTOBasic(int id){
        this.contas[id] = new ContaXPTOBasic(id);
    }

    public void criarContaXPTOPlus(int id){
        this.contas[id] = new ContaXPTOPlus(id);
    }

    public void criarContaXPTOExtreme(int id){
        this.contas[id] = new ContaXPTOExtreme(id);
    }

    public void creditar(int id, double valor){//Sobrecarga, pois em conta há o método creditar recebendo apenas o valor
        this.contas[id].creditar(valor);
    }

    public void debitar(int id, double valor){//Sobrecarga, pois em conta há o método debitar recebendo apenas o valor
        this.contas[id].debitar(valor);
    }

    public double consultarSaldo(int id){
        return this.contas[id].consultarSaldo();
    }



}