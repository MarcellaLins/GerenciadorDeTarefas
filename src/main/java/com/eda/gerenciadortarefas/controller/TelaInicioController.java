package com.eda.gerenciadortarefas.controller;

import com.eda.gerenciadortarefas.service.TarefaService;
import com.eda.gerenciadortarefas.utils.TrocaDeTela;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.eda.gerenciadortarefas.model.Tarefa;
import com.eda.gerenciadortarefas.structures.AVL;
import java.time.LocalDateTime;
//import com.eda.gerenciadortarefas.controller.TelaConcluidasController;
import javafx.util.StringConverter;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TelaInicioController {

    @FXML
    private Label title;

    @FXML
    private VBox taskList;

    @FXML
    private Button btnConcluidos;

    public static AVL<Tarefa> avl = new AVL<>();

    public static AVL<Tarefa> getArvore() {
        return avl;
    }

    @FXML
    public void initialize() {

        atualizarTela();

        Platform.runLater(() -> {

            Stage stage = (Stage) title.getScene().getWindow();

            stage.widthProperty().addListener((o, oldVal, newVal) -> {
                title.setStyle("-fx-font-size: " + (newVal.doubleValue() / 25) + "px;");
            });

        });

    }

    public void adicionarTarefa(String dia, String nome, String hora, String tipo) {

        Tarefa tarefa = new Tarefa();

        avl.insert(tarefa);

        atualizarTela();
    }

    private void atualizarTela() {
        taskList.getChildren().clear();

        String ultimaData = "";

        for (Tarefa t : avl.inOrder()) {

            // cabeçalho
            String dataTarefa = t.getDeadline().toLocalDate().toString();

            if (!dataTarefa.equals(ultimaData)) {
                Label labelData = new Label(dataTarefa);
                labelData.setStyle("-fx-font-size:16; -fx-font-weight:bold; -fx-padding: 10 0 5 0;");
                taskList.getChildren().add(labelData);
                ultimaData = dataTarefa;
            }

            // título da Tarefa
            CheckBox check = new CheckBox(t.getTitle());
            check.setStyle("-fx-font-weight:bold; -fx-font-size:14;");
            check.setSelected(t.isCompleted());

            check.setOnAction(e -> {
                if (check.isSelected()) {
                    t.markAsCompleted();
                    atualizarTela();
                }
            });

            // descrição, hora e categoria
            VBox infoContainer = new VBox(2);
            infoContainer.setStyle("-fx-padding: 0 0 10 30;");

            // Descrição
            Label desc = new Label(t.getDescription());
            desc.setStyle("-fx-text-fill: #666666; -fx-font-style: italic;");

            // Hora formatada
            String hora = String.format("%02d:%02d", t.getDeadline().getHour(), t.getDeadline().getMinute());
            Label dateTime = new Label("Horário: " + hora);

            // Categoria
            Label category = new Label("Categoria: " + t.getCategory());
            category.setStyle("-fx-text-fill: #2d8cff; -fx-font-weight: bold; -fx-font-size: 11px;");

            // Adiciona as informações no container
            infoContainer.getChildren().addAll(desc, dateTime, category);
            taskList.getChildren().addAll(check, infoContainer);

            check.setOnAction(event -> {
                if (check.isSelected()) {
                    // Remove a tarefa da AVL e atualiza
                    avl.remove(t);
                    atualizarTela();
                }
            });
        }
    }

    @FXML
    private void abrirNovaTarefa(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TrocaDeTela.carregar(stage, "/com/eda/gerenciadortarefas/view/nova-tarefa.fxml", 500, 400);
    }

    @FXML
    private void abrirConcluidos(ActionEvent event) {
        Stage stage = (Stage) btnConcluidos.getScene().getWindow();
        TrocaDeTela.carregar(stage, "/com/eda/gerenciadortarefas/view/TelaConcluidos.fxml", 500, 400);
    }
}