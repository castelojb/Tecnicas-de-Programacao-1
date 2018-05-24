
public interface Estatistica {
	public String getElemento(int linha, int coluna);
	public String getTituloColuna(int coluna);
	public String getTituloLinha(int linha);
	public int getTamanho();
	public double media(int coluna);
	public double variancia(int coluna);
	public double desvio(int coluna);
	public double mediana(int coluna);
	public String moda(int coluna);
	public double minimo(int coluna);
	public double maximo(int coluna);
	
}
