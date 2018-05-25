import java.io.File;
import java.util.ArrayList;

public class Controler implements Estatistica{
	ArrayList<Estatistica> lista;
	ArrayList<String> locais;
	
	Controler(){
		lista=new ArrayList<Estatistica>();
		locais=new ArrayList<String>();
	}
	
	boolean setArq(String onde) {//metodo de colocar arquivos
		File arquivoCSV = new File(onde);
		csv arquivo=new csv(arquivoCSV);
		
		for(int i=0;i<locais.size();i++) {//laço para procurar o arquivo ja existente e atualiza-lo
			
			if(onde==locais.get(i)) {
				
				lista.set(i, arquivo);
				
				return true;
				
			}
		}
		//é um arquivo novo
		locais.add(onde);
		lista.add(arquivo);
		
		return true;
	}
	
	int getKey(String onde) {
		for(int i=0;i<locais.size();i++) {
			if(onde==locais.get(i)) {
				return i;
			}
		}
		return -999;
	}
	
	Estatistica getFile(int i) {
		try {
			return lista.get(i);
		}catch(Exception e) {
			System.out.println("Erro ao resgatar o CSV: "+i);
			return null;
		}
	}
	double media(int coluna,int file) {
		return lista.get(file).media(coluna);
	}
	
	double variancia(int coluna,int file) {
		return lista.get(file).variancia(coluna);
	}
	
	double desvio(int coluna,int file) {
		return lista.get(file).desvio(coluna);
	}
	
	double mediana(int coluna,int file) {
		return lista.get(file).mediana(coluna);
	}
	
	String moda(int coluna,int file) {
		return lista.get(file).moda(coluna);
	}
	
	double minimo(int coluna,int file) {
		return lista.get(file).minimo(coluna);
	}
	
	double maximo(int coluna,int file) {
		return lista.get(file).maximo(coluna);
	}
	String getElemento(int linha, int coluna,int file) {
		return lista.get(file).getElemento(linha, coluna);
	}
	String getTituloColuna(int coluna,int file) {
		return lista.get(file).getTituloColuna(coluna);
	}
	String getTituloLinha(int linha,int file) {
		return lista.get(file).getTituloLinha(linha);
	}
	
}
