package model;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class Metricas extends Csv {

    public Metricas(File file){
        super(file);
    }


    protected boolean colunaNumerica(int coluna){
        int linha = 0;
        try{
            String elemento = getElemento(linha, coluna);
            while (elemento.equals("NA") || elemento.equals("")){
                linha++;
                elemento = getElemento(linha, coluna);
            }
        } catch (Exception e){
            System.out.println("Esse CSV tá meio bugado");
        }

        if (getElemento(linha, coluna).matches("^([+-]?\\d*\\.?\\d*)$")){
            return true;
        } else{
            return false;
        }
    }

    protected boolean eNumerico(int linha, int coluna){
        if (getElemento(linha, coluna).matches("^([+-]?\\d*\\.?\\d*)$")) return true;
        else return false;
    }

    public int indiceCabecalho(String nome){
        return getCabecalhoMap().get(nome);
    }

    public double elementoNumerico(int linha, int coluna){
        return Double.parseDouble(getElemento(linha, coluna));
    }

    public boolean erroArquivo(){
        return this.getErro();
    }

    public String tituloColuna(int coluna) {
        return this.getCabecalhoArray()[coluna];
    }


    public String[] titulosColunas(){
        return getCabecalhoArray();
    }

    public String[] elementosSemRepeticao(int coluna){
        int tamanho = this.getNumeroLinhas();
        Set<String> semRepeticao = new HashSet<String>();
        int indice = 0;
        if (coluna < this.getNumeroColunas()){
            for (int linha = 0; linha < tamanho; linha++){
                semRepeticao.add(getElemento(linha, coluna));
            }
        }

        String[] elementos = new String[semRepeticao.size()];

        for (String item : semRepeticao){
            elementos[indice] = item;
            indice++;
        }

        return elementos;

    }

    public String[] colunasNumericas(){
        int tamanho = this.getNumeroColunas();
        String[] auxiliar = new String[tamanho];
        String[] elementos;
        int indice = 0;
        for (int coluna = 0; coluna < tamanho; coluna++){
            if (colunaNumerica(coluna)){
                auxiliar[indice] = tituloColuna(coluna);
                indice++;
            }
        }
        elementos = new String[indice];
        for (int i = 0; i < indice; i++){
            elementos[i] = auxiliar[i];
        }
        return elementos;
    }

    public double media(int coluna) {
        double media = 0;
        int tamanho = this.getNumeroLinhas();
        int total = 0;
        for (int linha = 0; linha < tamanho; linha++) {
            if (this.eNumerico(linha, coluna)){
                media += this.elementoNumerico(linha, coluna);
                total++;
            }
        }
        return total > 0?  media/total : 0;
    }

    public double media(int colunaFixa, String nome, int coluna) {
        double media = 0;
        int tamanho = this.getNumeroLinhas();
        int total = 0;
        for (int linha = 0; linha < tamanho; linha++) {
            if (this.getElemento(linha, colunaFixa).matches(nome) && eNumerico(linha, coluna)){
                media += this.elementoNumerico(linha, coluna);
                total++;
            }
        }
        System.out.println("Total: "+ total);
        return total > 0?  media/total : 0;
    }

    public double variancia(int coluna) {
        double media = this.media(coluna);
        double variancia = 0;
        int total = 0;
        int tamanho = this.getNumeroLinhas();

        for (int linha = 0; linha < tamanho; linha++) {
            if (this.eNumerico(linha, coluna)){
                variancia += Math.pow(this.elementoNumerico(linha, coluna)-media, 2);
                total++;
            }
        }
        return total > 0?  variancia/total : 0;
    }
    public double variancia(int colunaFixa, String nome, int coluna) {
        double media = this.media(colunaFixa, nome, coluna);
        double variancia = 0;
        int tamanho = this.getNumeroLinhas();
        int total = 0;
        for (int linha = 0; linha < tamanho; linha++) {
            if (this.getElemento(linha, colunaFixa).matches(nome) && this.eNumerico(linha, coluna)){
                variancia += Math.pow(this.elementoNumerico(linha, coluna)-media, 2);
                total++;
            }
        }
        return total > 0?  variancia/total : 0;
    }

    public double desvio(int coluna) {
        double variancia = this.variancia(coluna);
        return Math.sqrt(variancia);
    }

    public double desvio(int colunaFixa, String nome, int coluna) {
        double variancia = this.variancia(colunaFixa, nome, coluna);
        return Math.sqrt(variancia);
    }


    public double mediana(int coluna) {
        int total = 0;
        double valor = 0;
        int tamanho = this.getNumeroLinhas();
        double mediana[] = new double[tamanho];

        for (int linha = 0; linha < tamanho; linha++) {
            if (this.eNumerico(linha, coluna)) {
                mediana[linha] = this.elementoNumerico(linha, coluna);
                total++;
            }
        }

        if (total == 0) return 0;

        Arrays.sort(mediana);

        if ((total)%2 == 0) {
            valor = (mediana[total/2] + mediana[total/2+1]) / 2;
        } else {
            valor = mediana[(total+1)/2];
        }

        return valor;
    }

    public double mediana(int colunaFixa, String nome, int coluna) {
        int total = 0;
        double valor = 0;
        int tamanho = this.getNumeroLinhas();
        double mediana[] = new double[tamanho];

        for (int linha = 0; linha < tamanho; linha++) {
            if (this.getElemento(linha, colunaFixa).matches(nome) && this.eNumerico(linha, coluna)) {
                mediana[linha] = this.elementoNumerico(linha, coluna);
                total++;
            }
        }
        int inicio = tamanho - total;
        if (total == 0) return 0;

        Arrays.sort(mediana);

        if ((total)%2 == 0) {
            valor = (mediana[(total/2)+inicio] + mediana[((total+1)/2)+inicio]) / 2;

        } else {
            valor = mediana[((total+1)/2)+inicio];

        }

        return valor;
    }


    public double[] moda(int coluna) {
        int tamanho = this.getNumeroLinhas();
        double[] moda = new double[tamanho+1];
        int indice = 1;
        int repeticao = 0;
        int maior = 0;
        HashMap<Double, Integer> dicionario = new HashMap<>();

        for (int linha = 0; linha < tamanho ; linha++) {
            if (this.eNumerico(linha, coluna)) {
                if (dicionario.containsKey(this.elementoNumerico(linha, coluna))) {
                    repeticao = dicionario.get(this.elementoNumerico(linha, coluna));
                    repeticao++;
                    dicionario.put(this.elementoNumerico(linha, coluna), repeticao);
                } else {
                    repeticao = 1;
                    dicionario.put(this.elementoNumerico(linha, coluna), repeticao);
                }

                if (repeticao > maior) {
                    maior = repeticao;
                    indice = 1;
                    moda[indice] = this.elementoNumerico(linha, coluna);
                    indice++;

                } else if (repeticao == maior) {
                    moda[indice] = this.elementoNumerico(linha, coluna);
                    indice++;
                }
            }
        }

        if (maior == 1) moda[0] = 0;
        else moda[0] = indice;

        return moda;
    }

    public double[] moda(int colunaFixa, String nome, int coluna) {
        int tamanho = this.getNumeroLinhas();
        double[] moda = new double[tamanho+1];
        int indice = 1;
        int repeticao = 0;
        int maior = 0;
        HashMap<Double, Integer> dicionario = new HashMap<>();

        for (int linha = 0; linha < tamanho ; linha++) {
            if (this.getElemento(linha, colunaFixa).matches(nome) && this.eNumerico(linha, coluna)) {
                if (dicionario.containsKey(this.elementoNumerico(linha, coluna))) {
                    repeticao = dicionario.get(this.elementoNumerico(linha, coluna));
                    repeticao++;
                    dicionario.put(this.elementoNumerico(linha, coluna), repeticao);


                } else {
                    repeticao = 1;
                    dicionario.put(this.elementoNumerico(linha, coluna), repeticao);
                }

                if (repeticao == maior) {
                    moda[indice] = this.elementoNumerico(linha, coluna);
                    indice++;

                }else if (repeticao > maior) {
                    maior = repeticao;
                    indice = 1;
                    moda[indice] = this.elementoNumerico(linha, coluna);
                    indice++;
                }
            }
        }
        if (maior == 1) moda[0] = 0;
        else moda[0] = indice;

        return moda;
    }


    public double minimo(int coluna) {
        int tamanho = this.getNumeroLinhas();
        boolean primeiro = true;
        double minimo = 0;

        for (int linha = 0; linha < tamanho ; linha++) {
            if (this.eNumerico(linha, coluna)) {
                if (primeiro) {
                    minimo = this.elementoNumerico(linha, coluna);
                    primeiro = false;

                } else if (minimo > this.elementoNumerico(linha, coluna)){
                    minimo = this.elementoNumerico(linha, coluna);
                }
            }
        }


        return minimo;
    }

    public double minimo(int colunaFixa, String nome, int coluna) {
        int tamanho = this.getNumeroLinhas();
        boolean primeiro = true;
        double minimo = 0;

        for (int linha = 0; linha < tamanho ; linha++) {
            if (this.getElemento(linha, colunaFixa).matches(nome) && this.eNumerico(linha, coluna)) {
                if (primeiro) {
                    primeiro = false;
                    minimo = this.elementoNumerico(linha, coluna);

                } else if (minimo > this.elementoNumerico(linha, coluna)){
                    minimo = this.elementoNumerico(linha, coluna);
                }
            }
        }

        return minimo;
    }


    public double maximo(int coluna) {
        int tamanho = this.getNumeroLinhas();
        double maximo = 0;
        boolean primeiro = true;

        for (int linha = 0; linha < tamanho ; linha++) {
            if (this.eNumerico(linha, coluna)) {
                if (primeiro) {
                    primeiro = false;
                    maximo = this.elementoNumerico(linha, coluna);

                } else if (maximo < this.elementoNumerico(linha, coluna)){
                    maximo = this.elementoNumerico(linha, coluna);
                }  ;
            }
        }

        return maximo;
    }

    public double maximo(int colunaFixa, String nome, int coluna) {
        int tamanho = this.getNumeroLinhas();
        double maximo = 0;
        boolean primeiro = true;

        for (int linha = 0; linha < tamanho ; linha++) {
            if (this.getElemento(linha, colunaFixa).matches(nome) && this.eNumerico(linha, coluna)) {
                if (primeiro) {
                    maximo = this.elementoNumerico(linha, coluna);
                    primeiro = false;

                } else if (maximo < this.elementoNumerico(linha, coluna)){
                    maximo = this.elementoNumerico(linha, coluna);
                }  ;
            }
        }

        return maximo;
    }

}
