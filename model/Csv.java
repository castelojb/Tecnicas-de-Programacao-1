package model;


import java.util.*;
import java.io.File;

public class Csv {
    private HashMap<String, Integer> cabecalhoMap;
    private String[] cabecalhoArray;
	private ArrayList<String[]> dados;
    private int numeroLinhas;
    private int numeroColunas;
    private boolean erro = false;

	protected Csv(File arquivo){
	    int aux = 0;
		int cont = 1;
		dados = new ArrayList<>();
		cabecalhoMap = new HashMap<>();

	    try {
			Scanner leitor = new Scanner(arquivo);

	 		String linhasDoArquivo;
			String cabecalhoString = leitor.nextLine();
			cabecalhoArray = cabecalhoString.split(",");
			numeroColunas = this.cabecalhoArray.length;

			for (int i = 0; i < numeroColunas; i++){
				this.cabecalhoMap.put(cabecalhoArray[i], i);
			}
			while(leitor.hasNext()) {

				linhasDoArquivo = leitor.nextLine();
				String[] vetorDaLinha = new String[numeroColunas];
				String[] valoresEntreVirgulas = linhasDoArquivo.split(",");
				String valor = "";
				int indice = 0;
				for (int i = 0; i < valoresEntreVirgulas.length; i++) {
					if (valoresEntreVirgulas[i].length() != 0) {

						char a = valoresEntreVirgulas[i].charAt(0);
						char b =  valoresEntreVirgulas[i].charAt(valoresEntreVirgulas[i].length()-1);
						if (a == '"') {
							valor = "";
							aux = 1;
						}
						if (b == '"') {
							valor += valoresEntreVirgulas[i];
							vetorDaLinha[indice] = valor;
							indice++;
							aux = 0;
						}
						else if (aux == 0) {
							valor = valoresEntreVirgulas[i];
							vetorDaLinha[indice] = valor;
							indice++;
						}
						if (aux == 1) {
							valor += valoresEntreVirgulas[i]+",";
						}

					} else {
						vetorDaLinha[indice] = valor;
						indice++;
					}
				}
				dados.add(vetorDaLinha);
				cont++;
			}

			numeroLinhas = dados.size();
   
			} catch(Exception e) {
	            erro = true;
				System.out.println("Erro ao carregar o CSV: "+cont+"\n"+e);

		}
	}

    protected boolean getErro(){
        return this.erro;
    }

    protected HashMap<String, Integer> getCabecalhoMap(){
	    return this.cabecalhoMap;
    }

    protected String[] getCabecalhoArray(){
	    return this.cabecalhoArray;
    }

    protected ArrayList<String[]> getDados(){
	    return this.dados;
    }

    protected int getNumeroLinhas() {
        return this.numeroLinhas;
    }

    protected int getNumeroColunas() {
        return this.numeroColunas;
    }

    protected String getElemento(int linha, int coluna) {
        return this.dados.get(linha)[coluna];
    }
/*

    protected String getTituloColuna(int coluna) {
        return this.cabecalho[coluna];
    }

    protected String getTituloLinha(int linha) {
        return this.dados.get(linha)[0];
    }
*/


}
