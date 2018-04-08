public class Teste{
    public static void main(String[] args) {
        Sistema s = new Sistema();
        s.criarContaXPTOBasic(1);
        s.criarContaXPTOPlus(2);
        s.criarContaXPTOExtreme(3);

        for (int i = 1; i <=3 ; i++) s.creditar(i, 1000);//Coerção, pois ao creditar 1000 o compilador faz a conversão de int para double

        for (int i = 1; i <=3 ; i++) s.debitar(i, 10);//Coerção, pois ao creditar 10 o compilador faz a conversão de int para double

        System.out.println(s.consultarSaldo(1));
        System.out.println(s.consultarSaldo(2));
        System.out.println(s.consultarSaldo(3));

		if (s.consultarSaldo(1) == 990) {
            System.out.println("bom1");
        }
        if (s.consultarSaldo(2) == 995) {
            System.out.println("bom2");
        }   
        if (s.consultarSaldo(3) == 992.02) {
            System.out.println("bom3");
        }

        for (int i = 1; i <= 3; i++) {
            System.out.println(s.contas[i]);
            
        }
    }
}
