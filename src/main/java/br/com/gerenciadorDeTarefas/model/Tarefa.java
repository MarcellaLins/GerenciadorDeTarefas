package main.java.br.com.gerenciadorDeTarefas.model;

import java.time.LocalDateTime;

public class Tarefa implements Comparable<Tarefa> {

    private static int contadorID = 1;

    private final int id;
    private String title;
    private String description;
    private Categoria category;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private boolean completed;

    public Tarefa(String title, String description, Categoria category, LocalDateTime deadline ) {
        this.id = contadorID++;
        this.title = title;
        this.description = description;
        this.category = category;
        this.creationDate = LocalDateTime.now();
        this.deadline = deadline;
        this.completed = false;
    }

    // ISSO É ÚTIL, ACREDITE
    public Tarefa() {
        this.id = contadorID++;
    }

    // TALVEZ SEJA ALTERADO

    // se está concluída (true) ou pendente (false)
    public boolean isCompleted(){ return completed;}

    public void markAsCompleted(){
        this.completed = true;
    }

    public void markAsPending(){
        this.completed = false;
    }

    /*
      Se está atrasada:
        pendente e com data anterior a data atual.

      OBS.: se data não definida (null), não se atrasa.
    */

    public boolean isOverdue(){
        return !completed && deadline != null && deadline.isBefore(LocalDateTime.now());
    }

    //GETTERS E SETTERS

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    // TO STRING
    @Override
    public String toString() {
        return "ID: " + id +
                "\nTítulo: " + title +
                "\nDescrição: " + description +
                "\nCategoria: " + category +
                "\nCriada em: " + creationDate +
                "\nPrazo: " + deadline +
                "\nConcluída: " + completed;
    }

    // COMPARE TO
    @Override
    public int compareTo(Tarefa other) {

        if (this.deadline == null && other.deadline == null) {
            return Integer.compare(this.id, other.id);
        }

        if (this.deadline == null) {
            return 1;
        }

        if (other.deadline == null) {
            return -1;
        }

        int result = this.deadline.compareTo(other.deadline);

        if (result != 0) {
            return result;
        }

        return Integer.compare(this.id, other.id);
    }

    // EQUALS
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Tarefa other = (Tarefa) obj;

        return this.id == other.id;
    }

    // HASH CODE
    public int hashCode(){
        return category.getCodigo();
    }

}
