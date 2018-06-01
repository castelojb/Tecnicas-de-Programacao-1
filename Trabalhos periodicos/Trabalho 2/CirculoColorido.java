public class CirculoColorido extends Circulo implements Colorida {
    //Hariamy: Métodos para criação de um CírculoColorido
    int cor;

    CirculoColorido(float centroX[], float centroY[], float raio, int cor){
        super(centroX, centroY, raio);
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Figura Circular Colorida";

    }

    @Override
    public void colorir(int cor){
        this.cor = cor;
    }

}
