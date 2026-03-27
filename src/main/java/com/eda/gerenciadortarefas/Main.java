package com.eda.gerenciadortarefas;

import com.eda.gerenciadortarefas.service.TaskService;
import com.eda.gerenciadortarefas.utils.TaskServiceAware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

// classe principal da aplicação de gerenciamento de tarefas.
public class Main extends Application {

    // logger da classe
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) {
        // instância da camada de serviço
        TaskService taskService = new TaskService();

        logger.info("Iniciando aplicação...");

        try {
            // carrega o arquivo FXML da tela inicial
            URL fxmlLocation = getClass().getResource("/com/eda/gerenciadortarefas/view/main-view.fxml");

            if (fxmlLocation == null) {
                logger.severe("Arquivo FXML não encontrado: main-view.fxml");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);

            // injeta o serviço nos controllers que implementam TaskService
            loader.setControllerFactory(controllerClass -> {
                try {
                    Object controller = controllerClass.getDeclaredConstructor().newInstance();

                    if (controller instanceof TaskServiceAware) {
                        ((TaskServiceAware) controller).setTaskService(taskService);
                    }

                    return controller;

                } catch (Exception exception) {
                    logger.log(Level.SEVERE, "Erro ao criar instância do controller", exception);
                    throw new RuntimeException(exception);
                }
            });

            Parent root = loader.load();

            // configuração da cena
            Scene scene = new Scene(root, 600, 480);

            primaryStage.setTitle("Gerenciador de Tarefas");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            logger.info("Aplicação iniciada com sucesso.");

        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Erro ao carregar a tela principal", exception);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}