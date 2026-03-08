package main.java.br.com.gerenciadorDeTarefas.structures;

public interface IfStack<T> {
    void push(T value);
    T pop();
    T peek();
    boolean isEmpty();

}
