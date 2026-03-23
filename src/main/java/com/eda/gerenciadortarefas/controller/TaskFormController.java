package com.eda.gerenciadortarefas.controller;

import com.eda.gerenciadortarefas.model.Category;
import com.eda.gerenciadortarefas.service.TaskService;
import com.eda.gerenciadortarefas.utils.ScreenLoader;
import com.eda.gerenciadortarefas.utils.TaskServiceAware;
import com.eda.gerenciadortarefas.utils.ScreenLoader;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.LocalTime;

// controller responsável pelo formulário de criação de tarefas
public class TaskFormController implements TaskServiceAware {

    private TaskService taskService;

    @Override
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> categoryCombo;

    @FXML
    private TextField timeField;

    @FXML
    public void initialize() {

        // máscara para HH:mm
        timeField.textProperty().addListener((obs, oldValue, newValue) -> {

            if (!newValue.matches("[\\d:]*")) {
                timeField.setText(oldValue);
                return;
            }

            if (newValue.length() == 2 && oldValue.length() == 1) {
                timeField.setText(newValue + ":");
            }

            if (newValue.length() > 5) {
                timeField.setText(oldValue);
            }
        });

        // preenche categorias (vem do enum diretamente)
        categoryCombo.getItems().addAll(
                "ESTUDOS",
                "TRABALHO",
                "CASA",
                "FINANCAS",
                "SAUDE",
                "PESSOAL"
        );
    }

    // validação dos campos do formulário
    private boolean validateFields() {

        if (titleField.getText().isEmpty()) {
            showError("Digite o título da tarefa");
            return false;
        }

        if (descriptionField.getText().isEmpty()) {
            showError("Digite a descrição da tarefa");
            return false;
        }

        if (datePicker.getValue() == null) {
            showError("Selecione o dia");
            return false;
        }

        if (categoryCombo.getValue() == null) {
            showError("Selecione a categoria");
            return false;
        }

        String time = timeField.getText();

        LocalTime parsedTime;

        try {
            String[] parts = time.split(":");

            if (parts.length != 2) {
                showError("Formato inválido. Use HH:mm");
                return false;
            }

            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            if (hour < 0 || hour > 23) {
                showError("Hora deve estar entre 00 e 23");
                return false;
            }

            if (minute < 0 || minute > 59) {
                showError("Minuto deve estar entre 00 e 59");
                return false;
            }

            parsedTime = LocalTime.of(hour, minute);

        } catch (Exception e) {
            showError("Horário inválido");
            return false;
        }

        LocalDate selectedDate = datePicker.getValue();

        if (selectedDate.atTime(parsedTime).isBefore(java.time.LocalDateTime.now())) {
            showError("Não é permitido criar tarefas com prazo no passado");
            return false;
        }

        return true;
    }

    // exibe alerta de erro
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Campos inválidos");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // salva a tarefa e retorna para tela principal
    @FXML
    private void saveTask(ActionEvent event) {

        if (!validateFields()) {
            return;
        }

        String title = titleField.getText();
        String description = descriptionField.getText();
        LocalDate date = datePicker.getValue();
        String categoryStr = categoryCombo.getValue();

        LocalTime time;

        try {
            time = LocalTime.parse(timeField.getText());
        } catch (Exception e) {
            time = LocalTime.MIDNIGHT; // fallback seguro
        }

        Category category = Category.valueOf(categoryStr.toUpperCase());

        taskService.taskAdd(title, description, category, date.atTime(time));

        // navegação de volta (reutilizando util existente)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        ScreenLoader.load(
                stage,
                "/com/eda/gerenciadortarefas/view/main-view.fxml",
                600,
                480,
                taskService
        );
    }
}