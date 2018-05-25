import botao.Botao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaInformacoes extends Janela implements ActionListener {
    private JPanel painelGlobal = new JPanel();
    private JPanel painelSuperior = new JPanel();
    private JPanel painelInferir = new JPanel();
    private JPanel painelLeste = new JPanel();
    private JPanel painelOeste = new JPanel();
    private JPanel painelCentro = new JPanel();

    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);


    JanelaInformacoes(){
        super();
    }

     void configuracoes(){
        super.configuracoesPadrao();
        // SEGUNDA JANELAAAAA
        painelSuperior.setLayout(new BorderLayout());
        painelInferir.setLayout(new BorderLayout());
        painelLeste.setLayout(new GridLayout(20, 1));
        painelOeste.setLayout(new GridLayout(20, 1));
        painelCentro.setLayout(new BorderLayout());
        painelGlobal.setLayout(new BorderLayout());
        painelGlobal.setBackground(corBranca);


        //   BARRA DE FERRAMENTAS - BOTÃO VOLTAR  //
        Icon botaoVoltar = new ImageIcon(getClass().getResource("volta.png"));

        Botao voltar = new Botao(botaoVoltar);
        voltar.setContentAreaFilled(false);
        voltar.addActionListener(this);

        painelSuperior.add(voltar, BorderLayout.WEST);
        painelSuperior.setBackground(corBotaoAnalisar);

        painelGlobal.add(painelSuperior, BorderLayout.NORTH);


        //   BARRA DOS BOTÕES - //
        Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);
        Botao media = new Botao("MÉDIA");
        media.setMargin(new Insets(0, 50, 0, 50));
        media.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);

        Botao moda = new Botao("MODA");
        moda.setMargin(new Insets(0, 50,0 , 50));
        moda.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);

        Botao variancia = new Botao("VARIÂNCIA");
        variancia.setMargin(new Insets(0, 50,0 , 50));
        variancia.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);

        Botao desvio = new Botao("DESVIO");
        desvio.setMargin(new Insets(0, 50,0 , 50));
        desvio.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);

        Botao mediana = new Botao("MEDIANA");
        mediana.setMargin(new Insets(0, 50,0 , 50));
        mediana.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);

        painelOeste.add(media);
        painelOeste.add(moda);
        painelOeste.add(mediana);
        painelOeste.add(desvio);
        painelOeste.add(variancia);
        painelOeste.setBackground(corTeste);
        painelGlobal.add(painelOeste, BorderLayout.WEST);

        //   BARRA DOS GRÁFICOS - //
        Botao g1 = new Botao("GRÁFICO 1");
        g1.setMargin(new Insets(0, 50,0 , 50));
        g1.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

        Botao g2 = new Botao("GRÁFICO 2");
        g2.setMargin(new Insets(0, 50,0 , 50));
        g2.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

        Botao g3 = new Botao("GRÁFICO 3");
        g3.setMargin(new Insets(0, 50,0 , 50));
        g3.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

        Botao g4 = new Botao("GRÁFICO 4");
        g4.setMargin(new Insets(0, 50,0 , 50));
        g4.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

        Botao g5 = new Botao("GRÁFICO 5");
        g5.setMargin(new Insets(0, 50,0 , 50));
        g5.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

        painelLeste.add(g1);
        painelLeste.add(g2);
        painelLeste.add(g3);
        painelLeste.add(g4);
        painelLeste.add(g5);
        painelLeste.setBackground(corBotaoBuscar);
        painelGlobal.add(painelLeste, BorderLayout.EAST);


        painelCentro.setPreferredSize(new Dimension(600, 400));
        painelCentro.setBackground(corFundo);
        Box boxCentral = Box.createHorizontalBox();
        boxCentral.add(new JScrollPane(painelCentro));
        painelGlobal.add(boxCentral, BorderLayout.CENTER);

        painelGlobal.add(painelInferir, BorderLayout.SOUTH);

        // FAZ A ROLAGEM
        Box box = Box.createHorizontalBox();
        JScrollPane rolagem = new JScrollPane(painelGlobal);
        rolagem.setComponentZOrder(rolagem.getVerticalScrollBar(), 0);
        rolagem.setComponentZOrder(rolagem.getViewport(), 1);
        rolagem.getVerticalScrollBar().setOpaque(false);
        box.add(rolagem);
        this.add(box);
    }

    public void actionPerformed(ActionEvent evento) {
        JanelaInicial janela = new JanelaInicial();
        janela.configuracoes();
        this.ocultarJanela();
        janela.exibirJanela();
    }
}
