
public class Universo {

	public static void main(String[] args) {
		 Conjunto naturais = new Conjunto(10);
	        Conjunto impar = new Conjunto(10);
	        Conjunto par = new Conjunto(10);
	        Conjunto primo = new Conjunto(10);
	        

	        int primos[] = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23};
	        for (int i = 0; i < 10; i++){
	            primo.setElemento(primos[i]);
	            naturais.setElemento(i);
	            if (i%2 == 0){
	                par.setElemento(i);
	            } else {
	                impar.setElemento(i);
	            }
	        }
	       

	        if (primo.subConjuntoDe(primo)){
	            System.out.println("O conjunto de primos é subconjunto de si mesmo");
	        } else {
	            System.out.println("Não é subconjunto de si mesmo");
	        }

	        if (par.subConjuntoDe(naturais)){
	            System.out.println("O conjunto par é subconjunto dos naturais");
	        } else {
	            System.out.println("Par não é subconjunto dos naturais");
	        }

	        if (impar.subConjuntoDe(naturais)){
	            System.out.println("O conjunto de impar é subconjunto dos naturais");
	        } else {
	            System.out.println("Impar não é subconjunto dos naturais");
	        }

	        if (primo.subConjuntoDe(naturais)){
	            System.out.println("O conjunto de primo é subconjunto dos naturais");
	        } else {
	            System.out.println("Primo não é subconjunto dos naturais");
	        }

	        Conjunto uniao = par.uniao(impar);

	        if (uniao.subConjuntoDe(naturais) && naturais.subConjuntoDe(uniao)) {
	            System.out.println("Uniao de impar e par é os naturais");
	        } else {
	            System.out.println("Uniao de impar e par não é os naturais");
	        }

	        Conjunto intersec = naturais.intercecao(par);

	        if (intersec.elemento == null) {
	            System.out.println("A intersecção dos naturais e os pares é vazia");
	        } else {
	            System.out.println("A intersecção dos naturais e os pares não é vazia");
	        }

	        Conjunto diferenca = naturais.diferenca(par);
	        System.out.print("O conjunto resultante de Naturais - Pares = ");
	        for (int i = 0; i < diferenca.indice; i++){
	            System.out.print(diferenca.elemento[i] + " ");
	        }
	        System.out.println("");
	        

	}

}
