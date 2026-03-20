package com.eda.gerenciadortarefas.structures;

public class Stack<T extends Comparable<T>> implements IfStack<T> {

    protected LinkedRec<T> elements;
    protected int top;

    public Stack(){
        elements = new LinkedRec<>();
        top = -1;
    }

    @Override
    public String toString() {
        return "[" + elements + "]";
    }

    public void push(T elemento){
        //TODO: Precisa de alguma exceção?
        top++;
        elements.add(elemento);
    }

    public T pop(){
        if(isEmpty()) return null;

        T removedElement = elements.prox.data;

        elements.prox = elements.prox.prox;

        top--;

        return removedElement;
    }

    public T peek(){
        if(isEmpty()) return null;
        return elements.prox.data;
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public int size(){
        return elements.size();
    }
}
