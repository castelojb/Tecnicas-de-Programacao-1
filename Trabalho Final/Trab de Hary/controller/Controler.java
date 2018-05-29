package controller;

import model.Metricas;
import java.io.File;
import java.util.ArrayList;

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

}

