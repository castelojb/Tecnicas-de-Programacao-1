package controller;

import model.Metricas;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Controler{
	public Metricas dados;

	public Controler(String arquivo){
	    try{
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
	    for (int i = 1; i < valores[0]; i++){
	        if (i == 1) elementos += String.valueOf(valores[i]);
	        else elementos += (", " + String.valueOf(valores[i]));
        }
        return elementos;
    }

    public String moda(String colunaFixa, String nome, String coluna) {
        double[] valores = dados.moda(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna));
        String elementos = "";
        for (int i = 1; i < valores[0]; i++){
            if (i == 1) elementos += String.valueOf(valores[i]);
            else elementos += (", "+String.valueOf(valores[i]));
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

    public String[][] frequenciasT(String coluna){
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

    public String[][] frequenciasT(String colunaFixa, String nome, String coluna){
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
        HashMap<ArrayList<String>, String[][]> resultado = dados.contingencia(dados.indiceCabecalho(colunaFixa), nome, dados.indiceCabecalho(coluna1), dados.indiceCabecalho(coluna2));
        if (resultado != null){
            Set<ArrayList<String>> cabaca = resultado.keySet();
            ArrayList<String> auxiliar = new ArrayList<>();
            for (ArrayList<String> elemento : cabaca){
                auxiliar = elemento;
            }

            String[] valor = new String[auxiliar.size()];
            for (int indice = 0; indice < auxiliar.size(); indice++){
                valor[indice] = auxiliar.get(indice);
            }
            String[][] retorno = new String[resultado.get(auxiliar).length+1][];
            retorno[0] = valor;

            System.arraycopy(resultado.get(auxiliar), 0, retorno, 1, resultado.get(auxiliar).length);
            System.out.println(retorno[0].length+"      "+retorno.length);

            return retorno;
        }
        return null;
    }
}

