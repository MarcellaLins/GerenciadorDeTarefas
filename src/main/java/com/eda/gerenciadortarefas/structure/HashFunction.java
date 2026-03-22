package com.eda.gerenciadortarefas.structure;

// define cálculo de hash para um tipo genérico
public interface HashFunction<T> {

    // retorna um valor inteiro que será transformado em índice pela HashTable
    int hash(T key);
}
