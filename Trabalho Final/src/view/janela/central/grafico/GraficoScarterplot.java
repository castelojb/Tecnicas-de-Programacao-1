package view.janela.central.grafico;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraficoScarterplot implements Grafico {
    ChartPanel painel;
    public GraficoScarterplot(String title, String[][] valores, String titulo1, String titulo2) {

        // Create dataset
        XYDataset dataset = createDataset(valores, titulo1, titulo2);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Scatter Plot de "+titulo1+" VS "+titulo2,
                titulo1, titulo2, dataset);

        chart.removeLegend();
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));


        this.painel = new ChartPanel(chart);
    }

    public ChartPanel getPainel() {
        return painel;
    }

    private XYDataset createDataset(String[][] valores, String titulo1, String titulo2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("");

        //Boys (Age,weight) series
        for (int linha = 0; linha < valores[0].length; linha++){
            if (valores[0][linha].matches("^([+-]?\\d*\\.?\\d*)$") && valores[1][linha].matches("^([+-]?\\d*\\.?\\d*)$")) {
                series1.add(Float.parseFloat(valores[0][linha]), Float.parseFloat(valores[1][linha]));
            }
        }

        dataset.addSeries(series1);

        return dataset;
    }

}