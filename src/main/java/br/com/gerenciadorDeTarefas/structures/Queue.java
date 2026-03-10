package main.java.br.com.gerenciadorDeTarefas.structures;

public class Queue<T extends Comparable<T>> implements IfQueue<T>{

    protected Stack<T> stack1;
    protected Stack<T> stack2;

    public Queue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    @Override
    public String toString() {
        return stack1.toString();
    }

    public void enqueue(T data){
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }

        stack2.push(data);

        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }

    public T dequeue(){
        return stack1.pop();
    }

    public int size(){
        return stack1.size();
    }

    public T head(){
        return stack1.peek();
    }

    public boolean isEmpty(){
        return stack1.isEmpty();
    }
}