package model;

public class Main {
    public static void main(String[] args){

        LinkedRec lista = new LinkedRec();

        for (int i = 0; i< 10; i++){
            lista.addLast(i);
        }


        System.out.println(lista);


        Queue fila = new Queue();

        for (int i =0; i<10; i++){
            fila.enqueue(i);
        }

        System.out.println(fila);








    }
}
