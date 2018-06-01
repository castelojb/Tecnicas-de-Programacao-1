package view;
import controller.Controler;
import layout.Botao;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelInformacoes extends JPanel{

    public Janela janela;
    private JScrollPane scrollCentral;
    Dimension dimensaoCental;
    Box boxCentral;
    private JPanel painelSuperior = new JPanel();
    private JPanel painelInferir = new JPanel();
    private JPanel painelLeste = new JPanel();
    private JPanel painelOeste = new JPanel();
    private JPanel painelCentro = new JPanel();

    Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);
    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);


    PainelInformacoes(Janela janela){
        super();
        this.janela = janela;
        this.configuracoes();
        this.setVisible(true);
    }

    public Controler getControlador(){
        return janela.getControlador();
    }

     void configuracoes(){

         // super.configuracoesPadrao();
         // SEGUNDA JANELAAAAA
         Border bordaVazia = BorderFactory.createEmptyBorder(0, 0, 0, 0);
         painelSuperior.setLayout(new BorderLayout());
         painelSuperior.setBorder(bordaVazia);

         painelInferir.setLayout(new BorderLayout());
         painelInferir.setBorder(bordaVazia);

         painelLeste.setLayout(new GridLayout(20, 1));
         painelLeste.setBorder(bordaVazia);

         painelOeste.setLayout(new GridLayout(20, 1));
         painelOeste.setBorder(bordaVazia);

         painelCentro.setLayout(new FlowLayout());
         painelCentro.setBorder(bordaVazia);

         this.setLayout(new BorderLayout());
         this.setBackground(corBranca);


         //   BARRA DE FERRAMENTAS - BOTÃO VOLTAR  //
         Icon botaoVoltar = new ImageIcon(getClass().getResource("volta.png"));

         Botao voltar = new Botao(botaoVoltar);
         voltar.setContentAreaFilled(false);
         voltar.addActionListener(new BotaoVoltar());


         painelSuperior.add(voltar, BorderLayout.WEST);
         painelSuperior.setBackground(corBotaoAnalisar);

         this.add(painelSuperior, BorderLayout.NORTH);


         //   BARRA DOS BOTÕES - //

         Botao media = new Botao("MÉDIA");
         media.setMargin(new Insets(0, 50, 0, 50));
         media.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         media.addActionListener(new Numericos(this, "media"));

         Botao moda = new Botao("MODA");
         moda.setMargin(new Insets(0, 50,0 , 50));
         moda.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         moda.addActionListener(new Numericos(this, "moda"));

         Botao variancia = new Botao("VARIÂNCIA");
         variancia.setMargin(new Insets(0, 50,0 , 50));
         variancia.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         variancia.addActionListener(new Numericos(this, "variancia"));

         Botao desvio = new Botao("DESVIO");
         desvio.setMargin(new Insets(0, 50,0 , 50));
         desvio.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         desvio.addActionListener(new Numericos(this, "desvio"));


         Botao mediana = new Botao("MEDIANA");
         mediana.setMargin(new Insets(0, 50,0 , 50));
         mediana.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         mediana.addActionListener(new Numericos(this, "mediana"));


         Botao minimo = new Botao("MÍNIMO");
         minimo.setMargin(new Insets(0, 50,0 , 50));
         minimo.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         minimo.addActionListener(new Numericos(this, "minimo"));


         Botao maximo = new Botao("MÁXIMO");
         maximo.setMargin(new Insets(0, 50,0 , 50));
         maximo.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         maximo.addActionListener(new Numericos(this, "maximo"));


         Botao skewness = new Botao("SKEWNESS");
         skewness.setMargin(new Insets(0, 50,0 , 50));
         skewness.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         skewness.addActionListener(new Numericos(this, "SKEWNESS"));


         Botao kurtoses = new Botao("KURTOSES");
         kurtoses.setMargin(new Insets(0, 50,0 , 50));
         kurtoses.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         kurtoses.addActionListener(new Numericos(this, "KURTOSES"));

         Botao editar = new Botao("EDITAR CSV");
         editar.setMargin(new Insets(0, 50,0 , 50));
         editar.configurarFonteCorFundo(fonteBotao, corBranca, corTeste);
         editar.addActionListener(new Editar(this, "EDITAR"));


         JLabel tituloNumerico = new JLabel("VALORES NUMÉRICOS", SwingConstants.CENTER);
         tituloNumerico.setFont(fonteBotao);
         tituloNumerico.setForeground(corBranca);
         painelOeste.add(new JLabel(""));
         painelOeste.add(tituloNumerico);
         painelOeste.add(media);
         painelOeste.add(moda);
         painelOeste.add(mediana);
         painelOeste.add(desvio);
         painelOeste.add(variancia);
         painelOeste.add(minimo);
         painelOeste.add(maximo);
         painelOeste.add(skewness);
         painelOeste.add(kurtoses);
         painelOeste.add(new JLabel(""));
         painelOeste.add(new JLabel(""));
         painelOeste.add(new JLabel(""));
         painelOeste.add(new JLabel(""));
         painelOeste.add(new JLabel(""));
         painelOeste.add(new JLabel(""));
         painelOeste.add(new JLabel(""));
         painelOeste.add(editar);
         painelOeste.setBackground(corTeste);

         this.add(painelOeste, BorderLayout.WEST);

        //   BARRA DOS GRÁFICOS - //
         //  Graficos
         Botao histograma = new Botao("HISTOGRAMA");
         histograma.setMargin(new Insets(0, 50,0 , 50));
         histograma.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         Botao frequenciasG = new Botao("FREQUÊNCIAS");
         frequenciasG.setMargin(new Insets(0, 50,0 , 50));
         frequenciasG.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
         frequenciasG.addActionListener(new Frequencia(this));

         Botao boxplot = new Botao("BOXPLOT");
         boxplot.setMargin(new Insets(0, 50,0 , 50));
         boxplot.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         Botao scartterplot = new Botao("SCARTTERPLOT");
         scartterplot.setMargin(new Insets(0, 50,0 , 50));
         scartterplot.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         //  Tabela
         Botao frequenciasT = new Botao("FREQUÊNCIAS");
         frequenciasT.setMargin(new Insets(0, 50,0 , 50));
         frequenciasT.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         Botao contingencia = new Botao("CONTINGÊNCIA");
         contingencia.setMargin(new Insets(0, 50,0 , 50));
         contingencia.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         Botao covariancia = new Botao("COVARIÂNCIA");
         covariancia.setMargin(new Insets(0, 50,0 , 50));
         covariancia.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         Botao correlacao = new Botao("CORRELAÇÃO");
         correlacao.setMargin(new Insets(0, 50,0 , 50));
         correlacao.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);

         JLabel tituloGrafico = new JLabel("GRÁFICOS", SwingConstants.CENTER);
         tituloGrafico.setFont(fonteBotao);
         tituloGrafico.setForeground(corBranca);

         JLabel tituloTabela = new JLabel("TABELAS", SwingConstants.CENTER);
         tituloTabela.setFont(fonteBotao);
         tituloTabela.setForeground(corBranca);

         painelLeste.add(new JLabel(""));
         painelLeste.add(tituloGrafico);
         painelLeste.add(histograma);
         painelLeste.add(frequenciasG);
         painelLeste.add(boxplot);
         painelLeste.add(scartterplot);

         painelLeste.add(new JLabel(""));
         painelLeste.add(new JLabel(""));
         painelLeste.add(tituloTabela);
         painelLeste.add(frequenciasT);
         painelLeste.add(contingencia);
         painelLeste.add(covariancia);
         painelLeste.add(correlacao);

        painelLeste.setBackground(corBotaoBuscar);
        this.add(painelLeste, BorderLayout.EAST);

         dimensaoCental = new Dimension(750, 0);

         painelCentro.setPreferredSize(dimensaoCental);
         painelCentro.setBackground(corFundo);
         boxCentral = Box.createHorizontalBox();

         JScrollPane rolagem = new JScrollPane(painelCentro);
         rolagem.setPreferredSize(new Dimension( 750,0));
         rolagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        boxCentral.add(rolagem);
        boxCentral.setBorder(bordaVazia);
        this.add(boxCentral, BorderLayout.CENTER);

       // this.add(painelInferir, BorderLayout.SOUTH);

    }

    public class BotaoVoltar implements ActionListener {
        public void actionPerformed(ActionEvent evento) {
            janela.getContentPane().removeAll();
            janela.conteudoJanela(new PainelInicial(janela));
            janela.revalidate();
            janela.repaint();
        }
    }

    public void adicionaPainelCentral(JPanel painel){
        dimensaoCental.setSize(700, dimensaoCental.getHeight()+100);
        painelCentro.add(painel, 0);
        painelCentro.revalidate();
        boxCentral.revalidate();
    }

    public void eliminaPainelCentral(JPanel painel){
        painelCentro.remove(painel);
        dimensaoCental.setSize(700, dimensaoCental.getHeight()-100);
        painelCentro.revalidate();
        boxCentral.revalidate();
        janela.repaint();
    }

}
