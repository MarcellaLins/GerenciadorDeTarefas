package main.java.br.com.gerenciadorDeTarefas.structures;

public interface IfQueue<T> {
    void enqueue(T data);
    T dequeue();
    int size();
    T head();
    boolean isEmpty();

}
