public class Main {
    public static void main(String[] args){

        LinkedRec lista = new LinkedRec();

        for (int i = 0; i< 10; i++){
            lista.add(i);
        }

        System.out.println(lista);
        System.out.println(lista.research(1));
        System.out.println(lista.size());


        for (int i = 0; i<= 5; i++){
            lista.remove(i);
        }
        System.out.println(lista);
        System.out.println(lista.size());

        for(int i = 0; i<5; i++){
            int j = 1;
            lista.addIndex(i,j);
        }

        System.out.println(lista);



    }
}
