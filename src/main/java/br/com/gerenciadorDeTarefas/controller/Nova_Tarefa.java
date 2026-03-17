package main.java.br.com.gerenciadorDeTarefas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Nova_Tarefa {
    @FXML
    private TextField tituloField;

    @FXML
    private TextArea descricao;

    @FXML
    private DatePicker prazoPicker;

    @FXML
    private ComboBox<String> categoriaCombo;

    @FXML
    public void initialize() {

        categoriaCombo.getItems().addAll(
                "Estudo",
                "Trabalho",
                "Pessoal",
                "Saúde"
        );

    }

    @FXML
    private void adicionarTarefa() {

        String titulo = tituloField.getText();
        String descricao;
        String categoria = categoriaCombo.getValue();

        System.out.println("Tarefa adicionada: " + titulo);

    }

    private boolean validarCampos() {

        if (tituloField.getText().isEmpty()) {
            mostrarErro("Digite o título da tarefa");
            return false;
        }

        if (descricao.getText().isEmpty()) {
            mostrarErro("Digite a descrição da tarefa");
            return false;
        }

        if (prazoPicker.getValue() == null) {
            mostrarErro("Selecione o dia");
            return false;
        }

        /* if (campoHora.getText().isEmpty()) {
            mostrarErro("Digite o horário");
            return false;
        }
*/
        if (categoriaCombo.getValue().isEmpty()) {
            mostrarErro("Digite o tipo");
            return false;
        }

        return true;
    }

    private void mostrarErro(String msg) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Campos obrigatórios");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void salvarTarefa() {

        if (!validarCampos()) {
            return; // PARA TUDO
        }

        String titulo = tituloField.getText();
        String descricao;
        LocalDate dia = prazoPicker.getValue();
        //    String hora = campoHora.getText();
        String tipo = categoriaCombo.getValue();

        //  Tela_InicioController.TarefaAVL(dia, titulo, tipo);
    }





}
