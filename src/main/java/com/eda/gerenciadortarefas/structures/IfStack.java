package com.eda.gerenciadortarefas.structures;

public interface IfStack<T> {
    void push(T value);
    T pop();
    T peek();
    boolean isEmpty();

}