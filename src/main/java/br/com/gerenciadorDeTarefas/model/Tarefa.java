package main.java.br.com.gerenciadorDeTarefas.model;

import java.time.LocalDate;

public class Tarefa {

    private static int contadorID = 1;

    private final int id;
    private String title;
    private String description;
    private Categoria category;
    private LocalDate deadline;
    private boolean completed;

    public Tarefa(String title, String description, Categoria category, LocalDate deadline ) {
        this.id = contadorID++;
        this.category = category;
        this.deadline = deadline;
        this.description = description;
        this.title = title;
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
        return !completed && deadline != null && deadline.isBefore(LocalDate.now());
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

}
