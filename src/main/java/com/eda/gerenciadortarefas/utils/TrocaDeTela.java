package com.eda.gerenciadortarefas.utils;

import com.eda.gerenciadortarefas.service.TarefaService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrocaDeTela {

    public static void carregar(Stage stage, String caminhoFXML, int largura, int altura, TarefaService tarefaService) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocaDeTela.class.getResource(caminhoFXML));

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

            Scene scene = new Scene(root, largura, altura);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
