public class Stack {
    protected LinkedRec elements;
    protected int top;


    public Stack(){
        elements = new LinkedRec();
        top = -1;
    }

    public void push(int elemento){
        //TODO: Precisa de alguma exceção?
        top++;
        elements.add(elemento);
    }

    public int pop(){
        if(isEmpty()) return -1;//ajustar isso dps
        int removedElement = elements.getData();
        top --;

        return removedElement;
    }

    public int peek(){
      return 0;
    }

    public boolean isEmpty(){
        return false;
    }

    public int size(){
        return 0;
    }



}
