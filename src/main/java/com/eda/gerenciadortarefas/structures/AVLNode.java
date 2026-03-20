package com.eda.gerenciadortarefas.structures;

public class AVLNode<T extends Comparable<T>> {
    private T data;
    private AVLNode<T> left;
    private AVLNode<T> right;
    private int height;

    // Construtor para nó NIL
    public AVLNode() {
        this.data = null;
        this.left = null;
        this.right = null;
        this.height = -1;
    }

    // Construtor para nó com valor
    public AVLNode(T data) {
        this.data = data;
        this.left = new AVLNode<>();
        this.right = new AVLNode<>();
        this.height = 0;
    }

    public boolean isNil() {
        return this.data == null;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public int getHeight(){
        return height;
    }

    public T getData() {
        return data;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setData(T data) {
        this.data = data;
    }
}
