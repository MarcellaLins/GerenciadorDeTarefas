package main.java.br.com.gerenciadorDeTarefas.structures;

import java.util.ArrayList;
import java.util.List;

public class AVL <T extends Comparable<T>> {
    private AVLNode<T> root;
    private int size;

    public AVL() {
        this.root = new AVLNode<>();
        this.size = 0;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root.isNil();
    }

    public int size() {
        return size;
    }

    public int height(){
        return root.getHeight();
    }

    private void updateHeight(AVLNode<T> node) {
        // usar updateHeight() nos metodos insert() e remove() para atualizar a altura dos nós após a modificação da AVL
        node.setHeight(1 + Math.max(
                node.getLeft().getHeight(),
                node.getRight().getHeight()
        ));
    }

    public void insert(T element) {
        root = insert(root, element);
    }

    private AVLNode<T> insert(AVLNode<T> node, T element) {
        if (node.isNil()) {
            size++;
            return new AVLNode<>(element);
        }

        if (element.compareTo(node.getData()) < 0) {
            node.setLeft(insert(node.getLeft(), element));
        } else if (element.compareTo(node.getData()) > 0) {
            node.setRight(insert(node.getRight(), element));
        } else {
            return node;
        }

        updateHeight(node);
        node = rebalance(node);
        return node;
    }

    private int calculateBalance(AVLNode<T> node) {
        return node.getLeft().getHeight() - node.getRight().getHeight();
    }

    private AVLNode<T> rebalance (AVLNode<T> node) {
        int balance = calculateBalance(node);

        // LL
        if (balance > 1 && calculateBalance(node.getLeft()) >= 0) {
            return rotateRight(node);
        }

        // LR
        if (balance > 1 && calculateBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && calculateBalance(node.getRight()) <= 0) {
            return rotateLeft(node);
        }

        // RL
        if (balance < -1 && calculateBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getLeft();
        AVLNode<T> temp = newRoot.getRight();

        newRoot.setRight(node);
        node.setLeft(temp);

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getRight();
        AVLNode<T> temp = newRoot.getLeft();

        newRoot.setLeft(node);
        node.setRight(temp);

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    public void remove(T element){
        root = remove(root, element);
    }

    private AVLNode<T> remove(AVLNode<T> node, T element) {
        if(node.isNil()) return node;

        if(element.compareTo(node.getData()) < 0){
            node.setLeft(remove(node.getLeft(), element));
        } else if(element.compareTo(node.getData()) > 0){
            node.setRight(remove(node.getRight(), element));
        } else {
            // caso 1
            if(node.getLeft().isNil() && node.getRight().isNil()){
                size--;
                return new AVLNode<>();
            }

            // caso 2
            if(node.getLeft().isNil()){
                size--;
                return node.getRight();
            }

            if(node.getRight().isNil()){
                size--;
                return node.getLeft();
            }

            // caso 3: dois filhos
            AVLNode<T> successor = successorRemove(node);

            node.setData(successor.getData());

            node.setRight(remove(node.getRight(), successor.getData()));
        }

        updateHeight(node);
        return rebalance(node);
    }

    private AVLNode<T> successorRemove(AVLNode<T> node) {
        AVLNode<T> n = node.getRight();
        while(!n.getLeft().isNil()){
            n = n.getLeft();
        }
        return n;
    }

    public AVLNode<T> search(T element) {
        return search(root, element);
    }

    private AVLNode<T> search(AVLNode<T> node, T element){
        if(node.isNil() || element.compareTo(node.getData()) == 0) return node;
        if(element.compareTo(node.getData()) < 0) return search(node.getLeft(), element);
        return search(node.getRight(), element);
    }

    public List<T> inOrder() {
        List<T> inOrderList = new ArrayList<>();
        inOrder(root, inOrderList);
        return inOrderList;
    }

    private void inOrder(AVLNode<T> node, List<T> inOrderList) {
        if(!node.isNil()){
            inOrder(node.getLeft(), inOrderList);
            inOrderList.add(node.getData());
            inOrder(node.getRight(), inOrderList);
        }
    }

    public List<T> preOrder() {
        List<T> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList);
        return preOrderList;
    }

    private void preOrder(AVLNode<T> node, List<T> preOrderList) {
        if(!node.isNil()){
            preOrderList.add(node.getData());
            preOrder(node.getLeft(), preOrderList);
            preOrder(node.getRight(), preOrderList);
        }
    }

    public List<T> postOrder() {
        List<T> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList);
        return postOrderList;
    }

    private void postOrder(AVLNode<T> node, List<T> postOrderList) {
        if(!node.isNil()){
            postOrder(node.getLeft(), postOrderList);
            postOrder(node.getRight(), postOrderList);
            postOrderList.add(node.getData());
        }
    }

}