import javax.swing.*;
import java.awt.*;
import java.net.URL;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io. *;

 class Janela extends JFrame{

    private Color corFundo = new Color(10, 10, 10);

    Janela(){
        super("Analisador de Dados CSV");
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
            URL url = ClassLoader.getSystemResource("icone1.png");
            Image imagemIcone = Toolkit.getDefaultToolkit().getImage(url);
            this.setIconImage(imagemIcone);

        } catch (Exception e) {
            System.out.println("Erro ao carregar o ícone\n"+e);
        }

        //   FIM DAS CONFIGS PADRAO   //

    }

    void exibirJanela(){
        this.setVisible(true);
    }

    void ocultarJanela(){
        this.setVisible(false);
    }

}
