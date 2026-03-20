package com.eda.gerenciadortarefas.service;

import com.eda.gerenciadortarefas.model.Tarefa;
import com.eda.gerenciadortarefas.structures.Queue;

public class TarefaService {
    private Queue<Tarefa> tarefasConcluidas;

    private static final int LIMITE_DE_APRESENTACAO = 5;

    public TarefaService(){
        this.tarefasConcluidas = new Queue<>();
    }

    public void concluirTarefa(Tarefa tarefa){
        tarefa.markAsCompleted();

        if(tarefasConcluidas.size() == LIMITE_DE_APRESENTACAO){
            tarefasConcluidas.dequeue(); // remove a mais antiga
        }

        tarefasConcluidas.enqueue(tarefa);
    }

    public Queue<Tarefa> getTarefasConcluidas(){
        return tarefasConcluidas;
    }
}
