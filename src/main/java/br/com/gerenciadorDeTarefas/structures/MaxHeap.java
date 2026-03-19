package main.java.br.com.gerenciadorDeTarefas.structures;

import java.util.ArrayList;

public class MaxHeap <T extends Comparable<T>> {
    private final ArrayList<T> elements;
    private int heapSize;

    public MaxHeap() {
        this.elements = new ArrayList<>(50);
        this.heapSize = 0;
    }

    public MaxHeap(ArrayList<T> list) {
        this.elements = new ArrayList<>(list);
        this.heapSize = list.size();
        buildHeap();
    }


    public void insert(T element) {
        elements.add(element);
        heapSize++;

        int i = heapSize - 1;

        while(i > 0 && elements.get(parent(i)).compareTo(elements.get(i)) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public T remove() {
        if (isEmpty()) throw new IllegalStateException("Heap underflow");

        T max = max();

        elements.set(0, elements.get(heapSize-1));

        elements.remove(heapSize - 1);
        heapSize--;

        heapify(0);

        return max;
    }

    public T max() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        return elements.get(0);
    }

    public int size()  {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void heapify(int index) {
        int largest = index;
        int left = left(index);
        int right = right(index);

        if(left < heapSize && elements.get(left).compareTo(elements.get(largest)) > 0){
            largest = left;
        }

        if(right < heapSize && elements.get(right).compareTo(elements.get(largest)) > 0){
            largest = right;
        }

        if(largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    private void buildHeap() {
        for(int i = (size() - 1) / 2; i >= 0; i--) heapify(i);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }

}
