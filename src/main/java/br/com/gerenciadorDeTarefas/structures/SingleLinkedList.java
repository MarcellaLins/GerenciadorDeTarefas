package main.java.br.com.gerenciadorDeTarefas.structures;

import java.util.ArrayList;

public class SingleLinkedList <T> {

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

    // inserção no início O(1)
    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);

        newNode.next = head;
        head = newNode;

        size++;
   }

   // inserção no início O(n)
   public void addLast(T data){
        Node<T> newNode = new Node<>(data);

        if(head == null){
            head = newNode;
        } else{
            Node<T> current = head;

            while(current.next != null){
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
   }

    // busca O(n)
    public boolean search(T data) {

        Node<T> current = head;

        while (current != null) {

            if (current.data.equals(data)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // remoção O(n)
    public boolean remove(T data) {

        if (head == null) {
            return false;
        }

        // remover primeiro
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;

        while (current.next != null) {

            if (current.next.data.equals(data)) {

                current.next = current.next.next;
                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

   public boolean isEmpty(){
        return size==0;
   }

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

    public ArrayList<T> toArrayList(){
        ArrayList<T> list = new ArrayList<>(size);

        Node<T> current = head;

        while(current != null){
            list.add(current.data);
            current = current.next;
        }

        return list;
    }
}
