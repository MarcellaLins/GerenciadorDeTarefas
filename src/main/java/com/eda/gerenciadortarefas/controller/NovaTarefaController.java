package com.eda.gerenciadortarefas.controller;

import com.eda.gerenciadortarefas.service.TarefaService;
import com.eda.gerenciadortarefas.utils.TaskService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.eda.gerenciadortarefas.model.Categoria;
import com.eda.gerenciadortarefas.model.Tarefa;
import com.eda.gerenciadortarefas.structures.AVL;
import com.eda.gerenciadortarefas.controller.TelaInicioController;

import java.time.LocalDate;
import java.time.LocalTime;

public class NovaTarefaController implements TaskService {
    private TarefaService tarefaService;

    @Override
    public void setService(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @FXML
    private TextField tituloField;

    @FXML
    private TextArea descricao;

    @FXML
    private DatePicker prazoPicker;

    @FXML
    private ComboBox<String> categoriaCombo;

    @FXML
    private TextField horaField;

    @FXML
    public void initialize() {

        System.out.println(tarefaService);

        // --- MÁSCARA DE HORA ---
        horaField.textProperty().addListener((obs, antigo, novo) -> {
            // Permite apenas números e :
            if (!novo.matches("[\\d:]*")) {
                horaField.setText(antigo);
            }

            // Adiciona : após o segundo número
            if (novo.length() == 2 && antigo.length() == 1) {
                horaField.setText(novo + ":");
            }

            // Limita a 5 caracteres (HH:mm)
            if (novo.length() > 5) {
                horaField.setText(antigo);
            }
        });

        categoriaCombo.getItems().addAll(
                "ESTUDOS",
                "TRABALHO",
                "CASA",
                "FINANCAS",
                "SAUDE",
                "PESSOAL"
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
/*
         if (campoHora.getText().isEmpty()) {
            mostrarErro("Digite o horário");
            return false;
        }*/

        if (categoriaCombo.getValue().isEmpty()) {
            mostrarErro("Digite o tipo");
            return false;
        }

        String horario = horaField.getText();

        try {

            String[] partes = horario.split(":");

            if (partes.length != 2) {
                mostrarErro("Formato inválido. Use HH:mm");
                return false;
            }

            int hora = Integer.parseInt(partes[0]);
            int minuto = Integer.parseInt(partes[1]);

            if (hora < 0 || hora > 23) {
                mostrarErro("A hora deve estar entre 00 e 23.");
                return false;
            }

            if (minuto < 0 || minuto > 59) {
                mostrarErro("Os minutos devem estar entre 00 e 59.");
                return false;
            }

        } catch (NumberFormatException e) {
            mostrarErro("Digite apenas números no horário.");
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

        LocalTime horario;
        try {
            // Tenta ler o que foi digitado (ex: "14:30")
            horario = LocalTime.parse(horaField.getText());
        } catch (Exception e) {
            // Se o campo estiver vazio ou o formato estiver errado,
            // define como 00:00 para o programa NÃO travar.
            horario = LocalTime.MIDNIGHT;
        }

        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());

        // Agora o horario não é nulo, o erro vai sumir!
        tarefaService.taskAdd(title, description, categoria, dia.atTime(horario));

        System.out.println("Tarefa adicionada com sucesso!");

        // volta para tela inicial
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/eda/gerenciadortarefas/view/tela-inicio.fxml"));
            loader.setControllerFactory(param -> {
                try {
                    Object controller = param.getDeclaredConstructor().newInstance();

                    if (controller instanceof TaskService) {
                        ((TaskService) controller).setService(tarefaService);
                    }

                    return controller;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
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