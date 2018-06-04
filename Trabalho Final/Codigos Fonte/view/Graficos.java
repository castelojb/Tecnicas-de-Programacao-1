package view;
import com.sun.org.apache.regexp.internal.RE;
import controller.Controler;
import javafx.scene.control.CheckBox;
import view.layout.Botao;
import view.layout.Cores;
import view.layout.Fontes;
import view.layout.grafico.*;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Method;

public class Graficos implements ActionListener{
    private Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);

    //CONSTRUTOR
    private Controler controlador;
    PainelInformacoes informacoes;
    String nome;
    boolean contingencia = false, frequencias = false, scatterplot = false, boxplot = false, histograma = false;
    /////////////////////
    JCheckBox escolhaPorcentagem;
    boolean escolha = false;
    boolean porcentagem = false;
    String colunaFixa = "Nenhum";
    String elementoFixo;
    String colunaCalculavel1;
    String colunaCalculavel2;
    String classes = "5";
    int colunaMedia;

    Grafico valor;
    JPanel painel;

    JComboBox setColunaFixa;
    JComboBox setElementoFixo;
    JComboBox setColunaCalculavel1;
    JComboBox setColunaCalculavel2;
    JComboBox numeroClasses;

    String[] nenhum = {"Nenhum"};

    JPanel calcular;


    Graficos(PainelInformacoes informacoes, String nome){
        this.controlador = informacoes.getControlador();
        this.informacoes = informacoes;
        this.nome = nome;

        if (nome.equals("contingencia")) contingencia = true;
        else if (nome.equals("scatterplot")) scatterplot = true;
        else if (nome.equals("frequencias")) frequencias = true;
        else if (nome.equals(  "boxplot"  )) boxplot = true;
        else if (nome.equals("histograma")) histograma = true;
    }

    public void actionPerformed(ActionEvent evento){
        painel = new JPanel();
        painel.setPreferredSize(new Dimension(780, 670));
        painel.setLayout(new BorderLayout());

        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        painelBotao.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        JPanel escolhas = new JPanel();
        escolhas.setLayout(new GridLayout(2, 4, 5, 5));
        escolhas.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 0));

        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());

        calcular = new JPanel();
        calcular.setLayout(new BorderLayout());
        calcular.setPreferredSize(new Dimension(700, 520));

        //CABECALHO
        JPanel cabecalho = new JPanel();
        JPanel cabecalhoTitulo = new JPanel();
        JPanel cabecalhoInformacao = new JPanel();

        cabecalhoTitulo.setBackground(Cores.corVerde);

        cabecalho.setLayout(new BorderLayout());
        cabecalhoTitulo.setLayout(new BorderLayout());
        cabecalhoInformacao.setLayout(new FlowLayout());

        JLabel tituloGeral = new JLabel(setTituloGeral());
        tituloGeral.setFont(Fontes.TITULO);
        tituloGeral.setForeground(Cores.corBotaoAzulEscuro);

        cabecalhoTitulo.add(tituloGeral, BorderLayout.WEST);
        Icon fechar = new ImageIcon(getClass().getResource("/imagens\\fechar.png"));
        Botao botaoFechar = new Botao(fechar);
        botaoFechar.setContentAreaFilled(false);
        botaoFechar.addActionListener(new Fechar(painel));
        cabecalhoTitulo.add(botaoFechar, BorderLayout.EAST);

        cabecalhoInformacao.add(new JLabel(setInformacao()));
        cabecalho.add(cabecalhoTitulo, BorderLayout.NORTH);
        cabecalho.add(cabecalhoInformacao, BorderLayout.CENTER);
        painel.add(cabecalho, BorderLayout.NORTH);

        //pega a coluna de escolha de media
        JLabel tituloColunaFixa = new JLabel("<html>OPCIONAL - caso queira,<BR/>escolha uma coluna fixa</html>");
        String[] colunaFixa = new String[controlador.titulosColunas().length+1];
        colunaFixa[0] = "Nenhum";
        System.arraycopy(controlador.titulosColunas(), 0, colunaFixa, 1, controlador.titulosColunas().length);
        controlador.titulosColunas();
        setColunaFixa = new JComboBox(colunaFixa);
        setColunaFixa.setMaximumRowCount(4);
        setColunaFixa.addItemListener(new ColunaFixa());

        JLabel tituloElementoFixo = new JLabel("<html>OPCIONAL - caso queira,<BR/>escolha um elemento fixo</html>");
        setElementoFixo = new JComboBox(nenhum);
        setElementoFixo.setMaximumRowCount(4);
        setElementoFixo.addItemListener(new ElementoFixo());

        JLabel tituloColunaCalculavel1 = new JLabel(setInformacaoVariavel());
        String[] colunaCalculavel1 = colunasCalculaveis();
        this.colunaCalculavel1 = colunaCalculavel1[0];
        setColunaCalculavel1 = new JComboBox(colunaCalculavel1);
        setColunaCalculavel1.setMaximumRowCount(4);
        setColunaCalculavel1.addItemListener(new ColunaCalculavel1());

        JLabel tituloColunaCalculavel2 = new JLabel();
        if (scatterplot || contingencia){
            tituloColunaCalculavel2 = new JLabel("EM RELAÇÃO:");
            String[] colunaCalculavel2 = colunasCalculaveis();
            this.colunaCalculavel2 = colunaCalculavel2[0];
            setColunaCalculavel2 = new JComboBox(colunaCalculavel2);
            setColunaCalculavel2.setMaximumRowCount(4);
            setColunaCalculavel2.addItemListener(new ColunaCalculavel2());

        }
        if (histograma){
            tituloColunaCalculavel2 = new JLabel("Quantidade de classes:");
            Integer[] classes = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            numeroClasses = new JComboBox(classes);
            numeroClasses.setMaximumRowCount(4);
            numeroClasses.addItemListener(new Classes());

        }

        escolhas.add(tituloColunaCalculavel1);
        if ( contingencia || scatterplot || histograma) escolhas.add(tituloColunaCalculavel2);
        escolhas.add(tituloColunaFixa);
        escolhas.add(tituloElementoFixo);


        escolhas.add(setColunaCalculavel1);
        if (contingencia || scatterplot) escolhas.add(setColunaCalculavel2);
        if (histograma) escolhas.add(numeroClasses);
        escolhas.add(setColunaFixa);
        escolhas.add(setElementoFixo);

        Botao botaoCalcular = new Botao("CALCULAR");
        botaoCalcular.setMargin(new Insets(0, 0,0 , 0));
        botaoCalcular.configurarFonteCorFundo(Fontes.TITULO, Cores.corBranca, Cores.azulEscuro2);
        botaoCalcular.addActionListener(new Calcular());

        if (frequencias){
            escolhaPorcentagem = new JCheckBox("Em porcentagem");
            escolhaPorcentagem.addItemListener(new Porcentagem());
            painelBotao.add(escolhaPorcentagem, gbc);

        }


        painelBotao.add(botaoCalcular, gbc);

        centro.add(escolhas, BorderLayout.CENTER);
        centro.add(painelBotao, BorderLayout.EAST);

        painel.add(centro, BorderLayout.CENTER);
        painel.add(calcular, BorderLayout.SOUTH);

        informacoes.adicionaPainelCentral(painel, 680);

    }

    void setPainelEscolhas(JPanel escolhas){
        if (frequencias){
            escolhas.setLayout(new GridLayout(2, 3, 10, 1));
        } else {
            escolhas.setLayout(new GridLayout(2, 4, 10, 1));
        }
    }

    String[] colunasCalculaveis(){
        if (scatterplot || histograma || boxplot || boxplot){
            return controlador.colunasNumericas();
        }
        return controlador.titulosColunas();
    }

    String setTituloGeral(){
        String tituloGeral = "";
        String espaco = "  ";
        if (nome.matches("contingencia")) tituloGeral = "CALCULAR GRÁFICO DE CONTINGÊNCIA";
        if (nome.matches("scatterplot")) tituloGeral = "CALCULAR GRÁFICO SCATTERPLOT";
        if (nome.matches("frequencias")) tituloGeral = "CALCULAR GRÁFICO DE FREQUÊNCIAS";
        if (nome.matches("boxplot")) tituloGeral = "CALCULAR GRÁFICO BOXPLOT";
        if (nome.matches("histograma")) tituloGeral = "CALCULAR GRÁFICO DE HISTOGRAMA";

        return espaco + tituloGeral;
    }

    String setInformacao(){
        String tituloGeral = "";

        if (nome.matches("contingencia")) tituloGeral = "o gráfico de contingência";
        if (nome.matches("scatterplot")) tituloGeral = "o gráfico scatterplot";
        if (nome.matches("frequencias")) tituloGeral = "o gráfico de frequências";
        if (nome.matches("boxplot")) tituloGeral = "o gráfico boxplot";
        if (nome.matches("histograma")) tituloGeral = "o gráfico do histograma";
        String info = "<html><center>É possível calcular "+tituloGeral+" selecionando todas as linhas de uma coluna" +
                "<br/>ou realizar o cálculo escolhendo apenas as linhas que possuem um elemento específico em comum</center></html>";
        return info;
    }

    String setInformacaoVariavel(){
        String tituloGeral = "";
        if (nome.matches("contingencia")) tituloGeral = "CONTINGÊNCIA";
        if (nome.matches("scatterplot")) tituloGeral = "SCATTERPLOT";
        if (nome.matches("frequencias")) tituloGeral = "FREQUENCIAS";
        if (nome.matches("boxplot")) tituloGeral = "BOXPLOT";
        if (nome.matches("histograma")) tituloGeral = "HISTOGRAMA";
        String info = tituloGeral+" DE:";
        return info;
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

    public class Classes implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (evento.getStateChange() == ItemEvent.SELECTED){
                classes = numeroClasses.getSelectedItem().toString();
            }

        }
    }

    public class Porcentagem implements ItemListener {
        public void itemStateChanged(ItemEvent evento) {
            if (escolhaPorcentagem.isSelected ())
                porcentagem = true;
            else
                porcentagem = false;

        }
    }

    public class Fechar implements ActionListener {
        JPanel painel;
        Fechar(JPanel painel){
            this.painel = painel;
        }
        public void actionPerformed(ActionEvent evento) {
            informacoes.eliminaPainelCentral(this.painel, 680);
        }
    }

    public class Calcular implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            String[] titulo = tituloTabela();

            String[][] resultado = calcularMetrica();
            String tituloCalcular = "";
            if (contingencia){
                if (resultado != null){
                    tituloCalcular += tituloCalcular(resultado);
                    valor = new GraficoContingencia(colunaCalculavel1, colunaCalculavel2, titulo, resultado);
                    JScrollPane scrollPane = new JScrollPane (valor.getPainel());
                    scrollPane.setPreferredSize(new Dimension( 700,500));
                    calcular.removeAll();
                    calcular.add(scrollPane, BorderLayout.NORTH);

                }
                else {
                    tituloCalcular = "NÃO HÁ ELEMENTOS";
                    calcular.removeAll();
                }
                calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));

            }

            else if (scatterplot){
                if (resultado != null){
                    tituloCalcular += tituloCalcular(resultado);
                    valor = new GraficoScarterplot("Sei lá", resultado, colunaCalculavel1, colunaCalculavel2);
                    JScrollPane scrollPane = new JScrollPane (valor.getPainel());
                    scrollPane.setPreferredSize(new Dimension( 700,500));
                    calcular.removeAll();
                    calcular.add(scrollPane, BorderLayout.NORTH);

                }
                else {
                    tituloCalcular = "NÃO HÁ ELEMENTOS";
                    calcular.removeAll();
                }
                calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));

            }

            else if (frequencias) {
                if (resultado != null){
                    tituloCalcular += tituloCalcular(resultado);
                    valor = new GraficoFrequencias(colunaCalculavel1, titulo, resultado, porcentagem);
                    JScrollPane scrollPane = new JScrollPane (valor.getPainel());
                    scrollPane.setPreferredSize(new Dimension( 700,500));
                    calcular.removeAll();
                    calcular.add(scrollPane, BorderLayout.NORTH);

                }
                else {
                    tituloCalcular = "NÃO HÁ ELEMENTOS";
                    calcular.removeAll();
                }
                calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));

            }

            else if (boxplot) {
                if (resultado != null){
                    tituloCalcular += tituloCalcular(resultado);
                    valor = new GraficoBoxplot(colunaCalculavel1, resultado);
                    JScrollPane scrollPane = new JScrollPane (valor.getPainel());
                    scrollPane.setPreferredSize(new Dimension( 700,500));
                    calcular.removeAll();
                    calcular.add(scrollPane, BorderLayout.NORTH);

                }
                else {
                    tituloCalcular = "NÃO HÁ ELEMENTOS";
                    calcular.removeAll();
                }

                calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));
            }

            else if (scatterplot) {
                if (resultado != null){
                    tituloCalcular += tituloCalcular(resultado);
                    valor = new GraficoScarterplot("Qualquer coisa", resultado, colunaCalculavel1, colunaCalculavel2);
                    JScrollPane scrollPane = new JScrollPane (valor.getPainel());
                    scrollPane.setPreferredSize(new Dimension( 700,500));
                    calcular.removeAll();
                    calcular.add(scrollPane, BorderLayout.NORTH);

                }
                else {
                    tituloCalcular = "NÃO HÁ ELEMENTOS";
                    calcular.removeAll();
                }

                calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));
            }

            else if (histograma) {
                if (resultado != null){
                    tituloCalcular += tituloCalcular(resultado);
                    valor = new GraficoHistograma(colunaCalculavel1, resultado, Integer.parseInt(classes));
                    JScrollPane scrollPane = new JScrollPane (valor.getPainel());
                    scrollPane.setPreferredSize(new Dimension( 700,500));
                    calcular.removeAll();
                    calcular.add(scrollPane, BorderLayout.NORTH);

                }
                else {
                    tituloCalcular = "NÃO HÁ ELEMENTOS";
                    calcular.removeAll();
                }
                calcular.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));
            }


            calcular.repaint();
            painel.revalidate();

        }

        public String[] tituloTabela(){
            if(frequencias || histograma || boxplot){
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

        public String[][] calcularMetrica(){
            Method metodo;
            Object texto;
            try {
                if (frequencias || histograma || boxplot){
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

        public String tituloCalcular(String[][] resultado){
            return " ";
        }
    }
}
