package com.eda.gerenciadortarefas.controller;

import com.eda.gerenciadortarefas.model.Category;
import com.eda.gerenciadortarefas.model.Task;
import com.eda.gerenciadortarefas.service.TaskService;
import com.eda.gerenciadortarefas.structure.AVL;
import com.eda.gerenciadortarefas.utils.TaskServiceAware;
import com.eda.gerenciadortarefas.utils.ScreenLoader;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collection;

// controller da tela inicial (listagem de tarefas)
public class MainViewController implements TaskServiceAware {

    private TaskService taskService;

    // estrutura principal com todas as tarefas (AVL)
    private AVL<Task> allTasks;

    // controle de inicialização da tela
    private boolean initialized = false;

    // categoria atual do filtro (null = sem filtro)
    private Category currentCategory = null;

    // recebe o serviço injetado
    @Override
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
        this.allTasks = taskService.getTodasTarefas();

        // Se a tela já estiver pronta, renderiza imediatamente
        if (initialized) {
            showAllTasks();
        }
    }

    @FXML
    private Label title;

    @FXML
    private VBox taskList;

    @FXML
    private Button btnCompleted;

    @FXML
    public void initialize() {

        initialized = true;

        // lista limpa ao iniciar
        taskList.getChildren().clear();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(30), e -> refreshUI())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // caso o service já tenha sido injetado antes do initialize
        if (taskService != null) {
            showAllTasks();
        }

        // ajusta dinamicamente o tamanho do título conforme a largura da janela
        Platform.runLater(() -> {
            Stage stage = (Stage) title.getScene().getWindow();

            stage.widthProperty().addListener((o, oldVal, newVal) -> {
                title.setStyle("-fx-font-size: " + (newVal.doubleValue() / 25) + "px;");
            });
        });
    }

    // renderiza lista de tarefas (ordenada e agrupada por data)
    private void renderTaskList(Collection<Task> tasks) {

        taskList.getChildren().clear();

        String lastDate = "";

        for (Task task : tasks) {

            String taskDate = task.getDeadline().toLocalDate().toString();

            // cria separador de data quando necessário
            if (!taskDate.equals(lastDate)) {
                Label dateLabel = new Label(taskDate);
                dateLabel.setStyle("-fx-font-size:16; -fx-font-weight:bold; -fx-padding: 10 0 5 0;");
                taskList.getChildren().add(dateLabel);
                lastDate = taskDate;
            }

            // checkbox principal (título da tarefa)
            CheckBox checkBox = new CheckBox(task.getTitle());
            checkBox.setStyle("-fx-font-weight:bold; -fx-font-size:14;");

            if (task.isOverdue() && !task.isCompleted()) {
                checkBox.setStyle(
                        "-fx-font-weight:bold; " +
                                "-fx-font-size:14; " +
                                "-fx-text-fill: red;"
                );
            }

            checkBox.setSelected(task.isCompleted());

            // container com informações adicionais
            VBox infoContainer = new VBox(2);
            infoContainer.setStyle("-fx-padding: 0 0 10 30;");

            Label description = new Label(task.getDescription());
            //description.setStyle("-fx-text-fill: #666666; -fx-font-style: italic;");

            description.setStyle(
                    task.isOverdue() && !task.isCompleted()
                            ? "-fx-text-fill: #ff4d4d; -fx-font-style: italic;"
                            : "-fx-text-fill: #666666; -fx-font-style: italic;"
            );


            String time = String.format("%02d:%02d",
                    task.getDeadline().getHour(),
                    task.getDeadline().getMinute());

            Label dateTime = new Label("Horário: " + time);

            Label categoryLabel = new Label("Categoria: " + task.getCategory());
            categoryLabel.setStyle("-fx-text-fill: #2d8cff; -fx-font-weight: bold; -fx-font-size: 11px;");

            infoContainer.getChildren().addAll(description, dateTime, categoryLabel);
            taskList.getChildren().addAll(checkBox, infoContainer);

            // ação ao marcar como concluída
            checkBox.setOnAction(e -> {
                if (checkBox.isSelected()) {
                    taskService.concluirTarefa(task);

                    // Re-renderiza respeitando o filtro atual
                    if (currentCategory != null) {
                        List<Task> filtered = taskService.filtrarPorCategoria(currentCategory);
                        renderTaskList(filtered);
                    } else {
                        renderTaskList(allTasks.inOrder());
                    }
                }
            });
        }
    }

    private void refreshUI() {
        if (currentCategory != null) {
            List<Task> filtered = taskService.filtrarPorCategoria(currentCategory);
            renderTaskList(filtered);
        } else {
            renderTaskList(allTasks.inOrder());
        }
    }

    // mostra todas as tarefas (sem filtro)
    @FXML
    private void showAllTasks() {
        currentCategory = null;
        renderTaskList(allTasks.inOrder());
    }

    // filtra tarefas por categoria selecionada
    @FXML
    private void showByCategory(ActionEvent event) {

        MenuItem item = (MenuItem) event.getSource();

        String categoryName = item.getText();
        currentCategory = Category.valueOf(categoryName.toUpperCase());

        ArrayList<Task> filtered = taskService.filtrarPorCategoria(currentCategory);
        ArrayList<Task> ordered = taskService.ordenarPorPrazo(filtered);

        renderTaskList(ordered);
    }

    // abre tela de criação de nova tarefa
    @FXML
    private void openNewTask(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        ScreenLoader.load(
                stage,
                "/com/eda/gerenciadortarefas/view/form-newt-task.fxml",
                600,
                480,
                taskService
        );
    }

    // abre tela de tarefas concluídas
    @FXML
    private void openCompletedTasks(ActionEvent event) {
        Stage stage = (Stage) btnCompleted.getScene().getWindow();

        ScreenLoader.load(
                stage,
                "/com/eda/gerenciadortarefas/view/completed-tasks.fxml",
                600,
                480,
                taskService
        );
    }
}