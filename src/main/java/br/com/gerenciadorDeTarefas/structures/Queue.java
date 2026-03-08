package main.java.br.com.gerenciadorDeTarefas.structures;

public class Queue<T extends Comparable<T>> implements IfQueue<T>{

    protected Stack<T> elements;
    protected int head;
    protected int tail;

    public Queue(){
        elements = new Stack<>();
        head = -1;
        tail = -1;
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public void enqueue(T data){
        Stack<T> aux = new Stack<>();

        while(!elements.isEmpty()){
            aux.push(elements.pop());
        }

        aux.push(data);

        while(!aux.isEmpty()){
            elements.push(aux.pop());
        }
    }

    public T dequeue(){
        return elements.pop();
    }

    public int size(){
        return elements.size();
    }

    public T head(){
        return elements.peek();
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }
}