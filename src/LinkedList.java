
public class LinkedList {
   private Node head;

   public LinkedList(){
        head = null;
   }
    //melhorar esse toString
    @Override
    public String toString() {
        if (head == null) return "[]";

        StringBuilder sb = new StringBuilder();
        Node current = head;

        while (current != null) {
            sb.append(current.data).append(",");
            current = current.next;
        }

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

   public int getHead(){
       return head.data;
   }
}
