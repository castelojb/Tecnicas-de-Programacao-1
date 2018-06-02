package view;
import com.sun.org.apache.regexp.internal.RE;
import controller.Controler;
import view.layout.Botao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;

public class Tabelas implements ActionListener{
    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);
    private Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);

    //CONSTRUTOR
    private Controler controlador;
    PainelInformacoes informacoes;
    String nome;
    boolean coeficiente, covariancia, contingencia, frequencias;
    /////////////////////

    boolean escolha = false;
    String colunaFixa = "Nenhum";
    String elementoFixo;
    String colunaCalculavel1;
    String colunaCalculavel2;
    int colunaMedia;

    JTable valor;
    JPanel painel;

    JComboBox setColunaFixa;
    JComboBox setElementoFixo;
    JComboBox setColunaCalculavel1;
    JComboBox setColunaCalculavel2;

    String[] nenhum = {"Nenhum"};

    JPanel calcular;


    Tabelas(PainelInformacoes informacoes, String nome, boolean coeficiente, boolean covariancia, boolean contingencia, boolean frequencias){
        this.controlador = informacoes.getControlador();
        this.informacoes = informacoes;
        this.nome = nome;
        this.coeficiente = coeficiente;
        this.covariancia = covariancia;
        this.contingencia = contingencia;
        this.frequencias = frequencias;

    }

    public void actionPerformed(ActionEvent evento){
        painel = new JPanel();
        painel.setPreferredSize(new Dimension(740, 650));
        painel.setLayout(new BorderLayout());

        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel escolhas = new JPanel();
        setPainelEscolhas(escolhas);

        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());

        calcular = new JPanel();
        calcular.setLayout(new BorderLayout());
        calcular.setPreferredSize(new Dimension(700, 520));

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
        String[] colunaCalculavel1 = colunasCalculaveis();
        setColunaCalculavel1 = new JComboBox(colunaCalculavel1);
        setColunaCalculavel1.setMaximumRowCount(4);
        setColunaCalculavel1.addItemListener(new ColunaCalculavel1());

        JLabel tituloColunaCalculavel2 = new JLabel();
        if (coeficiente || covariancia || contingencia){
            tituloColunaCalculavel2 = new JLabel("     Média em relação:        ");
            String[] colunaCalculavel2 = colunasCalculaveis();
            setColunaCalculavel2 = new JComboBox(colunaCalculavel2);
            setColunaCalculavel2.setMaximumRowCount(4);
            setColunaCalculavel2.addItemListener(new ColunaCalculavel2());

        }

        escolhas.add(tituloColunaFixa);
        escolhas.add(tituloElementoFixo);
        escolhas.add(tituloColunaCalculavel1);
        if (coeficiente || covariancia || contingencia) escolhas.add(tituloColunaCalculavel2);


        escolhas.add(setColunaFixa);
        escolhas.add(setElementoFixo);
        escolhas.add(setColunaCalculavel1);
        if (coeficiente || covariancia || contingencia) escolhas.add(setColunaCalculavel2);

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

    void setPainelEscolhas(JPanel escolhas){
        if (frequencias){
            escolhas.setLayout(new GridLayout(2, 3, 10, 1));
        } else {
            escolhas.setLayout(new GridLayout(2, 4, 10, 1));
        }
    }

    String[] colunasCalculaveis(){
        if (coeficiente || covariancia){
            return controlador.colunasNumericas();
        }
        return controlador.titulosColunas();
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

            String[] titulo = tituloTabela();
            String[][] resultado = calcularMetrica();
            String tituloCalcular = "";

            if (resultado != null){
                tituloCalcular += tituloCalcular(resultado);
                valor = new JTable(resultado, titulo){
                    public boolean isCellEditable(int row,int column){
                        Object o = getValueAt(row,column);
                        return false;
                    }
                };
                valor.setFillsViewportHeight(true);
                valor.getTableHeader().setReorderingAllowed(false);
                valor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                JScrollPane scrollPane = new JScrollPane (valor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setPreferredSize(new Dimension( 700,500));
                calcular.removeAll();
                calcular.add(scrollPane, BorderLayout.NORTH);

            }
            else {
                tituloCalcular = "NÃO HÁ ELEMENTOS";
                calcular.removeAll();
            }
            calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));

            calcular.repaint();
            painel.revalidate();

        }

        String[] tituloTabela(){
            if (coeficiente || covariancia){
                String[] titulo = new String[5];
                titulo[0] = colunaCalculavel1;
                titulo[1] = colunaCalculavel2;
                titulo[2] = "desvio de "+colunaCalculavel1;
                titulo[3] = "desvio de "+colunaCalculavel2;
                titulo[4] = "Covariancia";
                return titulo;

            } else if(frequencias){
                String[] titulo = new String[3];
                titulo[0] = colunaCalculavel1;
                titulo[1] = "Frequencia absoluat";
                titulo[2] = "Frequencia relativa";
                return titulo;

            }
            else{
                String[] titulo = new String[controlador.elementosColunaSemRepeticao(colunaCalculavel2).length+1];
                titulo[0] = colunaCalculavel1;
                System.arraycopy(controlador.elementosColunaSemRepeticao(colunaCalculavel2), 0, titulo, 1, controlador.elementosColunaSemRepeticao(colunaCalculavel2).length);
                return titulo;
            }
        }

        String[][] calcularMetrica(){
            Method metodo;
            Object texto;
            try {
                if (frequencias){
                    if (colunaFixa.matches("Nenhum")){
                        metodo = Controler.class.getMethod(nome, String.class);
                        texto = metodo.invoke(controlador, colunaCalculavel1);
                        return (String[][])texto;
                    }
                    else {
                        metodo = Controler.class.getMethod(nome, String.class, String.class, String.class);
                        texto = metodo.invoke(controlador, colunaFixa, elementoFixo, colunaCalculavel1);
                        return (String[][])texto;
                    }
                } else{
                    if (colunaFixa.matches("Nenhum")){
                        metodo = Controler.class.getMethod(nome, String.class, String.class);
                        texto = metodo.invoke(controlador, colunaCalculavel1, colunaCalculavel2);
                        return (String[][])texto;
                    }
                    else {
                        metodo = Controler.class.getMethod(nome, String.class, String.class, String.class, String.class);
                        texto = metodo.invoke(controlador, colunaFixa, elementoFixo, colunaCalculavel1, colunaCalculavel2);
                        return (String[][])texto;
                    }
                }

            } catch (Exception e){
                System.out.println("Não deu! "+ e);
                return null;
            }
        }

        String tituloCalcular(String[][] resultado){
            if (coeficiente) return Math.sqrt(Double.parseDouble(resultado[resultado.length-1][2])*Double.parseDouble(resultado[resultado.length-1][3])) == 0? "COEFICIENTE DE PEARSON = 0" : "COEFICIENTE DE PEARSON = "+String.valueOf((Double.parseDouble(resultado[resultado.length-1][4]))/Math.sqrt(Double.parseDouble(resultado[resultado.length-1][2])*Double.parseDouble(resultado[resultado.length-1][3])));
            if (covariancia) return "COVARIÂNCIA: "+resultado[resultado.length-1][4];
            if (contingencia) return "TABELA DE CONTINGENCIA: ";
            else return "TABELA DE FREQUENCIA";
        }
    }
}
