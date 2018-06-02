package view;
import controller.Controler;
import view.layout.Botao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;

public class Numericos implements ActionListener{
    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);
    private Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);

    private Controler controlador;
    PainelInformacoes informacoes;
    String nome;
    boolean escolha = false;
    String colunaFixa = "Nenhum";
    String elementoFixo;
    String colunaCalculavel;
    int colunaMedia;

    JLabel valor;

    JComboBox setColunaFixa;
    JComboBox setElementoFixo;
    JComboBox setColunaCalculavel;

    String[] nenhum = {"Nenhum"};

    Numericos(PainelInformacoes informacoes, String nome){
        this.controlador = informacoes.getControlador();
        this.informacoes = informacoes;
        this.nome = nome;
    }
    public void actionPerformed(ActionEvent evento) {

        JPanel painel = new JPanel();
        JPanel painelBotao = new JPanel();
        painel.setPreferredSize(new Dimension(740, 150));
        painel.setLayout(new BorderLayout());
        painelBotao.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel escolhas = new JPanel();
        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());
        escolhas.setLayout(new GridLayout(2, 3, 10, 1));

        JPanel calcular = new JPanel();
        calcular.setLayout(new BorderLayout());

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


        //pega a coluna de escolha de media
        JLabel tituloColunaFixa = new JLabel("     Coluna fixa:    ");
        String[] colunaFixa = new String[controlador.titulosColunas().length+1];
        colunaFixa[0] = "Nenhum";
        System.arraycopy(controlador.titulosColunas(), 0, colunaFixa, 1, controlador.titulosColunas().length);
        controlador.titulosColunas();
        setColunaFixa = new JComboBox(colunaFixa);
        setColunaFixa.setMaximumRowCount(4);
        setColunaFixa.addItemListener(new ColunaFixa());

        JLabel tituloElementoFixo = new JLabel("     Elemento fixo:    ");
        setElementoFixo = new JComboBox(nenhum);
        setElementoFixo.setMaximumRowCount(4);
        setElementoFixo.addItemListener(new ElementoFixo());

        JLabel tituloColunaCalculavel = new JLabel("     Média em relação:        ");
        String[] colunaCalculavel = controlador.colunasNumericas();
        setColunaCalculavel = new JComboBox(colunaCalculavel);
        setColunaCalculavel.setMaximumRowCount(4);
        setColunaCalculavel.addItemListener(new ColunaCalculavel());

        escolhas.add(tituloColunaFixa);
        escolhas.add(tituloElementoFixo);
        escolhas.add(tituloColunaCalculavel);

        escolhas.add(setColunaFixa);
        escolhas.add(setElementoFixo);
        escolhas.add(setColunaCalculavel);

        Botao botaoCalcular = new Botao("CALCULAR");
        botaoCalcular.setMargin(new Insets(0, 0,0 , 0));
        botaoCalcular.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        botaoCalcular.addActionListener(new Calcular());

        //Só aparece quando aperta o botao calcular
        JLabel resultado = new JLabel("     RESULTADO");
        valor = new JLabel();
        calcular.add(resultado, BorderLayout.CENTER);
        calcular.add(valor, BorderLayout.EAST);


        painelBotao.add(botaoCalcular, gbc);
        painelBotao.setBackground(corTeste);
        centro.add(escolhas, BorderLayout.CENTER);
        centro.add(painelBotao, BorderLayout.EAST);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(calcular, BorderLayout.SOUTH);


        informacoes.adicionaPainelCentral(painel);

    }

    public class ColunaFixa implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                colunaFixa = setColunaFixa.getSelectedItem().toString();
                String[] vetor;
                if (colunaFixa.matches("Nenhum")){
                    vetor = nenhum;
                }else{
                    vetor =  controlador.elementosColunaSemRepeticao(colunaFixa);
                }

                DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel(vetor);
                setElementoFixo.setModel(modelo);
                informacoes.revalidate();

            }

        }
    }

    public class ElementoFixo implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                elementoFixo = setElementoFixo.getSelectedItem().toString();
            }

        }
    }

    public class ColunaCalculavel implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                colunaCalculavel = setColunaCalculavel.getSelectedItem().toString();
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

    public class Calcular implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            Method metodo;
            Object texto;
            try{
                if (colunaFixa.matches("Nenhum")){
                    metodo = Controler.class.getMethod(nome, String.class);
                    texto = metodo.invoke(controlador, colunaCalculavel);
                    valor.setText((String)texto);

                } else{
                    metodo = Controler.class.getMethod(nome, String.class, String.class, String.class);
                    texto = metodo.invoke(controlador, colunaFixa, elementoFixo, colunaCalculavel);
                    valor.setText((String)texto);
                }
                informacoes.revalidate();

            } catch (Exception e){
                System.out.println("Nao deu: "+e);
            }
        }
    }
}