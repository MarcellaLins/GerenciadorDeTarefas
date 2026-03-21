package com.eda.gerenciadortarefas.controller;

import com.eda.gerenciadortarefas.model.Categoria;
import com.eda.gerenciadortarefas.service.TarefaService;
import com.eda.gerenciadortarefas.utils.TaskService;
import com.eda.gerenciadortarefas.utils.TrocaDeTela;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.List;
import java.util.Collection;

public class TelaInicioController implements TaskService {
    private TarefaService tarefaService;
    private AVL<Tarefa> todasTarefas;

    private boolean initialized = false;
    private Categoria categoriaAtual = null;

    @Override
    public void setService(TarefaService tarefaService){
        this.tarefaService = tarefaService;
        this.todasTarefas = tarefaService.getTodasTarefas();

        if (initialized) {
            mostrarTodasTarefas();
        }
    }

    @FXML
    private Label title;

    @FXML
    private VBox taskList;

    @FXML
    private Button btnConcluidos;

    @FXML
    public void initialize() {

        initialized = true;

        taskList.getChildren().clear();

        if (tarefaService != null) {
            mostrarTodasTarefas();
        }

        Platform.runLater(() -> {
            Stage stage = (Stage) title.getScene().getWindow();

            stage.widthProperty().addListener((o, oldVal, newVal) -> {
                title.setStyle("-fx-font-size: " + (newVal.doubleValue() / 25) + "px;");
            });
        });

    }

    private void renderizarLista(Collection<Tarefa> tarefas) {
        taskList.getChildren().clear();

        // Garante ordenação independente da estrutura
        List<Tarefa> listaOrdenada = new ArrayList<>(tarefas);
        listaOrdenada.sort(Comparator.comparing(Tarefa::getDeadline));

        String ultimaData = "";

        for (Tarefa t : listaOrdenada) {

            String dataTarefa = t.getDeadline().toLocalDate().toString();

            if (!dataTarefa.equals(ultimaData)) {
                Label labelData = new Label(dataTarefa);
                labelData.setStyle("-fx-font-size:16; -fx-font-weight:bold; -fx-padding: 10 0 5 0;");
                taskList.getChildren().add(labelData);
                ultimaData = dataTarefa;
            }

            CheckBox check = new CheckBox(t.getTitle());
            check.setStyle("-fx-font-weight:bold; -fx-font-size:14;");
            check.setSelected(t.isCompleted());

            VBox infoContainer = new VBox(2);
            infoContainer.setStyle("-fx-padding: 0 0 10 30;");

            Label desc = new Label(t.getDescription());
            desc.setStyle("-fx-text-fill: #666666; -fx-font-style: italic;");

            String hora = String.format("%02d:%02d",
                    t.getDeadline().getHour(),
                    t.getDeadline().getMinute());
            Label dateTime = new Label("Horário: " + hora);

            Label categoryLabel = new Label("Categoria: " + t.getCategory());
            categoryLabel.setStyle("-fx-text-fill: #2d8cff; -fx-font-weight: bold; -fx-font-size: 11px;");

            infoContainer.getChildren().addAll(desc, dateTime, categoryLabel);
            taskList.getChildren().addAll(check, infoContainer);

            check.setOnAction(e -> {
                if (check.isSelected()) {
                    tarefaService.concluirTarefa(t);

                    if (categoriaAtual != null) {
                        List<Tarefa> filtradas = tarefaService.filtrarPorCategoria(categoriaAtual);
                        renderizarLista(filtradas);
                    } else {
                        renderizarLista(todasTarefas.inOrder());
                    }
                }
            });
        }
    }

    @FXML
    private void mostrarTodasTarefas() {
        categoriaAtual = null;
        renderizarLista(todasTarefas.inOrder());
    }

    @FXML
    private void mostrarPorCategoria(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();

        String nomeCategoria = item.getText();
        categoriaAtual = Categoria.valueOf(nomeCategoria.toUpperCase());

        List<Tarefa> filtradas = tarefaService.filtrarPorCategoria(categoriaAtual);

        renderizarLista(filtradas);
    }

    @FXML
    private void abrirNovaTarefa(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TrocaDeTela.carregar(stage, "/com/eda/gerenciadortarefas/view/nova-tarefa.fxml", 500, 400, tarefaService);
    }

    @FXML
    private void abrirConcluidos(ActionEvent event) {
        Stage stage = (Stage) btnConcluidos.getScene().getWindow();
        TrocaDeTela.carregar(stage, "/com/eda/gerenciadortarefas/view/TelaConcluidos.fxml", 500, 400, tarefaService);
    }
}