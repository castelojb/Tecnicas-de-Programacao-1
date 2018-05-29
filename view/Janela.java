package view;

import controller.Controler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Janela extends JFrame{
    protected Controler controlador;
    private Color corFundo = new Color(10, 10, 10);

    public Janela(){
        super("Analisador de Dados CSV");
        configuracoesPadrao();
    }

    public void setControlador(Controler controlador){
        this.controlador = controlador;
    }
    public Controler getControlador(){
        return this.controlador;
    }
    void configuracoesPadrao(){
        //     CONFIGURAÇÕES PADRÃO      //
        //SET BORDER LAYOUT
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);


        //TAMANHO DA TELA
        this.setSize(900, 700);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(corFundo);

        //BOTÃO FECHAR FUNCIONANDO
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            //ICONE BONITINHO
            URL url = ClassLoader.getSystemResource("imagens/icone.png");
            Image imagemIcone = Toolkit.getDefaultToolkit().getImage(url);
            this.setIconImage(imagemIcone);

        } catch (Exception e) {
            System.out.println("Erro ao carregar o ícone\n"+e);
        }

        //   FIM DAS CONFIGS PADRAO   //

    }

    public void conteudoJanela(JPanel painel){
        // FAZ A ROLAGEM
        Box box = Box.createHorizontalBox();
        box.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollPane rolagem = new JScrollPane(painel);
        rolagem.setComponentZOrder(rolagem.getVerticalScrollBar(), 0);
        rolagem.setComponentZOrder(rolagem.getViewport(), 1);
        rolagem.getVerticalScrollBar().setOpaque(false);
        rolagem.setViewportBorder(null);
     //   rolagem.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
     //   rolagem.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10));
    //    rolagem.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        box.add(rolagem);

        this.add(box);
    }

    public void exibirJanela(){
        this.setVisible(true);
    }



}
