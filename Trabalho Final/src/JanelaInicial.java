import botao.Botao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JanelaInicial extends Janela implements ActionListener {

    private JPanel painelGlobal = new JPanel();
    private JPanel painelSuperior = new JPanel();
    private JPanel painelInferir = new JPanel();
    private JPanel painelTexto = new JPanel();
    private JPanel painelBotao = new JPanel();

    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);


    JanelaInicial(){
        super();
    }

    void configuracoes(){
        super.configuracoesPadrao();
        //     LAYOUT DA PÁGINA INICIAL     //
        painelInferir.setLayout(new BorderLayout());
        painelSuperior.setLayout(new BorderLayout());
        painelGlobal.setLayout(new BorderLayout());

        try {
            //IMAGEM DA TELA DE INICIO
            Icon imagemTitulo = new ImageIcon(getClass().getResource("paginaInicial.png"));
            JLabel titulo = new JLabel(imagemTitulo, SwingConstants.CENTER);
            painelSuperior.add(titulo, BorderLayout.CENTER);

        }catch (Exception e){
            System.out.println("Erro ao carregar a imagem de fundo\n"+e);
        }

        //CAIXA DE TEXTO PARA INSERIR O ARQUIVO CSV//


        //caixa de texto
        Font fonte = new Font("SansSerif", Font.PLAIN, 31);
        JTextField texto = new JTextField("c:/", 25);
        texto.setSize(110, 50);
        texto.setFont(fonte);

        this.painelTexto.add(texto, BorderLayout.CENTER);
        this.painelTexto.setBackground(corFundo);

        //BOTAO DE PROCURAR ARQUIVO CSV
        Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);
        Botao buscar = new Botao("BUSCAR");
        buscar.setMargin(new Insets(4, 30, 4, 30));
        buscar.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        painelTexto.setPreferredSize(new Dimension(900, 200));

        this.painelTexto.add(buscar, BorderLayout.NORTH);
        this.painelInferir.add(painelTexto, "North");

        //BOTAO ANALISAR
        Botao analisar = new Botao("ANALISAR");
        analisar.setMargin(new Insets(10, 40, 10, 40));
        analisar.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoAnalisar);
        analisar.addActionListener(this);

        painelBotao.setBackground(corFundo);
        painelSuperior.setBackground(corFundo);

        painelBotao.add(analisar, BorderLayout.CENTER);

        painelInferir.add(painelBotao, "Center");

        painelGlobal.add(painelSuperior, "North");
        painelGlobal.add(painelInferir, "Center");
        painelGlobal.setBackground(corFundo);

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
        JanelaInformacoes janela = new JanelaInformacoes();
        janela.configuracoes();

        this.ocultarJanela();
        janela.exibirJanela();
    }
}
