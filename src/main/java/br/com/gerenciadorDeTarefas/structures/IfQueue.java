package main.java.br.com.gerenciadorDeTarefas.structures;

public interface IfQueue {
    void enqueue(int data);
    int dequeue();
    int size();
    int head();
    boolean isEmpty();

}
