package controller;

import model.Metricas;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Controlador {
    private Metricas dados;
    public String nomeArquivo;

    public Controlador(String arquivo){
        try{
            this.nomeArquivo =arquivo;
            File arquivoCSV = new File(arquivo);
            dados = new Metricas(arquivoCSV);

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void setControler(String arquivo){
        try{
            nomeArquivo = arquivo;
            File arquivoCSV = new File(arquivo);
            dados = new Metricas(arquivoCSV);

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public  String[] titulosColunas(){
        return dados.titulosColunas();
    }

    public String[] elementosColunaSemRepeticao(String coluna){
        return dados.elementosSemRepeticao(dados.indiceCabecalho(coluna));

    }
    public boolean eColunaNumerica(int coluna){
        return dados.colunaNumerica(coluna);
    }
    public boolean eColunaNumerica(int coluna, String elemento){
        return dados.colunaNumerica(coluna, elemento);
    }

    public String[] colunasNumericas(){
        return dados.colunasNumericas();
    }

    public boolean erro(){
        return this.dados.erroArquivo();
    }

    public String media(String coluna){
        return String.valueOf(dados.media(dados.indiceCabecalho(coluna)));
    }
    public String media(String colunaFixa, String nome, String coluna){
        return String.valueOf(dados.media(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }

    public String variancia(String coluna) {
        return String.valueOf(dados.variancia(dados.indiceCabecalho(coluna)));
    }

    public String variancia(String colunaFixa, String nome, String coluna) {
        return String.valueOf(dados.variancia(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }
    public String desvio(String coluna) {
        return String.valueOf(dados.desvio(dados.indiceCabecalho(coluna)));
    }

    public String desvio(String colunaFixa, String nome, String coluna) {
        return String.valueOf(dados.desvio(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));

    }

    public String mediana(String coluna) {
        return String.valueOf(dados.media(dados.indiceCabecalho(coluna)));
    }

    public String mediana(String colunaFixa, String nome, String coluna) {
        return String.valueOf(dados.mediana(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }

    public String moda(String coluna) {
        double[] valores = dados.moda(dados.indiceCabecalho(coluna));
        String elementos = "";
        if (valores[0] == 0) elementos = "é Amodal, ou seja, não possuem moda.";
        else if (valores[0] == 2) elementos = "possue moda igual a: ";
        else if (valores[0] == 3) elementos = "são Bimodais com modas: ";
        else elementos = "são Multimodais com modas: ";

        for (int i = 1; i < valores[0]; i++){
            if (i == 1) elementos += String.valueOf(valores[i]);
            else if ((i != 1) && (i == valores[0]-1)) elementos += (" e " + String.valueOf(valores[i]));
            else elementos += (", " + String.valueOf(valores[i]));

        }
        return elementos;
    }

    public String moda(String colunaFixa, String nome, String coluna) {
        double[] valores = dados.moda(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna));

        String elementos = "";
        if (valores[0] == 0) elementos = "é Amodal, ou seja, não possuem moda.";
        else if (valores[0] == 2) elementos = "possue moda igual a: ";
        else if (valores[0] == 3) elementos = "são Bimodais com modas: ";
        else elementos = "são Multimodais com modas: ";

        for (int i = 1; i < valores[0]; i++){
            if (i == 1) elementos += String.valueOf(valores[i]);
            else if ((i != 1) && (i == valores[0]-1)) elementos += (" e " + String.valueOf(valores[i]));
            else elementos += (", " + String.valueOf(valores[i]));

        }

        return elementos;
    }

    public String minimo(String coluna) {
        return String.valueOf(dados.minimo(dados.indiceCabecalho(coluna)));
    }

    public String minimo(String colunaFixa, String nome, String coluna) {
        return String.valueOf(dados.minimo(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }

    public String maximo(String coluna) {
        return String.valueOf(dados.maximo(dados.indiceCabecalho(coluna)));
    }

    public String maximo(String colunaFixa, String nome, String coluna) {
        return String.valueOf(dados.maximo(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }

    public String kurtosis(String coluna){
        return String.valueOf(dados.kurtosis(dados.indiceCabecalho(coluna)));
    }

    public String kurtosis(String colunaFixa, String nome, String coluna){
        return String.valueOf(dados.kurtosis(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }


    public String skewness(String coluna){
        return String.valueOf(dados.skewness(dados.indiceCabecalho(coluna)));
    }

    public String skewness(String colunaFixa, String nome, String coluna){
        return String.valueOf(dados.skewness(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna)));
    }

    public String[][] covariancia(String coluna1, String coluna2){
        ArrayList<Double[]> resultado = dados.covariancia(dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));
        if(resultado != null) {
            String[][] covariancia = new String[resultado.size()][5];
            for (int linha = 0; linha < resultado.size(); linha++){
                for (int coluna = 0; coluna < 5; coluna++){
                    covariancia[linha][coluna] = String.valueOf(resultado.get(linha)[coluna]);
                }

            }
            return covariancia;
        }
        return null;

    }

    public String[][] covariancia(String colunaFixa, String nome, String coluna1, String coluna2){
        ArrayList<Double[]> resultado = dados.covariancia(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));

        if (resultado != null){
            String[][] covariancia = new String[resultado.size()][5];
            for (int linha = 0; linha < resultado.size(); linha++){
                for (int coluna = 0; coluna < 5; coluna++){
                    covariancia[linha][coluna] = String.valueOf(resultado.get(linha)[coluna]);
                }

            }
            return covariancia;
        }
        return null;

    }

    public String[][] frequencias(String coluna){
        HashMap<String, Float[]> resultado = dados.frequenciasT(dados.indiceCabecalho(coluna));
        String[][] frequencias = new String[resultado.size()][3];
        String[] chaves = dados.elementosSemRepeticao(dados.indiceCabecalho(coluna));
        for (int linha = 0; linha < chaves.length; linha++){

            frequencias[linha][0] = chaves[linha];
            frequencias[linha][1] = String.valueOf(resultado.get(chaves[linha])[0]);
            frequencias[linha][2] = String.valueOf(resultado.get(chaves[linha])[1]) + " %";
        }

        return frequencias;
    }

    public String[][] frequencias(String colunaFixa, String nome, String coluna){
        HashMap<String, Float[]> resultado = dados.frequenciasT(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna));
        if (resultado != null){
            String[][] frequencias = new String[resultado.size()][3];
            String[] chaves = dados.elementosSemRepeticao(dados.indiceCabecalho(coluna));
            int linha = 0;

            for (int indice = 0; indice < chaves.length; indice++){
                if (resultado.containsKey(chaves[indice])){
                    frequencias[linha][0] = chaves[indice];
                    frequencias[linha][1] = String.valueOf(resultado.get(chaves[indice])[0]);
                    frequencias[linha][2] = String.valueOf(resultado.get(chaves[indice])[1]) + " %";
                    linha++;
                }
            }

            return frequencias;
        }
        return null;
    }

    public String[][] coeficiente(String coluna1, String coluna2){
        ArrayList<Double[]> resultado = dados.coeficiente(dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));

        if (resultado != null){
            String[][] covariancia = new String[resultado.size()][5];

            for (int linha = 0; linha < resultado.size(); linha++){
                for (int coluna = 0; coluna < 5; coluna++){
                    covariancia[linha][coluna] = String.valueOf(resultado.get(linha)[coluna]);
                }

            }
            return covariancia;
        }
        return null;

    }

    public String[][] coeficiente(String colunaFixa, String nome, String coluna1, String coluna2){
        ArrayList<Double[]> resultado = dados.coeficiente(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));

        if (resultado != null) {
            String[][] covariancia = new String[resultado.size()][5];
            for (int linha = 0; linha < resultado.size(); linha++){
                for (int coluna = 0; coluna < 5; coluna++){
                    covariancia[linha][coluna] = String.valueOf(resultado.get(linha)[coluna]);
                }

            }
            return covariancia;
        }
        return null;

    }

    public String[][] contingencia(String coluna1, String coluna2){
        return dados.contingencia(dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));
    }

    public String[][] contingencia(String colunaFixa, String nome, String coluna1, String coluna2){
        String[][] resultado = dados.contingencia(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));
        return resultado;
    }

    public String[][] scatterplot(String coluna1, String coluna2){
        String[][] resultado = new String[2][];
        resultado[0] = dados.getElementosColuna(dados.indiceCabecalho(coluna1));
        resultado[1] = dados.getElementosColuna(dados.indiceCabecalho(coluna2));
        return resultado;
    }

    public String[][] scatterplot(String colunaFixa, String nome, String coluna1, String coluna2){
        String[][] valor = dados.scatterplot(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));
        String[][] resultado = new String[2][Integer.parseInt(valor[2][0])];

        for (int linha = 0; linha < Integer.parseInt(valor[2][0]); linha++){
            resultado[0][linha] = valor[0][linha];
            resultado[1][linha] = valor[1][linha];
        }

        return resultado;
    }

    public String[][] histograma(String coluna){
        String[][] resultado = new String[2][];
        resultado[0] = dados.getElementosColuna(dados.indiceCabecalho(coluna));
        String[] valores = new String[2];
        valores[0] = String.valueOf(dados.minimo(dados.indiceCabecalho(coluna)));
        valores[1] = String.valueOf(dados.maximo(dados.indiceCabecalho(coluna)));

        resultado[1] = valores;
        return resultado;
    }

    public String[][] histograma(String colunaFixa, String nome, String coluna){
        double[][] valor = dados.histograma(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna));
        String[][] resultado = new String[2][(int)valor[1][0]];
        for (int linha = 0; linha < valor[1][0]; linha++){
            resultado[0][linha] = String.valueOf(valor[0][linha]);
        }

        String[] valores = new String[2];
        valores[0] = String.valueOf(valor[2][0]);
        valores[1] = String.valueOf(valor[2][1]);

        resultado[1] = valores;
        return resultado;
    }

    public String[][] boxplot(String coluna){
        String[][] resultado = new String[1][];
        resultado[0] = dados.getElementosColuna(dados.indiceCabecalho(coluna));
        return resultado;
    }

    public String[][] boxplot(String colunaFixa, String nome, String coluna){
        String[][] auxiliar = histograma(colunaFixa, nome, coluna);
        String[][] resultado = new String[1][];
        resultado[0] = auxiliar[0];
        return resultado;
    }

    public String[][] dadosCSV(){
        String[][] csv = new String[dados.getDadosCSV().size()][];
        ArrayList<String[]> dadosCSV = dados.getDadosCSV();
        for (int linha = 0; linha < dados.getDadosCSV().size(); linha++){
            csv[linha] = dadosCSV.get(linha).clone();
        }
        return csv;
    }
}

