package com.eda.gerenciadortarefas.service;

import com.eda.gerenciadortarefas.model.Categoria;
import com.eda.gerenciadortarefas.model.Tarefa;
import com.eda.gerenciadortarefas.structures.HashTable;
import com.eda.gerenciadortarefas.structures.Queue;
import com.eda.gerenciadortarefas.structures.SingleLinkedList;

import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private Queue<Tarefa> tarefasConcluidas;
    private HashTable<Tarefa> tabela;

    private static final int LIMITE_DE_APRESENTACAO = 5;
    private static final int NUMERO_DE_CATEGORIAS = 6;

    public TarefaService(){
        this.tarefasConcluidas = new Queue<>();
        this.tabela = new HashTable<>(NUMERO_DE_CATEGORIAS);
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

    public List<Tarefa> filtrarPorCategoria (Categoria categoria){
        int index = categoria.getCodigo();

        SingleLinkedList<Tarefa> lista = tabela.getBucket(index);

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
