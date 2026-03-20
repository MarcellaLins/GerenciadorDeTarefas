package com.eda.gerenciadortarefas.structures;

public class HashTable <T> {
    private int capacity;
    private SingleLinkedList<T>[] table;

    public HashTable(int capacity){
        this.capacity = capacity;
        table = new SingleLinkedList[capacity];

        for (int i = 0; i < capacity; i++){
            table[i] = new SingleLinkedList();
        }
    }

    private int hash(T key){
        return key.hashCode() % capacity;
    }

    public void insert(T key){
        int index = hash(key);

        if(!table[index].search(key)){
            table[index].addFirst(key);
        }
    }

    public boolean search(T key){
        int index = hash(key);
        return table[index].search(key);
    }

    public boolean remove(T key){
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

    public SingleLinkedList<T> getBucket(int index){
        return table[index];
    }
}
