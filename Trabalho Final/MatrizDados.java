import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.Math;


public class MatrizDados {
	ArrayList<String[]> dados;
	int tamanho;

	MatrizDados(File arquivo){
	    int aux = 0;
		int cont = 2;
		dados = new ArrayList<>();

	    try {
				Scanner leitor = new Scanner(arquivo);

				String linhasDoArquivo = new String();
				String cabecalho = new String();
				
				cabecalho = leitor.nextLine();
				String[] cabecalhoEntreVirgulas = cabecalho.split(",");
				dados.add(cabecalhoEntreVirgulas);

				int tamanhoDaLinha = cabecalhoEntreVirgulas.length;

				while(leitor.hasNext()) {

					linhasDoArquivo = leitor.nextLine();
					String[] vetorDaLinha = new String[tamanhoDaLinha];
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
							valor = "0";
							vetorDaLinha[indice] = valor;
							indice++;
						}
					}
				
					dados.add(vetorDaLinha);
					cont++;	
				}

				tamanho = dados.size();

			} catch(Exception e) {
				System.out.println("Erro ao carregar o CSV: "+cont);

		}
	}

	public String getElemento(int linha, int coluna) {
		return this.dados.get(linha+1)[coluna];
	}	

	public String getTituloColuna(int coluna) {
		return this.dados.get(0)[coluna];
	}	

	public String getTituloLinha(int linha) {
		return this.dados.get(linha)[0];
	}

	public int getTamanho() {
		return this.tamanho-1;
	}

	public double media(int coluna){
		int media = 0;
		int menos = 0;
		int tamanho = this.getTamanho();
		//System.out.println("Deu bom aqui")
		for (int i = 0; i < tamanho; i++) {
			if (this.getElemento(i, coluna).equals("NA")) {
				menos--;
			} else {
				media += Integer.parseInt(this.getElemento(i, coluna));
			}
		}
		return media/(tamanho-menos);
	}	

	public double variancia(int coluna){
		double media = this.media(coluna);
		int variancia = 0;
		int menos = 0;
		int tamanho = this.getTamanho();
		for (int i = 0; i < tamanho ; i++) {
			if (this.getElemento(i, coluna).equals("NA")) {
				menos--;

			} else {
				variancia += Math.pow(Integer.parseInt(this.getElemento(i, coluna))-media, 2);
			}
		}
		return variancia/(tamanho-menos);
	}

	public double desvio(int coluna){
		double variancia = this.variancia(coluna);

		return Math.sqrt(variancia);
	}	

	public double mediana(int coluna){
		int valor = 0;
		int menos = 0;
		int tamanho = this.getTamanho();
		int mediana[] = new int[tamanho];

		for (int i = 0; i < tamanho; i++) {
			if (this.getElemento(i, coluna).equals("NA")) {
				menos--;
			} else {
				mediana[i] = Integer.parseInt(this.getElemento(i, coluna));
			}
		}

		Arrays.sort(mediana);
		tamanho -= menos-1;

		if ((tamanho)%2 == 0) {
			valor = (mediana[tamanho/2] + mediana[tamanho/2+1]) / 2;
		} else {
			valor = mediana[(tamanho+1)/2];
		}

		return valor;
	} 	
	
	public String moda(int coluna){
		int aux = 0;
		int maior = 0;
		String moda = "";
		int tamanho = this.getTamanho();
		HashMap<String, Integer> dicionario = new HashMap<String, Integer>();
	
		for (int i = 0; i < tamanho ; i++) {
			if (dicionario.containsKey(this.getElemento(i, coluna))) {
				aux = dicionario.get(this.getElemento(i, coluna));
				aux++;
				dicionario.put(this.getElemento(i, coluna), aux);
			} else {
				aux = 1;
				dicionario.put(this.getElemento(i, coluna), aux);
			}

			if (aux > maior) {
				maior = aux;
				moda = this.getElemento(i, coluna);
			}
		}
		return moda;
	} 

	public double minimo(int coluna){
		int valor = 0;
		int menos = 0;
		int tamanho = this.getTamanho();
		int minimo[] = new int[tamanho];

		for (int i = 0; i < tamanho ; i++) {
			if (!this.getElemento(i, coluna).equals("NA")) {
				minimo[i] = Integer.parseInt(this.getElemento(i, coluna));
			}
		}

		Arrays.sort(minimo);

		return minimo[0];
	} 	

	public double maximo(int coluna){
		int valor = 0;
		int tamanho = this.getTamanho();
		int maximo[] = new int[tamanho];
		for (int i = 0; i < tamanho ; i++) {
			maximo[i] = Integer.parseInt(this.getElemento(i, coluna));
		}

		Arrays.sort(maximo);

		return maximo[tamanho-1];
	} 
}