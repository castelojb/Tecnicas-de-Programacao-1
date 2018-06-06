import view.janela.Janela;
import view.janela.PainelInicial;

import java.awt.*;
import java.io.File;
import java.net.URL;


public class Main{
    public static void main(String args[]) {
        System.setProperty("file.encoding", "UTF-8");

        Janela janela = new Janela();
        janela.conteudoJanela(new PainelInicial(janela));
        janela.exibirJanela();
    }
}