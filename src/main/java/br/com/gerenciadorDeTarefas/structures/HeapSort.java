package main.java.br.com.gerenciadorDeTarefas.structures;

import java.util.ArrayList;

public class HeapSort {

    public static <T extends Comparable<T>> void sort(ArrayList<T> list) {
        MaxHeap<T> heap = new MaxHeap<>(list);

        for(int i = heap.size() - 1; i >= 0; i--) {
            list.set(i, heap.remove());
        }
    }

    public static <T extends Comparable<T>> ArrayList<T> sort(SingleLinkedList<T> ll){
        ArrayList<T> list = ll.toArrayList();

        sort(list);

        return list;
    }

}
