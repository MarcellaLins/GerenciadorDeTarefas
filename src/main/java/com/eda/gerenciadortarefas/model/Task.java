package com.eda.gerenciadortarefas.model;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {

    // geração de ID
    private static int counterID = 1;

    private final int id;
    private String title;
    private String description;
    private Category category;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private boolean completed;
    private LocalDateTime completedAt;

    public Task(String title, String description, Category category, LocalDateTime deadline) {
        this.id = counterID++;
        this.title = title;
        this.description = description;
        this.category = category;
        this.creationDate = LocalDateTime.now();
        this.deadline = deadline;
        this.completed = false;
    }

    // indica se a tarefa tá concluída
    public boolean isCompleted() {
        return completed;
    }

    // marca a tarefa como concluída
    public void markAsCompleted() {
        this.completed = true;
        completedAt = LocalDateTime.now();
    }

    // reverte a tarefa para pendente, vamos usar? talvez não
    public void markAsPending() {
        this.completed = false;
    }

    // uma tarefa está atrasada se: não foi concluíd, tem prazo definido, o prazo já passou
    public boolean isOverdue() {
        return !completed &&
                deadline != null &&
                deadline.isBefore(LocalDateTime.now());
    }

    // Getters e Setters

    public int getId() {
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

    public LocalDateTime getCompletedAt(){
        return completedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // representação texto. Deve ser útil para debug
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

    /* critpério de ordenação:
        1. por prazo (mais próximo primeiro)
        2. tarefas sem prazo vão para o final
        3. desempate por ID
     */

    @Override
    public int compareTo(Task other) {

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

    // diferenciar um objeto de outro
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Task other = (Task) obj;

        return this.id == other.id;
    }
}