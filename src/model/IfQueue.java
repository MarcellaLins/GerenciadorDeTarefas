package model;

public interface IfQueue {
    void enqueue(int data);
    int dequeue();
    int size();
    int head();
    boolean isEmpty();

}
