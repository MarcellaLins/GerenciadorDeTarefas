package com.eda.gerenciadortarefas.service;

import com.eda.gerenciadortarefas.model.Category;
import com.eda.gerenciadortarefas.model.Task;
import com.eda.gerenciadortarefas.structure.AVL;
import com.eda.gerenciadortarefas.structure.HashTable;
import com.eda.gerenciadortarefas.structure.Queue;
import com.eda.gerenciadortarefas.structure.SingleLinkedList;
import com.eda.gerenciadortarefas.structure.HeapSort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    // Armazena todas as tarefas ativas em uma árvore AVL (ordenadas)
    private AVL<Task> allTasks;

    // Fila que guarda as últimas tarefas concluídas
    private Queue<Task> completedTasks;

    // Tabela hash para organizar tarefas por categoria
    private HashTable<Task> categoryTable;

    // Constantes de controle
    private static final int DISPLAY_LIMIT = 5;
    private static final int CATEGORY_COUNT = 6;

    public TaskService() {
        this.completedTasks = new Queue<>();
        this.categoryTable = new HashTable<>(CATEGORY_COUNT, task -> task.getCategory().getCode());
        this.allTasks = new AVL<>();
    }

    // Adiciona uma nova tarefa nas estruturas principais
    public void addTask(String title, String description, Category category, LocalDateTime deadline) {
        Task newTask = new Task(title, description, category, deadline);
        allTasks.insert(newTask);
        categoryTable.insert(newTask);
    }

    // Retorna todas as tarefas ativas (estrutura AVL)
    public AVL<Task> getAllTasks() {
        return allTasks;
    }

    // Marca uma tarefa como concluída e move para a fila de concluídas
    public void completeTask(Task task) {
        task.markAsCompleted();

        // Remove das estruturas de tarefas ativas
        allTasks.remove(task);
        categoryTable.remove(task);

        // Mantém o limite da fila (remove a mais antiga)
        if (completedTasks.size() == DISPLAY_LIMIT) {
            completedTasks.dequeue();
        }

        completedTasks.enqueue(task);
    }

    // Retorna uma cópia das tarefas concluídas sem perder os dados da fila original
    public List<Task> getCompletedTasksSnapshot() {
        Queue<Task> tempQueue = new Queue<>();
        Queue<Task> originalQueue = completedTasks;
        List<Task> snapshot = new ArrayList<>();

        // Copia os elementos para lista e fila temporária
        while (!originalQueue.isEmpty()) {
            Task task = originalQueue.dequeue();
            snapshot.add(task);
            tempQueue.enqueue(task);
        }

        // Restaura a fila original
        while (!tempQueue.isEmpty()) {
            originalQueue.enqueue(tempQueue.dequeue());
        }

        return snapshot;
    }

    // filtra tarefas por categoria usando a tabela hash
    public ArrayList<Task> filterByCategory(Category category) {
        int index = category.getCode();

        SingleLinkedList<Task> list = categoryTable.getBucket(index);
        return list.toArrayList();
    }

    // ordena tarefas pelo prazo usando HeapSort
    public ArrayList<Task> sortByDeadline(ArrayList<Task> tasks) {
        HeapSort.sort((ArrayList<Task>) tasks);
        return tasks;
    }
}