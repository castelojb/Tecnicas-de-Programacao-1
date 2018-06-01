class Conjunto {
	Object elemento[];//colecao de termos
	int indice;// tamanho atual do conjunto
	int total;//tamanho declarado do conjunto
	
	Conjunto(int i){
		elemento= new Object[i];//conjunto de tamanho i
		indice=0;
		total=i;
	}
	
	boolean pertinencia(Object objeto) {//verifica a presenca de um objeto no conjunto
		for(int aux=0;aux<this.indice;aux++) {
			if(objeto==(this.elemento[aux])) {
				return true;//achou o objeto
			}
		}
		return false;// nao achou :(
	}
	
	boolean setElemento(Object objeto) {//retorna false se der erro e true se deu certo
		if (indice==total) {//conjunto cheio
			Object expancao[]= new Object[this.total+10];//um vetor para expandir
			for(int i=0; i<this.indice+1; i++){//copia o conjunto para expancao
  				 expancao[i]=this.elemento[i];
			}
			this.elemento=expancao;//elemento recebe expancao
			this.elemento[this.indice]=objeto;
			this.indice++;
			this.total= this.total+10;//aumentou o total em 10
			return true;
		}
		if(this.pertinencia(objeto)) {// ja tinha ele aqui
			return true;
		}
		this.elemento[(this.indice)]=objeto;
		this.indice=this.indice +1;
		return true;// deu bom
	}
	
	Object getElemento(int posicao) {//retorna o elemento do conjunto
		if(posicao>this.total-1) {//fora do conjunto
			return -1;//erro
		}
		if(posicao<0) {
			return -1;//erro por estar fora do conjunto 
		}
		return this.elemento[posicao];
	}
	
	int getIndice() {//indice elemento atual
		return this.indice;
	}
	
	int getTotal() {//tamanho total do conjunto
		return this.total;
	}
	
	boolean subConjuntoDe(Conjunto B) {//seria A subconjunto_de B
		for(int aux=0;aux<this.getIndice();aux++) {
			if(B.pertinencia(this.elemento[aux])==false) {
				return false;//se nao tiver pertinencia do elemento A em B, A n eh sub de B
			}
		}
		return true;
		
	}
	
	Conjunto uniao(Conjunto B) {//A U B
		
		Conjunto Uniao;
		Uniao=new Conjunto(10);
		
		if(B.subConjuntoDe(this)) {// B estava em A
			return this;
		}
		for(int aux=0;aux<B.indice;aux++) {
			Uniao.setElemento(B.elemento[aux]);
		}
		for(int aux = 0; aux<this.indice; aux++) {
			Uniao.setElemento(this.elemento[aux]);
		}
		return Uniao;
		
		
	}
	
	Conjunto intercecao(Conjunto B) {// A inter B
		
		Conjunto Intercecao;
		Intercecao= new Conjunto(10);
		for (int aux=0;aux<this.indice;aux++) {//percorre todos os elementos de A
			if(B.pertinencia(this.elemento[aux])) {// eu posso ver que o elemento de A na posicao aux esta em B
				Intercecao.setElemento(this.elemento[aux]);
			}
		}
		
		
		return Intercecao;
	}
	
	Conjunto diferenca(Conjunto B) {// A-B processo inverso de intercecao e retorna o conjunto desejado
		Conjunto Diferenca;// conjunto diferenca
		Diferenca= new Conjunto(10);
		for(int aux=0; aux<B.indice ; aux++) {
			if (this.pertinencia(B.elemento[aux])==false) {// se n estiver em A entao ele faz parte da Diferenca
				Diferenca.setElemento(B.elemento[aux]);
			}
		}
		for(int aux=0; aux<this.indice; aux++) {
			if(B.pertinencia(this.elemento[aux])==false) {// mesma coisa que o laco anterior apenas na outra ordem
				Diferenca.setElemento(this.elemento[aux]);
			}
		}
		return Diferenca;
		
		
		
	}
	
}