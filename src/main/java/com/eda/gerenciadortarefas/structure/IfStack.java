package com.eda.gerenciadortarefas.structure;

public interface IfStack<T> {
    void push(T value);
    T pop();
    T peek();
    boolean isEmpty();

}