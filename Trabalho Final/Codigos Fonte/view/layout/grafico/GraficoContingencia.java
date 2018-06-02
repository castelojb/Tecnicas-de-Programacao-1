package view.layout.grafico;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficoContingencia {
    ChartPanel painel;

    public GraficoContingencia(String tituloTabela, String[] titulo, String[][] dados) {

        JFreeChart barChart = ChartFactory.createBarChart(
                tituloTabela,
                "Categoria",
                "Repetição",
                criarDados(titulo, dados),
                PlotOrientation.VERTICAL,
                true, true, false);


        ChartPanel chartPanel = new ChartPanel( barChart );
        int tamanho = dados.length < titulo.length? titulo.length : dados.length;
        chartPanel.setPreferredSize(new java.awt.Dimension(tamanho, tamanho));
        painel = chartPanel;
    }

    public ChartPanel getPainel() {
        return painel;
    }

    private CategoryDataset criarDados(String[] titulo, String[][] dados) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for (int coluna = 1; coluna < titulo.length; coluna++){
            for (int linha = 0; linha < dados.length; linha++){
                dataset.addValue(Float.parseFloat(dados[linha][coluna]), titulo[coluna], dados[linha][0]);
            }
        }


        return dataset;
    }

}