package com.eda.gerenciadortarefas;

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

        System.out.println("iniciando aplicação...");

        try {

            URL url = getClass().getResource("/com/eda/gerenciadortarefas/view/tela-inicio.fxml");

            if (url == null) {
                System.out.println("Não foi possível encontrar o arquivo .fxml");
                return;
            }

            FXMLLoader loader = new FXMLLoader(url);

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

        LinkedRec lista = new LinkedRec();

        for (int i = 0; i< 10; i++){
            lista.add(i);
        }


        System.out.println(lista);


        Queue fila = new Queue();

        for (int i =0; i<10; i++){
            fila.enqueue(i);
        }

        System.out.println(fila);
        System.out.println(fila.dequeue());
        System.out.println(fila.size());

        // testes com Tarefa

        Tarefa t1 = new Tarefa(
                "Estudar Tabela Hash",
                "Revisar e implementar métodos de forma iterativa",
                Categoria.ESTUDOS,
                // LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute)
                LocalDateTime.of(2026, 3, 10, 14, 0)
        );

        Tarefa t2 = new Tarefa(
                "Estudar Tabela Hash",
                "Revisar e implementar métodos de forma iterativa",
                Categoria.ESTUDOS,
                LocalDateTime.of(2026, 3, 7, 9, 30)
        );

        System.out.println(t1.getId());
        System.out.println(t2.getId());
        System.out.println("O hash é: " + t2.hashCode());

        launch(args);
    }
}