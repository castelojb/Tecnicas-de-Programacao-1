package view;

import layout.Botao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Frequencia implements ActionListener{
    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);
    Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);


    PainelInformacoes informacoes;

    int colunaFixa;
    String elementoFixo;
    int colunaMedia;

    Frequencia(PainelInformacoes informacoes){
        this.informacoes = informacoes;
    }
    public void actionPerformed(ActionEvent evento) {

        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(740, 150));
        painel.setLayout(new BorderLayout());

        JPanel escolhas = new JPanel();
        escolhas.setLayout(new FlowLayout());

        JPanel calcular = new JPanel();
        calcular.setLayout(new FlowLayout());

        JPanel cabecalho = new JPanel();
        cabecalho.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("  FREQUÊNCIA");
        cabecalho.add(titulo, BorderLayout.WEST);
        Icon fechar = new ImageIcon(getClass().getResource("fechar.png"));
        Botao botaoFechar = new Botao(fechar);
        botaoFechar.setContentAreaFilled(false);
        botaoFechar.addActionListener(new Fechar(painel));
        cabecalho.add(botaoFechar, BorderLayout.EAST);
        painel.add(cabecalho, BorderLayout.NORTH);


        //pega a coluna de escolha de media
        JLabel titulo1 = new JLabel("     Coluna:    ");
        escolhas.add(titulo1);
        String[] escolha1 = {"Val1", "Val2", "Val3", "Val4", "Val5"};
        JComboBox setEscolha1 = new JComboBox(escolha1);
        setEscolha1.setMaximumRowCount(4);
        setEscolha1.addItemListener(new Escolha1());
        escolhas.add(setEscolha1);

        Botao botaoExibir = new Botao("EXIBIR");
        botaoExibir.setMargin(new Insets(0, 0,0 , 0));
        botaoExibir.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        //ACAO COM O BOTAO PRA CALCULAR E DEPOIS EXIBE NA COISA
        escolhas.add(botaoExibir);

        //Só aparece quando aperta o botao calcular
        JLabel resultado = new JLabel("     RESULTADO");
        calcular.add(resultado, BorderLayout.EAST);

        painel.add(escolhas, BorderLayout.CENTER);
        painel.add(calcular, BorderLayout.SOUTH);

        informacoes.adicionaPainelCentral(painel);

    }
    public class Escolha1 implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                //faz o que eu quiser com ele acho
            }
        }
    }

    public class Fechar implements ActionListener {
        JPanel painel;
        Fechar(JPanel painel){
            this.painel = painel;
        }
        public void actionPerformed(ActionEvent evento) {
            informacoes.eliminaPainelCentral(this.painel);
        }
    }
}