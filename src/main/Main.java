package main;

import main.java.br.com.gerenciadorDeTarefas.structures.LinkedRec;
import main.java.br.com.gerenciadorDeTarefas.structures.Queue;

public class Main {
    public static void main(String[] args){

        LinkedRec lista = new LinkedRec();

        for (int i = 0; i< 10; i++){
            lista.add(i);
        }


        System.out.println(lista);


        Queue fila = new Queue();

        for (int i =0; i<10; i++){
            fila.enqueue(i);
        }

        System.out.println(fila);
        System.out.println(fila.dequeue());
        System.out.println(fila.size());








    }
}
