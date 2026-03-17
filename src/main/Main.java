package main;

import main.java.br.com.gerenciadorDeTarefas.model.Categoria;
import main.java.br.com.gerenciadorDeTarefas.model.Tarefa;
import main.java.br.com.gerenciadorDeTarefas.structures.LinkedRec;
import main.java.br.com.gerenciadorDeTarefas.structures.Queue;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        // testes com Tarefa

        Tarefa t1 = new Tarefa(
                "Estudar Tabela Hash",
                "Revisar e implementar métodos de forma iterativa",
                Categoria.ESTUDOS,
                // LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute)
                LocalDateTime.of(2026, 3, 10, 14, 0)
        );

        Tarefa t2 = new Tarefa(
                "Estudar Tabela Hash",
                "Revisar e implementar métodos de forma iterativa",
                Categoria.ESTUDOS,
                LocalDateTime.of(2026, 3, 7, 9, 30)
        );

        System.out.println(t1.getId());
        System.out.println(t2.getId());
        System.out.println("O hash é: " + t2.hashCode());
    }
}
