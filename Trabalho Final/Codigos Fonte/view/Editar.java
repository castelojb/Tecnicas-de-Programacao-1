package view;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import view.layout.Botao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EmptyStackException;


import controller.Controler;


public class Editar implements ActionListener{
    private Color corFundo = new Color(10, 10, 10);
    private Color corBotaoBuscar = new Color(10, 50, 10);
    private Color corBotaoAnalisar = new Color(5, 50, 100);
    private Color corBranca = new Color(255, 255, 255);
    private Color corTeste = new Color(255, 0, 255);
    Font fonteBotao = new Font("SansSerif", Font.BOLD, 25);

    private Controler controlador;
    private PainelInformacoes informacoes;
    private String nome;
    private JTable valor;

    private JPanel painel;
    private JPanel centro;
    private String[][] tabelaCSV;
    private String[] tituloTabela;

    Editar(PainelInformacoes informacoes, String nome){
        this.controlador = informacoes.getControlador();
        this.informacoes = informacoes;
        this.nome = nome;
        tabelaCSV = controlador.dadosCSV();
        tituloTabela = controlador.titulosColunas();
    }
    public void actionPerformed(ActionEvent evento) {
        painel = new JPanel();
        painel.setPreferredSize(new Dimension(740, 900));
        painel.setLayout(new BorderLayout());

        centro = new JPanel();
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


        String nomeArquivo = controlador.nomeArquivo.replace("\\", "-");
        String[] nomeArquivoArray = nomeArquivo.split("-");
        String tituloCalcular = "ARQUIVO: "+nomeArquivoArray[nomeArquivoArray.length-1];

        valor = new JTable(tabelaCSV, tituloTabela){
            public boolean isCellEditable(int row,int column){
                Object o = getValueAt(row,column);
                return true;
            }
        };
        valor.setFillsViewportHeight(true);
        valor.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane (valor);
        scrollPane.setPreferredSize(new Dimension( 700,500));

        centro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), tituloCalcular, TitledBorder.CENTER, TitledBorder.TOP));
        centro.add(scrollPane, BorderLayout.NORTH);


        Botao botaoSalvar = new Botao("SALVAR");
        botaoSalvar.setMargin(new Insets(0, 0,0 , 0));
        botaoSalvar.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        botaoSalvar.addActionListener(new Salvar());

        Botao botaoSalvarComo = new Botao("SALVAR COMO");
        botaoSalvarComo.setMargin(new Insets(0, 0,0 , 0));
        botaoSalvarComo.configurarFonteCorFundo(fonteBotao, corBranca, corBotaoBuscar);
        botaoSalvarComo.addActionListener(new SalvarComo());


        //Só aparece quando aperta o botao calcular
        JLabel resultado = new JLabel("     RESULTADO");
        salvar.add(botaoSalvar);
        salvar.add(botaoSalvarComo);
        painel.add(centro, BorderLayout.CENTER);
        painel.add(salvar, BorderLayout.SOUTH);

        informacoes.adicionaPainelCentral(painel);

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

    public class Salvar implements ActionListener{
        public void actionPerformed(ActionEvent evento){
            salvarArquivo(controlador.nomeArquivo);
        }
    }


    public class SalvarComo implements ActionListener{
        public void actionPerformed(ActionEvent evento){
            JFileChooser arquivo;
            LookAndFeel anteriro = UIManager.getLookAndFeel();
            String onde;
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                arquivo = new JFileChooser();
                UIManager.setLookAndFeel(anteriro);

            } catch (Exception e) {
                arquivo = new JFileChooser();
            }

            arquivo.setDialogTitle("Salvar arquivo como");
            FileNameExtensionFilter filtroCSV = new FileNameExtensionFilter("Arquivos CSV", "csv");
            arquivo.addChoosableFileFilter(filtroCSV);

            if(arquivo.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION){
                onde = arquivo.getSelectedFile().getAbsolutePath();
                salvarArquivo(onde);
            }
        }
    }

    private ArrayList<String> prepararArquivo(){
        try {
            ArrayList<String> lines = new ArrayList<>();
            String linhaCSV = "";
            for (int coluna = 0; coluna < valor.getColumnCount(); coluna++){
                linhaCSV += valor.getColumnName(coluna);
                if (coluna < tituloTabela.length-1) linhaCSV+=",";
            }
            lines.add(linhaCSV);
            for (int linha = 0; linha < valor.getRowCount(); linha++){
                String linhaCSVs;
                linhaCSVs = "";
                for (int coluna = 0; coluna < valor.getColumnCount(); coluna++){
                    String elemento = String.valueOf(valor.getValueAt(linha, coluna));
                    if (elemento.contains(",") && !elemento.contains("\"")) {
                        JOptionPane.showMessageDialog (new JFrame(), "Não é possível colocar: \""+elemento+"\" na linha "+linha+1+" coluna "+coluna+1+".\nRemova a vírgula, ou coloque a palavra entre aspas e tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                        throw new EmptyStackException();
                    }
                    if (controlador.eColunaNumerica(coluna) && (elemento.matches("^([+-]?\\d*\\.?\\d*)$") || elemento.equals("NA"))){
                        linhaCSVs = linhaCSVs + valor.getValueAt(linha, coluna);
                        if (coluna < valor.getColumnCount()-1) linhaCSVs += ",";
                    }
                    else if (!controlador.eColunaNumerica(coluna)){
                        linhaCSVs = linhaCSVs + valor.getValueAt(linha, coluna);
                        if (coluna < valor.getColumnCount()-1) linhaCSVs += ",";
                    }

                    else{
                        JOptionPane.showMessageDialog (new JFrame(), "Não é possível colocar"+elemento+"em uma coluna numérica", "Erro", JOptionPane.ERROR_MESSAGE);
                        throw new EmptyStackException();
                    }
                }

                lines.add(linhaCSVs);
            }
        return lines;

        } catch (Exception e){
            System.out.println("Não deu " + e);
            return null;
        }
    }

    private void salvarArquivo(String onde){
        try {
            ArrayList<String> arquivo = prepararArquivo();
            if (arquivo == null) throw new EmptyStackException();
            Path file = Paths.get(onde);
            Files.write(file, arquivo, Charset.defaultCharset());
            controlador.setControler(controlador.nomeArquivo);
            Icon iconeOK = new ImageIcon(getClass().getResource("ok.png"));
            JOptionPane.showMessageDialog (new JFrame(), "CSV atualizado com sucesso", "FEITO", JOptionPane.INFORMATION_MESSAGE, iconeOK);

        } catch (Exception e){
            System.out.println("Não deu"+e);
        }
    }
}