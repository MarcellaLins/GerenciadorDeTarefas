package com.eda.gerenciadortarefas.service;

import com.eda.gerenciadortarefas.model.Category;
import com.eda.gerenciadortarefas.model.Task;
import com.eda.gerenciadortarefas.structure.AVL;
import com.eda.gerenciadortarefas.structure.HashTable;
import com.eda.gerenciadortarefas.structure.Queue;
import com.eda.gerenciadortarefas.structure.SingleLinkedList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private AVL<Task> todasTarefas;
    private Queue<Task> tarefasConcluidas;
    private HashTable<Task> tabelaCategoria;

    private static final int LIMITE_DE_APRESENTACAO = 5;
    private static final int NUMERO_DE_CATEGORIAS = 6;

    public TaskService(){
        this.tarefasConcluidas = new Queue<>();
        this.tabelaCategoria = new HashTable<>(NUMERO_DE_CATEGORIAS, task -> task.getCategory().getCode());
        this.todasTarefas = new AVL<>();
    }

    public void taskAdd(String title, String description, Category category, LocalDateTime deadline){
        Task novaTask = new Task(title, description, category, deadline);
        todasTarefas.insert(novaTask);
        tabelaCategoria.insert(novaTask);
    }

    public AVL<Task> getTodasTarefas(){
        return todasTarefas;
    }

    public void concluirTarefa(Task task){
        task.markAsCompleted();
        todasTarefas.remove(task);
        tabelaCategoria.remove(task);

        if(tarefasConcluidas.size() == LIMITE_DE_APRESENTACAO){
            tarefasConcluidas.dequeue(); // remove a mais antiga
        }

        tarefasConcluidas.enqueue(task);
    }

    public List<Task> getCompletedTasksSnapshot() {
        Queue<Task> tempQueue = new Queue<>();
        Queue<Task> original = tarefasConcluidas; // sua fila original
        List<Task> snapshot = new ArrayList<>();

        // copia elementos para a fila temporária e adiciona à lista
        while (!original.isEmpty()) {
            Task t = original.dequeue();
            snapshot.add(t);
            tempQueue.enqueue(t);
        }

        // restaura a fila original
        while (!tempQueue.isEmpty()) {
            original.enqueue(tempQueue.dequeue());
        }

        return snapshot;
    }

    public List<Task> filtrarPorCategoria (Category category){
        int index = category.getCode();

        SingleLinkedList<Task> lista = tabelaCategoria.getBucket(index);

        List<Task> resultado = new ArrayList<>();

        for(Task t: lista){
            resultado.add(t);
        }

        return resultado;
    }

    public List<Task> ordenarPorPrazo(List<Task> tasks){
        //TODO: implementar lógica
        throw new UnsupportedOperationException("Ainda não implementado");
    }
}
