import java.io.File;

public class Main {
	public static void main(String[] args) {
		
		File arquivoCSV = new File(args[0]);
		
		MatrizDados arquivo = new MatrizDados(arquivoCSV);
		
		System.out.println("Tamanho: "+arquivo.getTamanho());
		
		System.out.println("media de "+arquivo.getTituloColuna(4)+" = "+arquivo.media(4));
		System.out.println("moda de "+arquivo.getTituloColuna(1)+" = "+arquivo.moda(1));
		System.out.println("moda de "+arquivo.getTituloColuna(4)+" = "+arquivo.moda(4));
		System.out.println("mediana de "+arquivo.getTituloColuna(4)+" = "+arquivo.mediana(4));
		System.out.println("minimo de "+arquivo.getTituloColuna(4)+" = "+arquivo.minimo(4));
		System.out.println("maximo de "+arquivo.getTituloColuna(4)+" = "+arquivo.maximo(4));
		System.out.println("variancia de "+arquivo.getTituloColuna(4)+" = "+arquivo.variancia(4));
		System.out.println("desvio padrao de "+arquivo.getTituloColuna(4)+" = "+arquivo.desvio(4));

	}
}