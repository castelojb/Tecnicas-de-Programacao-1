package view;

import layout.Botao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Editar implements ActionListener{
    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);
    Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);


    PainelInformacoes informacoes;
    String nome;
    int colunaFixa;
    String elementoFixo;
    int colunaMedia;

    Editar(PainelInformacoes informacoes, String nome){
        this.informacoes = informacoes;
        this.nome = nome;
    }
    public void actionPerformed(ActionEvent evento) {
        JPanel painel = new JPanel();
        painel.setPreferredSize(new Dimension(740, 900));
        painel.setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout());

        JPanel salvar = new JPanel();
        salvar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel cabecalho = new JPanel();
        cabecalho.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("  "+nome);
        cabecalho.add(titulo, BorderLayout.WEST);
        Icon fechar = new ImageIcon(getClass().getResource("fechar.png"));
        Botao botaoFechar = new Botao(fechar);
        botaoFechar.setContentAreaFilled(false);
        botaoFechar.addActionListener(new Fechar(painel));
        cabecalho.add(botaoFechar, BorderLayout.EAST);
        painel.add(cabecalho, BorderLayout.NORTH);


        Botao botaoSalvar = new Botao("SALVAR");
        botaoSalvar.setMargin(new Insets(0, 0,0 , 0));
        botaoSalvar.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        //ACAO COM O BOTAO PRA CALCULAR E DEPOIS EXIBE NA COISA


        Botao botaoSalvarComo = new Botao("SALVAR COMO");
        botaoSalvarComo.setMargin(new Insets(0, 0,0 , 0));
        botaoSalvarComo.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        //ACAO COM O BOTAO PRA CALCULAR E DEPOIS EXIBE NA COISA


        //Só aparece quando aperta o botao calcular
        JLabel resultado = new JLabel("     RESULTADO");
        salvar.add(botaoSalvar);
        salvar.add(botaoSalvarComo);

        painel.add(centro, BorderLayout.CENTER);
        painel.add(salvar, BorderLayout.SOUTH);

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