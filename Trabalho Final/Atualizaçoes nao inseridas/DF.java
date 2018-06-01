import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class DF {
	Map<String,Integer> frequencia(int coluna) {
		String[] chaves= elementosSemRepeticao(coluna);
		Map<String,Integer> frequencia= new HashMap<>();
		Integer aux=0;
		//inicializacao da frequencia
		
		for(int i=0;i<chaves.length;i++) {
			frequencia.put(chaves[i], aux);
		}
		
		int tamanho = this.getNumeroLinhas();
		
		for (int linha = 0; linha < tamanho ; linha++) {
			frequencia.set(getElemento(linha, coluna), frequencia.get(getElemento(linha, coluna))++); 
		}
		
		return frequencia;

		
	}
	
	
}
