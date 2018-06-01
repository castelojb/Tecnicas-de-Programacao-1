package view;
import controller.Controler;
import layout.Botao;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class PainelInicial extends JPanel {
    public Janela janela;
    private JTextField texto;

    private JPanel painelSuperior = new JPanel();
    private JPanel painelInferir = new JPanel();
    private JPanel painelTexto = new JPanel();
    private JPanel painelBotao = new JPanel();

    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);


    public PainelInicial(Janela janela){
        super();
        this.configuracoes();
        this.janela = janela;
        this.setVisible(true);
    }

    public void configuracoes(){
        //super.configuracoesPadrao();
        //     LAYOUT DA PÁGINA INICIAL     //
        Border bordaVazia = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        this.setBorder(bordaVazia);
        painelInferir.setBorder(bordaVazia);
        painelSuperior.setBorder(bordaVazia);

        this.setLayout(new BorderLayout());
        painelInferir.setLayout(new BorderLayout());
        painelSuperior.setLayout(new BorderLayout());

        try {
            //IMAGEM DA TELA DE INICIO
            Icon imagemTitulo = new ImageIcon(getClass().getResource("paginaInicial.png"));
            JLabel titulo = new JLabel(imagemTitulo, SwingConstants.CENTER);
            painelSuperior.add(titulo, BorderLayout.CENTER);

        }catch (Exception e){
            System.out.println("Erro ao carregar a imagens de fundo\n"+e);
        }

        //CAIXA DE TEXTO PARA INSERIR O ARQUIVO CSV//


        //caixa de texto
        Font fonte = new Font("SansSerif", Font.PLAIN, 32);
        texto = new JTextField("C:\\Users\\Hariamy\\Desktop\\netflix.csv", 25);
        texto.setBorder(bordaVazia);
        texto.setSize(110, 50);
        texto.setFont(fonte);

        this.painelTexto.add(texto, BorderLayout.CENTER);
        this.painelTexto.setBackground(corFundo);
        painelTexto.setBorder(bordaVazia);

        //BOTAO DE PROCURAR ARQUIVO CSV


        Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);
        Botao buscar = new Botao("BUSCAR");
        buscar.setMargin(new Insets(4, 30, 4, 30));
        buscar.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        buscar.addActionListener(new BotaoBuscar());
        painelTexto.setPreferredSize(new Dimension(900, 150));

        this.painelTexto.add(buscar, BorderLayout.NORTH);
        this.painelInferir.add(painelTexto, "North");

        //BOTAO ANALISAR
        Botao analisar = new Botao("ANALISAR");
        analisar.setMargin(new Insets(10, 40, 10, 40));
        analisar.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoAnalisar);
        analisar.addActionListener(new BotaoAnalisar());


        painelBotao.setBackground(corFundo);
        painelBotao.setBorder(bordaVazia);
        painelSuperior.setBackground(corFundo);

        painelBotao.add(analisar, BorderLayout.CENTER);

        painelInferir.add(painelBotao, "Center");

        this.add(painelSuperior, "North");
        this.add(painelInferir, "Center");
        this.setBackground(corFundo);

    }

    public class BotaoAnalisar implements ActionListener {
        public void actionPerformed(ActionEvent evento) {
            boolean erro;
            Controler controlador = new Controler(texto.getText());
            erro = controlador.erro();

            if (erro){
                String mensagem = "";
                if (texto.getText().equals("")) mensagem = "Escolha um arquivo, ou digite um diretório na caixa de texto";
                else mensagem = "Não foi possível abrir o arquivo!";

                JOptionPane.showMessageDialog (new JFrame(), mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
            } else{
                janela.setControlador(controlador);
                janela.getContentPane().removeAll();
                janela.conteudoJanela(new PainelInformacoes(janela));
                janela.revalidate();
                janela.repaint();
            }

        }
    }

    public class BotaoBuscar implements ActionListener {
        public void actionPerformed(ActionEvent evento) {

            JFileChooser arquivo;
            LookAndFeel anteriro = UIManager.getLookAndFeel();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                arquivo = new JFileChooser();
                UIManager.setLookAndFeel(anteriro);

            } catch (Exception e) {
                arquivo = new JFileChooser();
            }

            FileNameExtensionFilter filtroCSV = new FileNameExtensionFilter("Arquivos CSV", "csv");
            arquivo.addChoosableFileFilter(filtroCSV);
            arquivo.setAcceptAllFileFilterUsed(false);

            if(arquivo.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION){
                texto.setText(arquivo.getSelectedFile().getAbsolutePath());
            }
        }
    }

}
