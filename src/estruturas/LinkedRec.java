package estruturas;

public class LinkedRec {

    protected int data;
    protected LinkedRec prox;
    public LinkedRec () {
        this.prox = null;
    }

    public LinkedRec(int data) {
        this.data = data;
        this.prox = null;
    }

    public int getData() {
        return data;
    }

    //addFirst para facilitar na pilha
    public void add(int value) {
            addIndex(value,0);
    }

    // facilitar o funcionamento da fila
    public void addLast(int value){
       addIndex(value, size());
    }

    public void addIndex(int value, int index) {

        if (index < 0) {
            return; // índice inválido:  não faz nada
        }

        if (index == 0) {
            LinkedRec novo = new LinkedRec(value);
            novo.prox = this.prox;
            this.prox = novo;
        }
        else {
            if (this.prox != null) {
                this.prox.addIndex(value, index - 1);
            }
        }
    }


    public boolean research(int value) {
        if (this.data == value) {
            return true;
        }
        if (this.prox == null) {
            return false;
        }
        return this.prox.research(value);
    }

    public void remove(int value){
        if (this.prox == null) return;
        if (this.prox.data == value) {
            this.prox = this.prox.prox;
        }
        else {
            this.prox.remove(value); }
    }

    public int size(){
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