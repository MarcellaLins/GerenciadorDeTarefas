package com.eda.gerenciadortarefas.utils;

import com.eda.gerenciadortarefas.service.TaskService;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Responsável por carregar telas (FXML) e injetar dependências nos controllers
public class ScreenLoader {

    // Carrega uma nova tela no stage atual
    public static void load(
            Stage stage,
            String fxmlPath,
            int width,
            int height,
            TaskService taskService
    ) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    ScreenLoader.class.getResource(fxmlPath)
            );

            // Injeta o TaskService em controllers que implementam TaskServiceAware
            loader.setControllerFactory(controllerClass -> {
                try {
                    Object controller = controllerClass.getDeclaredConstructor().newInstance();

                    if (controller instanceof TaskServiceAware) {
                        ((TaskServiceAware) controller).setTaskService(taskService);
                    }

                    return controller;

                } catch (Exception exception) {
                    throw new RuntimeException("Error creating controller", exception);
                }
            });

            Parent root = loader.load();

            // Configuração da cena
            Scene scene = new Scene(root, width, height);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception exception) {
            // Falha crítica ao carregar tela
            exception.printStackTrace();
        }
    }
}