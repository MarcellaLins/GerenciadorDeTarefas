package br.com.gerenciadorDeTarefas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import br.com.gerenciadorDeTarefas.model.Categoria;
import br.com.gerenciadorDeTarefas.model.Tarefa;
import br.com.gerenciadorDeTarefas.structures.AVL;

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

        String title = tituloField.getText();
        String description;
        String categoria = categoriaCombo.getValue();

        System.out.println("Tarefa adicionada: " + title);

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
            return;
        }

        String title = tituloField.getText();
        String description = descricao.getText();
        LocalDate dia = prazoPicker.getValue();
        String categoriaStr = categoriaCombo.getValue();

        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());

        // cria a tarefa
        Tarefa nova = new Tarefa(title, description, categoria, dia.atStartOfDay());

        // insere na AVL
        Tela_Inicio.getArvore().insert(nova);

        System.out.println("Tarefa adicionada: " + nova);

        // volta para tela inicial
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Tela_Inicio.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tituloField.getScene().getWindow();
            Scene scene = new Scene(root, 500, 400);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
