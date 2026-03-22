package com.eda.gerenciadortarefas.controller;

import com.eda.gerenciadortarefas.model.Task;
import com.eda.gerenciadortarefas.service.TaskService;
import com.eda.gerenciadortarefas.utils.TaskServiceAware;
import com.eda.gerenciadortarefas.utils.ScreenLoader;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CompletedTasksController implements TaskServiceAware {

    private TaskService taskService;

    private boolean initialized = false;

    @FXML
    private VBox completedTasksList;

    @FXML
    private Button btnPending;

    @FXML
    private Button btnCompleted;

    @Override
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;

        if (initialized) {
            renderCompletedTasks();
        }
    }

    @FXML
    public void initialize() {
        initialized = true;

        completedTasksList.getChildren().clear();

        if (taskService != null) {
            renderCompletedTasks();
        }
    }

    // renderiza respeitando a ORDEM DA FILA
    private void renderCompletedTasks() {
        completedTasksList.getChildren().clear();

        List<Task> completedTasks = taskService.getCompletedTasksSnapshot();

        for (Task task : completedTasks) {
            CheckBox checkBox = new CheckBox(task.getTitle());
            checkBox.setSelected(true);
            checkBox.setDisable(true);
            checkBox.setStyle("-fx-font-weight:bold; -fx-font-size:14;");

            VBox infoContainer = new VBox(2);
            infoContainer.setStyle("-fx-padding: 0 0 10 30;");

            Label description = new Label(task.getDescription());
            description.setStyle("-fx-text-fill: #666666; -fx-font-style: italic;");

            String time = String.format("%02d:%02d",
                    task.getDeadline().getHour(),
                    task.getDeadline().getMinute());
            Label dateTime = new Label("Horário: " + time);

            Label categoryLabel = new Label("Categoria: " + task.getCategory());
            categoryLabel.setStyle("-fx-text-fill: #2d8cff; -fx-font-weight: bold; -fx-font-size: 11px;");

            Label status = new Label("Concluída");
            status.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");

            infoContainer.getChildren().addAll(description, dateTime, categoryLabel, status);
            completedTasksList.getChildren().addAll(checkBox, infoContainer);
        }
    }

    // navegação para pendentes
    @FXML
    private void openPendingTasks() {
        Stage stage = (Stage) btnPending.getScene().getWindow();

        ScreenLoader.load(
                stage,
                "/com/eda/gerenciadortarefas/view/main-view.fxml",
                600,
                480,
                taskService
        );
    }
}