import view.janela.Janela;
import view.janela.PainelInicial;

public class Main{
    public static void main(String args[]) {
        System.setProperty("file.encoding", "UTF-8");
        Janela janela = new Janela();
        janela.conteudoJanela(new PainelInicial(janela));
        janela.exibirJanela();
    }
}