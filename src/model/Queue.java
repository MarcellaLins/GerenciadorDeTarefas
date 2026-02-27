package model;

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
        return 0;
    }
    public int size(){
        return 0;
    }
    public int head(){
        return 0;
    }
    public boolean isEmpty(){
        return false;
    }
}
