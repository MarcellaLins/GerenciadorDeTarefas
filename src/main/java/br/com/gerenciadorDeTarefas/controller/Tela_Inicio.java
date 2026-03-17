package main.java.br.com.gerenciadorDeTarefas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structures.AVL;


import java.io.IOException;

public class Tela_Inicio {

    @FXML
    private Label titulo;

    @FXML
    private VBox listaTarefas;

    private AVL<TarefaAVL> avl = new AVL<>();

    @FXML
    public void initialize() {

        titulo.sceneProperty().addListener((obs, oldScene, newScene) -> {

            if (newScene != null) {

                newScene.windowProperty().addListener((obsWindow, oldWindow, newWindow) -> {

                    if (newWindow != null) {

                        Stage stage = (Stage) newWindow;

                        stage.widthProperty().addListener((o, oldVal, newVal) -> {
                            titulo.setStyle("-fx-font-size: " + (newVal.doubleValue() / 25) + "px;");
                        });

                    }

                });

            }

        });
    }

    public void filtrar() {
        System.out.println(avl.inOrder()); // MOSTRA A AVL ORDENADA
    }

    public void adicionarTarefa(String dia, String nome, String hora, String tipo) {

        TarefaAVL tarefa = new TarefaAVL(nome, dia, hora, tipo);

        avl.insert(tarefa);

        atualizarTela();
    }

    private void atualizarTela() {

        listaTarefas.getChildren().clear();

        String ultimoDia = "";

        for (TarefaAVL t : avl.inOrder()) {

            if (!t.getDia().equals(ultimoDia)) {

                Label labelDia = new Label(t.getDia());
                labelDia.setStyle("-fx-font-size:16; -fx-font-weight:bold;");

                listaTarefas.getChildren().add(labelDia);

                ultimoDia = t.getDia();
            }

            CheckBox check = new CheckBox(t.getNome());

            Label hora = new Label(t.getHora());
            Label tipo = new Label(t.getTipo());

            VBox info = new VBox(hora, tipo);

            HBox box = new HBox(10, check, info);
            box.setStyle("-fx-padding:0 0 10 20;");

            listaTarefas.getChildren().add(box);
        }
    }


    @FXML
    private void abrirNovaTarefa(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/projetoLeda/Nova_Tarefa (1).fxml")
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root, 500, 400));
    }
}
