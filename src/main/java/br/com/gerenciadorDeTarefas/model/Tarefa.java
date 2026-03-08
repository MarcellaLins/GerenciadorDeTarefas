package main.java.br.com.gerenciadorDeTarefas.model;

import java.time.LocalDate;

public class Tarefa {

    private String title;
    private String description;
    private Categoria category;
    private LocalDate deadline;

    public Tarefa(Categoria category, LocalDate deadline, String description, String title) {
        this.category = category;
        this.deadline = deadline;
        this.description = description;
        this.title = title;
    }

    //GETTERS E SETTERS

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
