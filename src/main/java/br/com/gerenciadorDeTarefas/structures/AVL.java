package main.java.br.com.gerenciadorDeTarefas.structures;

public class AVL <T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVL() {
        this.root = new AVLNode<>();
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root.isNil();
    }

    public void insert(T element) {
        root = insert(root, element);
    }

    private AVLNode<T> insert(AVLNode<T> node, T element) {
        if (node.isNil()) {
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

        // TO-DO: determinar o caso de balanceamento e aplicar a rotação adequada (LL,RR,LR,RL)

        return node;
    }

    private void updateHeight(AVLNode<T> node) {
        // usar updateHeight() nos metodos insert() e remove() para atualizar a altura dos nós após a modificação da AVL
        node.setHeight(1 + Math.max(
                node.getLeft().getHeight(),
                node.getRight().getHeight()
        ));
    }

    public int height(){
        return root.getHeight();
    }

}