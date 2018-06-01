package layout.grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import java.util.Map;

public class GraficoBarra{
    ChartPanel charPanel;

    private static final long serialVersionUID = 1L;

    public GraficoBarra(String chartTitle, Map<String, Integer> dicionario) {

        // Isso irá criar o conjunto de dados
        PieDataset dataset = createDataset(dicionario);

        // com base no conjunto de dados que criamos o gráfico
        JFreeChart chart = createChart(dataset, chartTitle);

        // vamos colocar o gráfico em um painel
        ChartPanel chartPanel = new ChartPanel(chart);

        // default tamanho
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        // adiciona a nossa aplicação
        this.charPanel = chartPanel;

    }

    /**
     * Cria um conjunto de dados de amostra
     */

    private PieDataset createDataset( Map<String, Integer> dicionario) {
        DefaultPieDataset result = new DefaultPieDataset();

        for (String key : dicionario.keySet()) {
            Integer value = dicionario.get(key);
            result.setValue(key, value);

        }
        return result;

    }

    /**
     * Cria um gráfico
     */

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset,true, true, false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(1f);
        return chart;

    }

}