package view.janela.central.grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import java.awt.*;

public class GraficoHistograma implements Grafico{
    ChartPanel painel;
    boolean porcentagem;
    String tituloTabela;
    int classes;

    public GraficoHistograma(String tituloTabela, String[][] dados, int classes) {
        this.classes = classes;
        this.tituloTabela = tituloTabela;

        PlotOrientation orientation = PlotOrientation.VERTICAL;

        JFreeChart chart = ChartFactory.createHistogram("Histograma de \""+tituloTabela+"\"", tituloTabela,
                "Quantidade de Ocorrencia", criarDados( dados), orientation,
                false,
                false,
                false);

        chart.setBackgroundPaint(Color.white);
        ChartPanel chartPanel = new ChartPanel( chart, true );
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

        painel = chartPanel;
    }

    public ChartPanel getPainel() {
        return painel;
    }

    private HistogramDataset criarDados( String[][] dados) {
        HistogramDataset dataset = new HistogramDataset();
        double[] valores = new double[dados[0].length];
        int tamanho = 0;
        for (int linha = 0; linha < dados[0].length; linha++){
            if (dados[0][linha].matches("^([+-]?\\d*\\.?\\d*)$") ) {
                valores[linha] = (Float.parseFloat(dados[0][linha]));
                tamanho++;
            }
        }
        double[] dadosFinais = new double[tamanho];
        for (int i = 0; i < tamanho; i++) dadosFinais[i] = valores[i];

        dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Hist", dadosFinais, classes, Double.parseDouble(dados[1][0]), Double.parseDouble(dados[1][1]));
        return dataset;
    }

}