package com.eda.gerenciadortarefas.service;

import com.eda.gerenciadortarefas.model.Categoria;
import com.eda.gerenciadortarefas.model.Tarefa;
import com.eda.gerenciadortarefas.structures.AVL;
import com.eda.gerenciadortarefas.structures.HashTable;
import com.eda.gerenciadortarefas.structures.Queue;
import com.eda.gerenciadortarefas.structures.SingleLinkedList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private AVL<Tarefa> todasTarefas;
    private Queue<Tarefa> tarefasConcluidas;
    private HashTable<Tarefa> tabelaCategoria;

    private static final int LIMITE_DE_APRESENTACAO = 5;
    private static final int NUMERO_DE_CATEGORIAS = 6;

    public TarefaService(){
        this.tarefasConcluidas = new Queue<>();
        this.tabelaCategoria = new HashTable<>(NUMERO_DE_CATEGORIAS);
        this.todasTarefas = new AVL<>();
    }

    public void taskAdd(String title, String description, Categoria category, LocalDateTime deadline){
        Tarefa novaTarefa = new Tarefa(title, description, category, deadline);
        todasTarefas.insert(novaTarefa);
        tabelaCategoria.insert(novaTarefa);
    }

    public AVL<Tarefa> getTodasTarefas(){
        return todasTarefas;
    }

    public void concluirTarefa(Tarefa tarefa){
        tarefa.markAsCompleted();
        todasTarefas.remove(tarefa);
        tabelaCategoria.remove(tarefa);

        if(tarefasConcluidas.size() == LIMITE_DE_APRESENTACAO){
            tarefasConcluidas.dequeue(); // remove a mais antiga
        }

        tarefasConcluidas.enqueue(tarefa);
    }

    public Queue<Tarefa> getTarefasConcluidas(){
        return tarefasConcluidas;
    }

    public List<Tarefa> filtrarPorCategoria (Categoria categoria){
        int index = categoria.getCodigo();

        SingleLinkedList<Tarefa> lista = tabelaCategoria.getBucket(index);

        List<Tarefa> resultado = new ArrayList<>();

        for(Tarefa t: lista){
            resultado.add(t);
        }

        return resultado;
    }

    public List<Tarefa> ordenarPorPrioridade(List<Tarefa> tarefas){
        //TODO: implementar lógica
        throw new UnsupportedOperationException("Ainda não implementado");
    }
}
