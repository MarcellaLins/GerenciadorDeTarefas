package com.eda.gerenciadortarefas.structure;

public class HashTable<T> {

    // quantidade de posições (buckets) da tabela
    private int capacity;

    // função de hash definida externamente
    private HashFunction<T> hashFunction;

    // Array de listas encadeadas para colisões
    private SingleLinkedList<T>[] table;

    public HashTable(int capacity, HashFunction<T> hashFunction) {
        this.capacity = capacity;
        this.hashFunction = hashFunction;

        table = new SingleLinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new SingleLinkedList<>();
        }
    }

    private int hash(T key) {
        return Math.abs(hashFunction.hash(key)) % capacity;
    }

    public void insert(T key) {
        int index = hash(key);

        if (!table[index].search(key)) {
            table[index].addFirst(key);
        }
    }

    public boolean search(T key) {
        int index = hash(key);
        return table[index].search(key);
    }

    public boolean remove(T key) {
        int index = hash(key);
        return table[index].remove(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < capacity; i++) {
            sb.append(i)
                    .append(": ")
                    .append(table[i])
                    .append("\n");
        }

        return sb.toString();
    }

    // retorna a linkdelist (todas as tarefas de uma categoria)
    public SingleLinkedList<T> getBucket(int index) {
        return table[index];
    }
}