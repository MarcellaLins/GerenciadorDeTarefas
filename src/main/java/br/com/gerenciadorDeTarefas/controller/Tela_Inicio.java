package br.com.gerenciadorDeTarefas.controller;

import br.com.gerenciadorDeTarefas.utils.TrocaDeTela;
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
import br.com.gerenciadorDeTarefas.model.Tarefa;
import br.com.gerenciadorDeTarefas.structures.AVL;
import java.time.LocalDateTime;
import br.com.gerenciadorDeTarefas.controller.Tela_Concluidas;

import java.io.IOException;

public class Tela_Inicio {

    @FXML
    private Label title;

    @FXML
    private VBox taskList;

    @FXML
    private Button btnConcluidos;

    private static AVL<Tarefa> avl = new AVL<>();

    public static AVL<Tarefa> getArvore() {
        return avl;
    }

    @FXML
    public void initialize() {

    atualizarTela();

        javafx.application.Platform.runLater(() -> {

            Stage stage = (Stage) title.getScene().getWindow();

            stage.widthProperty().addListener((o, oldVal, newVal) -> {
                title.setStyle("-fx-font-size: " + (newVal.doubleValue() / 25) + "px;");
            });

        });
    }

    public void filtrar() {
        System.out.println(avl.inOrder());
    }

    public void adicionarTarefa(String dia, String nome, String hora, String tipo) {

        Tarefa tarefa = new Tarefa();

        avl.insert(tarefa);

        atualizarTela();
    }

    private void atualizarTela() {

        taskList.getChildren().clear();

        // divide as tarefas por dia para que não apareça o dia em toda tarefa que for criada. por exemplo, Hoje seguidos de todas as tarefas para hoje.
        String ultimoDia = "";

        for (Tarefa t : avl.inOrder()) {

            if (!t.getCreationDate().equals(ultimoDia)) {

                Label labelCriationDate = new Label();
                labelCriationDate.setStyle("-fx-font-size:16; -fx-font-weight:bold;");
                taskList.getChildren().add(labelCriationDate);

                ultimoDia = String.valueOf(t.getCreationDate());
            }

            CheckBox check = new CheckBox(t.getTitle());

            Label dateTime = new Label();
            Label category = new Label();

            VBox info = new VBox(dateTime, category);

            HBox box = new HBox(10, check, info);
            box.setStyle("-fx-padding:0 0 10 20;");

            taskList.getChildren().add(box);
        }
    }

    @FXML
    private void abrirNovaTarefa(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TrocaDeTela.carregar(stage, "/view/Nova_Tarefa.fxml", 500, 400);
    }

    @FXML
    private void abrirConcluidos(ActionEvent event) {
        Stage stage = (Stage) btnConcluidos.getScene().getWindow();
        TrocaDeTela.carregar(stage, "/view/Tela_Concluidos.fxml", 500, 400);
    }



}