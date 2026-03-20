package com.eda.gerenciadortarefas.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrocaDeTela {

    // criado para ser chamado por tela
    public static void carregar(Stage stage, String caminhoFXML, int largura, int altura) {
        try {
            FXMLLoader loader = new FXMLLoader(TrocaDeTela.class.getResource(caminhoFXML));
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
