import java.util.ArrayList;

public class Controler {
	ArrayList<Estatistica> lista;
	
	Controler(){
		lista=new ArrayList<Estatistica>();
	}
	
	boolean setFile(Estatistica arq) {

		try {
			lista.add(arq);
			return true;
		}catch(Exception e) {
			return false;
		}
	} 
	
	Estatistica getFile(int i) {
		try {
			return lista.get(i);
		}catch(Exception e) {
			System.out.println("Erro ao resgatar o CSV: "+i);
			return null;
		}
	}
	
	
}
