package view.layout.grafico;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;


public class GraficoBoxplot implements Grafico{

    ChartPanel painel;
    boolean porcentagem;
    String tituloTabela;

    public GraficoBoxplot(String tituloTabela, String[][] dados) {
        this.tituloTabela = tituloTabela;

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Boxplot em Relação \""+tituloTabela+"\"", "",
                tituloTabela, criarDados( dados),
                false);

        ChartPanel chartPanel = new ChartPanel( chart, true );
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

        painel = chartPanel;
    }

    public ChartPanel getPainel() {
        return painel;
    }

    private DefaultBoxAndWhiskerCategoryDataset criarDados( String[][] dados) {
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        List<Number> dadosFinais = new ArrayList<>();

        for (int linha = 0; linha < dados[0].length; linha++){
            if (dados[0][linha].matches("^([+-]?\\d*\\.?\\d*)$") ) {
                dadosFinais.add(Float.parseFloat(dados[0][linha]));
            }
        }

        dataset.add(dadosFinais, "", "");
        return dataset;
    }

}