package estruturas;

public class Stack implements IfStack{
    protected LinkedRec elements;
    protected int top;

    public Stack(){
        elements = new LinkedRec();
        top = -1;
    }

    @Override
    public String toString() {
        return "[" + elements + "]";
    }

    public void push(int elemento){
        //TODO: Precisa de alguma exceção?
        top++;
        elements.add(elemento);
    }

    public int pop(){ //não está retornando
        if(isEmpty()) return -1;//ajustar isso dps
        int removedElement = elements.prox.data; //add get

        elements.prox = elements.prox.prox; //remove aqui

        top--;

        return removedElement;
    }

    public int peek(){
        if(isEmpty()) return -1;
        return elements.prox.data;
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public int size(){
        return elements.size();
    }



}
