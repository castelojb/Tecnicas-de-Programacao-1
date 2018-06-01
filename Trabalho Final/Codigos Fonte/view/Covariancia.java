package view;
import controller.Controler;
import javafx.scene.control.ComboBox;
import layout.Botao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;

public class Covariancia implements ActionListener{
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
    String colunaCalculavel1;
    String colunaCalculavel2;
    int colunaMedia;
    JPanel painel;

    JTable valor;

    JComboBox setColunaFixa;
    JComboBox setElementoFixo;
    JComboBox setColunaCalculavel1;
    JComboBox setColunaCalculavel2;

    String[] nenhum = {"Nenhum"};

    JPanel calcular;

    Covariancia(PainelInformacoes informacoes, String nome){
        this.controlador = informacoes.getControlador();
        this.informacoes = informacoes;
        this.nome = nome;
    }

    public void actionPerformed(ActionEvent evento) {

        painel = new JPanel();
        JPanel painelBotao = new JPanel();
        painel.setPreferredSize(new Dimension(740, 600));
        painel.setLayout(new BorderLayout());
        painelBotao.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel escolhas = new JPanel();
        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());
        escolhas.setLayout(new GridLayout(2, 4, 10, 1));

        calcular = new JPanel();
        calcular.setLayout(new BorderLayout());
        calcular.setPreferredSize(new Dimension(700, 500));

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

        JLabel tituloColunaCalculavel1 = new JLabel("     Média em relação:        ");
        String[] colunaCalculavel1 = controlador.colunasNumericas();
        setColunaCalculavel1 = new JComboBox(colunaCalculavel1);
        setColunaCalculavel1.setMaximumRowCount(4);
        setColunaCalculavel1.addItemListener(new ColunaCalculavel1());

        JLabel tituloColunaCalculavel2 = new JLabel("     Média em relação:        ");
        String[] colunaCalculavel2 = controlador.colunasNumericas();
        setColunaCalculavel2 = new JComboBox(colunaCalculavel2);
        setColunaCalculavel2.setMaximumRowCount(4);
        setColunaCalculavel2.addItemListener(new ColunaCalculavel2());

        escolhas.add(tituloColunaFixa);
        escolhas.add(tituloElementoFixo);
        escolhas.add(tituloColunaCalculavel1);
        escolhas.add(tituloColunaCalculavel2);

        escolhas.add(setColunaFixa);
        escolhas.add(setElementoFixo);
        escolhas.add(setColunaCalculavel1);
        escolhas.add(setColunaCalculavel2);

        Botao botaoCalcular = new Botao("CALCULAR");
        botaoCalcular.setMargin(new Insets(0, 0,0 , 0));
        botaoCalcular.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        botaoCalcular.addActionListener(new Calcular());

        //Só aparece quando aperta o botao calcular
        valor = new JTable();
        JScrollPane scrollPane = new JScrollPane (valor);
        calcular.add(scrollPane);


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

    public class ColunaCalculavel1 implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                colunaCalculavel1 = setColunaCalculavel1.getSelectedItem().toString();
            }

        }
    }


    public class ColunaCalculavel2 implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                colunaCalculavel2 = setColunaCalculavel2.getSelectedItem().toString();
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
            String[] titulo = new String[5];
            titulo[0] = colunaCalculavel1;
            titulo[1] = colunaCalculavel2;
            titulo[2] = "desvio de "+colunaCalculavel1;
            titulo[3] = "desvio de "+colunaCalculavel2;
            titulo[4] = "Covariancia";

            try{
                if (colunaFixa.matches("Nenhum")){
                    metodo = Controler.class.getMethod(nome, String.class, String.class);
                    texto = metodo.invoke(controlador, colunaCalculavel1, colunaCalculavel2);
                    String[][] resultado = (String[][])texto;
                    if (resultado != null){
                        valor = new JTable(resultado, titulo){
                            public boolean isCellEditable(int row,int column){
                                Object o = getValueAt(row,column);
                                return false;
                            }
                        };
                        valor.setFillsViewportHeight(true);
                        valor.getTableHeader().setReorderingAllowed(false);
                        JScrollPane scrollPane = new JScrollPane (valor);
                        scrollPane.setPreferredSize(new Dimension( 700,500));
                        calcular.removeAll();
                        calcular.add(scrollPane, BorderLayout.NORTH);
                    } else {
                        calcular.removeAll();
                        calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "SEM ELEMENTOS PARA CALCULAR", TitledBorder.CENTER, TitledBorder.TOP));
                    }

                } else{
                    metodo = Controler.class.getMethod(nome, String.class, String.class, String.class, String.class);
                    texto = metodo.invoke(controlador, colunaFixa, elementoFixo, colunaCalculavel1, colunaCalculavel2);
                    String[][] resultado = (String[][])texto;

                    if (resultado != null){
                        valor = new JTable(resultado, titulo){
                            public boolean isCellEditable(int row,int column){
                                Object o = getValueAt(row,column);
                                return false;
                            }
                        };
                        valor.setFillsViewportHeight(true);
                        valor.getTableHeader().setReorderingAllowed(false);
                        JScrollPane scrollPane = new JScrollPane (valor);
                        scrollPane.setPreferredSize(new Dimension( 700,500));
                        calcular.removeAll();
                        calcular.add(scrollPane, BorderLayout.NORTH);
                    }
                    else {
                        calcular.removeAll();
                        calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "SEM ELEMENTOS PARA CALCULAR", TitledBorder.CENTER, TitledBorder.TOP));

                    }
                }
                calcular.repaint();
                painel.revalidate();

            } catch (Exception e){
                System.out.println("Nao deu: "+e);
            }
        }
    }
}