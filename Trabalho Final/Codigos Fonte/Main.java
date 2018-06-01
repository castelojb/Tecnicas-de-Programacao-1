import view.Janela;
import controller.Controler;
import view.PainelInicial;

public class Main{
    public static void main(String args[]) {
        Janela janela = new Janela();
        janela.conteudoJanela(new PainelInicial(janela));
        janela.exibirJanela();
    }
}