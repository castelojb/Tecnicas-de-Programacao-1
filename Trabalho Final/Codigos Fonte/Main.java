import view.janela.Janela;
import view.janela.PainelInicial;

public class Main{
    public static void main(String args[]) {
        Janela janela = new Janela();
        janela.conteudoJanela(new PainelInicial(janela));
        janela.exibirJanela();
    }
}