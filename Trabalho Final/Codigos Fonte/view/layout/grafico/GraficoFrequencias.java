package view.layout.grafico;
import com.sun.javafx.charts.Legend;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficoFrequencias implements Grafico{
    ChartPanel painel;
    boolean porcentagem;
    String tituloTabela;

    public GraficoFrequencias(String tituloTabela, String[] titulo, String[][] dados, boolean porcentagem) {

        this.porcentagem = porcentagem;
        this.tituloTabela = tituloTabela;
        String tituloY = porcentagem? "Em porcentagem" : "Repetições";
        JFreeChart barChart = ChartFactory.createBarChart(
                "Frequencia dos elementos de: \""+tituloTabela+"\"",
                tituloTabela,
                tituloY,
                criarDados(titulo, dados),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart, true );
        int tamanho = dados.length < titulo.length? titulo.length : dados.length;
        tamanho = tamanho > 500? 800 : 500;
        chartPanel.setPreferredSize(new java.awt.Dimension(tamanho, 300));

        painel = chartPanel;
    }

    public ChartPanel getPainel() {
        return painel;
    }

    private CategoryDataset criarDados(String[] titulo, String[][] dados) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for (int linha = 0; linha < dados.length; linha++){

            String nome = porcentagem? dados[linha][2].split(" %")[0] : dados[linha][1];
            String informacao = dados[linha][0];
            if (dados[linha][1].matches("^([+-]?\\d*\\.?\\d*)$") && Float.parseFloat(dados[linha][1]) != 0 ){
                dataset.addValue(Float.parseFloat(nome), informacao, tituloTabela);

            }
        }

        return dataset;
    }

}