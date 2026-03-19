package main.java.br.com.gerenciadorDeTarefas.structures;

import main.java.br.com.gerenciadorDeTarefas.structures.MaxHeap;

public class HeapSort {

    public static <T extends Comparable<T>> void sort(MaxHeap<T> heap) {

        MaxHeap<T> aux = new MaxHeap<>();

        while (!heap.isEmpty()) {
            aux.insert(heap.remove());
        }

        while (!aux.isEmpty()) {
            heap.insert(aux.remove());
        }
    }
}
