package visao.janela.central.grafico;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;


public class GraficoBoxplot implements Grafico{

    private ChartPanel painel;

    public GraficoBoxplot(String tituloTabela, String[][] dados) {

        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Boxplot em Relação \""+tituloTabela+"\"", "",
                tituloTabela, criarDados( dados),
                legenda(dados));

        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setMeanVisible(false);
        renderer.setMedianVisible(true);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRenderer(renderer);
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
    private boolean legenda(String[][] dados){
        for (int linha = 0; linha < dados[0].length; linha++){
            if (dados[0][linha].matches("^([+-]?\\d*\\.?\\d*)$") ) {
                if (dados[0][linha].length() > 10) return false;
            }
        }
        return true;
    }


}