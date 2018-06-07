package visao.janela.central.grafico;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficoContingencia implements Grafico{
    private ChartPanel painel;

    public GraficoContingencia(String tituloTabela1, String tituloTabela2, String[] titulo, String[][] dados) {
        JFreeChart barChart = ChartFactory.createBarChart("\""+tituloTabela1+"\" VS \""+tituloTabela2+"\"",
                tituloTabela2, "Repetições", criarDados(titulo, dados), PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart, true );
        chartPanel.setPreferredSize(new java.awt.Dimension(1500, 5000));

        painel = chartPanel;
    }

    public ChartPanel getPainel() {
        return painel;
    }

    private CategoryDataset criarDados(String[] titulo, String[][] dados) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for (int coluna = 1; coluna < titulo.length; coluna++){
            for (int linha = 0; linha < dados.length; linha++){
                if (Float.parseFloat(dados[linha][coluna]) != 0) dataset.addValue(Float.parseFloat(dados[linha][coluna]), titulo[coluna], dados[linha][0]);
            }
        }

        return dataset;
    }

    private boolean legenda(String[][] dados){
        for (int linha = 0; linha < dados[0].length; linha++){
            if (dados[1][linha].length() > 10) return false;
            if (dados[0][linha].length() > 10) return false;
        }
        return false;
    }

}