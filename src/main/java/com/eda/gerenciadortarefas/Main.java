package com.eda.gerenciadortarefas;

import com.eda.gerenciadortarefas.service.TarefaService;
import com.eda.gerenciadortarefas.utils.TaskService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;

import com.eda.gerenciadortarefas.model.Tarefa;
import com.eda.gerenciadortarefas.model.Categoria;
import com.eda.gerenciadortarefas.structures.LinkedRec;
import com.eda.gerenciadortarefas.structures.Queue;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        TarefaService tarefaService = new TarefaService();

        System.out.println("iniciando aplicação...");

        try {

            URL url = getClass().getResource("/com/eda/gerenciadortarefas/view/tela-inicio.fxml");

            if (url == null) {
                System.out.println("Não foi possível encontrar o arquivo .fxml");
                return;
            }

            FXMLLoader loader = new FXMLLoader(url);
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

            Scene scene = new Scene(root, 500, 400);
            stage.setTitle("Gerenciador de Tarefas");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            System.out.println("Erro ao abrir tela:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}