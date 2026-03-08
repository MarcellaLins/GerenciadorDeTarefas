package main.java.br.com.gerenciadorDeTarefas.structures;

public class LinkedRec<T extends Comparable<T>> {

    protected T data;
    protected LinkedRec<T> prox;

    public LinkedRec() {
        this.prox = null;
    }

    public LinkedRec(T data) {
        this.data = data;
        this.prox = null;
    }

    public T getData() {
        return data;
    }

    // addFirst para facilitar na pilha
    public void add(T value) {
        addIndex(value, 0);
    }

    // facilitar o funcionamento da fila
    public void addLast(T value) {
        addIndex(value, size());
    }

    public void addIndex(T value, int index) {

        if (index < 0) {
            return; // índice inválido: não faz nada
        }

        if (index == 0) {
            LinkedRec<T> novo = new LinkedRec<>(value);
            novo.prox = this.prox;
            this.prox = novo;
        } else {
            if (this.prox != null) {
                this.prox.addIndex(value, index - 1);
            }
        }
    }

    public boolean research(T value) {
        if (this.prox == null) return false;

        if (this.prox.data.compareTo(value) == 0) return true;

        return this.prox.research(value);
    }

    public void remove(T value) {
        if (this.prox == null) return;

        if (this.prox.data.compareTo(value) == 0) {
            this.prox = this.prox.prox;
        } else {
            this.prox.remove(value);
        }
    }

    public int size() {
        if (isEmpty()) return 0;
        return 1 + this.prox.size();
    }

    public boolean isEmpty() {
        return this.prox == null;
    }

    @Override
    public String toString() {
        if (this.prox == null) return "";
        if (this.prox.prox == null) {
            return String.valueOf(this.prox.data);
        }
        return this.prox.data + " " + this.prox;
    }
}