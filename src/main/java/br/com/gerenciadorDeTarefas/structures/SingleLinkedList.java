package main.java.br.com.gerenciadorDeTarefas.structures;

public class SingleLinkedList <T extends Comparable<T>> {

    // class Node integrada
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
        }

    }

   private Node<T> head;
    private int size;

    public T getHead(){
        if(isEmpty()){
            return null;
        }

        return head.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<T> current = head;

        while(current != null){
            sb.append(current.data);
            if (current.next != null){
                sb.append(", ");
            }

            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public void add(int elemento){
       Node newNode = new Node(elemento);
       if (head == null){
           head = newNode;
           return;
       }
       Node current = head;
       while (current.next != null) {
           current = current.next;
       }
       current.next = newNode;


   }

   public void remove(int elemento){

   }

   public void research(int elemento){

   }

   public int size(){
        return 0;
   }

   public boolean isEmpty(){
        return false;
   }

}
