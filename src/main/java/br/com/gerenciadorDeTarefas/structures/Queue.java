package main.java.br.com.gerenciadorDeTarefas.structures;

public class Queue implements IfQueue{

    protected Stack elements;
    protected int head;
    protected int tail;

    public Queue(){
        elements = new Stack();
        head = -1;
        tail = -1;
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public  void enqueue(int data){
        Stack aux = new Stack();


        while(!elements.isEmpty()){
            aux.push(elements.pop());
        }
        aux.push(data);

        while(!aux.isEmpty()){
            elements.push(aux.pop());
        }

    }
    public int dequeue(){
        return elements.pop();
    }
    public int size(){
        return elements.size();
    }
    public int head(){
        return elements.peek();
    }
    public boolean isEmpty(){
        return elements.isEmpty();
    }
}
