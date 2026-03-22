package com.eda.gerenciadortarefas.structure;

public interface IfQueue<T> {
    void enqueue(T data);
    T dequeue();
    int size();
    T head();
    boolean isEmpty();

}
